package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.JpaConst;
import models.Attendance;
import models.Department;
import models.Division;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class WorkLogEditServlet
 */
@WebServlet("/work_log_edit")
public class IndexWorkLogEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexWorkLogEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = ((HttpServletRequest) request).getSession();
         Integer login_user_id = (Integer) session.getAttribute("login_user_id");

         LocalDate today;

         if(request.getSession().getAttribute("today")!=null)
         {
             today=((LocalDate)request.getSession().getAttribute("today"));
             request.getSession().removeAttribute("today");
         }
         else if(request.getAttribute("selected_date")!=null)
         {
             today=(LocalDate)request.getAttribute("selected_date");
             request.getSession().removeAttribute("selected_date");
         }
         else
         {
             today=LocalDate.now();
         }
         System.out.println("today:"+today);
         int yyyy = Integer.parseInt(today.toString().substring(0,4));
         int MM = Integer.parseInt(today.toString().substring(5,7));
         int dd = Integer.parseInt(today.toString().substring(8,10));
         Calendar cal = Calendar.getInstance();

         cal.set(yyyy,MM-1,dd);
         int last = cal.getActualMaximum(Calendar.DATE);

         LocalDate start_date_month=LocalDate.of(yyyy, MM, 1);
         LocalDate end_date_month=LocalDate.of(yyyy, MM, last);

         EntityManager em = DBUtil.createEntityManager();

         List<User> users = em.createNamedQuery("selectUser_UserId", User.class).setParameter("id",login_user_id).getResultList();

         List<Department> depts=null;
         List<Division> divs=null;
         if(users.size()>0)
         {
             depts = em.createNamedQuery("getDepartment_deptId", Department.class).setParameter("dept_id",users.get(0).getDept_id()).getResultList();

             if(depts.size()>0)
             {
                 divs=em.createNamedQuery("getDivision_divisionId", Division.class).setParameter("div_id",depts.get(0).getDivision_id()).getResultList();
             }
         }

         List<Attendance> attendances = (List<Attendance>)em.createNamedQuery("selectAttendance_Today",Attendance.class).setParameter("id",login_user_id).setParameter("date",today).getResultList();
         System.out.println("attendances:"+attendances.size());

         List<Attendance> attendances_month= (List<Attendance>)em.createNamedQuery("selectAttendance_MonthStats",Attendance.class)
                     .setParameter("id",login_user_id).setParameter("start_date",LocalDate.now())
                             .setParameter("start_date",start_date_month)
                                     .setParameter("end_date",end_date_month).getResultList();
         em.close();


         String user_name = null;
         if(users.size()>0)
         {
             user_name= users.get(0).getUser_name();
         }

         int worked_holiday_count=0;
         int used_holiday_count=0;
         int worked_hour_of_date=0;
         int worked_min_of_date=0;
         int worked_hour_stats=0;
         int worked_min_stats=0;
         int aditional_worked_hour_of_date=0;
         int aditional_worked_hour_stats=0;
         int aditional_worked_min_stats=0;

         for(Attendance buf:attendances_month)
         {

             if(buf.getWork_class()==JpaConst.work_class.rest.ordinal()+1)
             {
                 used_holiday_count++;
             }

             if(buf.getWork_holiday_flg()==JpaConst.FLG_TRUE)
             {
                 worked_holiday_count++;
             }

             if(buf.getEnd_date()==null || buf.getStart_date()==null)
             {
                 continue;
             }

             int start_date=buf.getStart_date().getDayOfYear();
             int end_date=buf.getEnd_date().getDayOfYear();
             int start_hour=buf.getStart_date().getHour()*JpaConst.HOUR;
             int end_hour=buf.getEnd_date().getHour()*JpaConst.HOUR;
             int start_min=buf.getStart_date().getMinute();
             int end_min=buf.getEnd_date().getMinute();

             if(start_date==end_date)
             {
                 worked_min_of_date=(end_hour+end_min) -(start_hour+start_min);
             }
             else if(start_date<end_date)
             {
                 worked_min_of_date=(JpaConst.DAY * JpaConst.HOUR )-(start_hour+start_min)+(end_hour+end_min);
             }

             worked_hour_of_date=worked_min_of_date/JpaConst.HOUR ;
             if(worked_hour_of_date>=JpaConst.DEFAULT_WORK_HOUR)
             {
                 worked_hour_of_date-=JpaConst.DEFAULT_WORK_REST;//休憩時間を減算
                 aditional_worked_hour_of_date=worked_hour_of_date-JpaConst.DEFAULT_WORK_HOUR;//残業時間算出
                 aditional_worked_hour_stats+=aditional_worked_hour_of_date;
             }
             worked_hour_stats+=worked_hour_of_date;

             worked_min_of_date%=JpaConst.HOUR ;
             worked_min_stats+=worked_min_of_date;
         }

         worked_hour_stats+=(int)(worked_min_stats/JpaConst.MINUTE);

         worked_min_stats%=JpaConst.MINUTE;
         if(aditional_worked_hour_stats>0)
         {
             aditional_worked_min_stats=worked_min_stats;
         }

         if(attendances.size()>0)
         {
             if(attendances.get(0).getStart_date()!=null)
             {
                 request.setAttribute("start_date", attendances.get(0).getStart_date().format(DateTimeFormatter.ofPattern("HH:mm" )));
             }

             if(attendances.get(0).getEnd_date()!=null)
             {
                 request.setAttribute("end_date", attendances.get(0).getEnd_date().format(DateTimeFormatter.ofPattern("HH:mm" )));
             }

             if(attendances.get(0).getWork_class()!=null)
             {
                 if(attendances.get(0).getWork_class() == JpaConst.work_class.work.ordinal()+1)//0始まりのため
                 {
                     request.setAttribute("work_class", "出勤");
                 }

                 if(attendances.get(0).getWork_class() == JpaConst.work_class.rest.ordinal()+1)
                 {
                     request.setAttribute("work_class", "有給休暇");
                 }

                 if(attendances.get(0).getWork_class() == JpaConst.work_class.absence.ordinal()+1)
                 {
                     request.setAttribute("work_class", "欠勤");
                 }

                 if(attendances.get(0).getWork_class() == JpaConst.work_class.suspension.ordinal()+1)
                 {
                     request.setAttribute("work_class", "休職");
                 }


             }
         }

         request.setAttribute("today", today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd" )));
         if(divs.size()>0)
         {
             request.setAttribute("division", divs.get(0).getDivision_name());
         }
         if(depts.size()>0)
         {
             request.setAttribute("department", depts.get(0).getDepartment_name());
         }

         request.setAttribute("stats_year", yyyy);
         request.setAttribute("stats_month", MM);
         request.setAttribute("user_id", login_user_id);
         request.setAttribute("user_name", user_name);
         request.setAttribute("used_horiday_counts", used_holiday_count+"日");
         request.setAttribute("worked_holiday_count", worked_holiday_count+"日");
         request.setAttribute("default_work_hour", JpaConst.DEFAULT_WORK_HOUR_STATS+":00");
         request.setAttribute("worked_hour_stats", worked_hour_stats+":"+String.format("%02d", worked_min_stats));
         request.setAttribute("aditional_worked_hour_stats", aditional_worked_hour_stats+":"+String.format("%02d", aditional_worked_min_stats));

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/work_log_edit.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String BtnReqest=request.getParameter("type");
        LocalDate CurrentDate=null;

        if(request.getParameter("today")!=null)
        {
            CurrentDate=LocalDate.parse(request.getParameter("today").replace("/", "-"));
            System.out.println("today");
        }
        if(request.getParameter("selected_date")!=null)
        {
            CurrentDate=LocalDate.parse(request.getParameter("selected_date").replace("/", "-"));
            System.out.println("selected_date");
        }

        if(Objects.isNull(BtnReqest))
        {
            request.getSession().setAttribute("today",CurrentDate);
            response.sendRedirect(request.getContextPath() + "/work_log_edit");
            return;
        }

        if(BtnReqest.equals(JpaConst.NEXT))
        {
            CurrentDate=CurrentDate.plusDays(1);//1日進める
            request.getSession().setAttribute("today",CurrentDate);
            response.sendRedirect(request.getContextPath() + "/work_log_edit");
            return;
        }
        else if(BtnReqest.equals(JpaConst.UNDO))
        {
            CurrentDate=CurrentDate.minusDays(1);//1日戻す
            request.getSession().setAttribute("today",CurrentDate);
            response.sendRedirect(request.getContextPath() + "/work_log_edit");
            return;
        }
        else if(BtnReqest.equals(JpaConst.INPUT))
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime end_date=null;
            if(!request.getParameter("end_date").isBlank())
            {
                System.out.println("end_date:"+request.getParameter("end_date"));
                end_date= LocalDateTime.parse((CurrentDate + " " + request.getParameter("end_date").toString()),dtf);
                System.out.println(end_date);
            }
            LocalDateTime start_date=null;
            if(!request.getParameter("start_date").isBlank())
            {
                System.out.println("start_date"+request.getParameter("start_date"));
                start_date= LocalDateTime.parse((CurrentDate + " " + request.getParameter("start_date").toString()),dtf);

                if(!request.getParameter("end_date").isBlank())
                {
                    if(start_date.isAfter(end_date))//開始時間が終了時間より後だったら、前日の夜から勤務したと見做す
                    {
                        start_date= LocalDateTime.parse((CurrentDate.minusDays(1) + " " + request.getParameter("start_date").toString()),dtf);
                    }
                }
                System.out.println(start_date);
            }
            request.getSession().setAttribute("work_type",request.getParameter("work_type"));
            request.getSession().setAttribute("start_date",start_date);
            request.getSession().setAttribute("end_date",end_date);
            request.getSession().setAttribute("today",CurrentDate);
            response.sendRedirect(request.getContextPath() + "/create_update_work_log");
            return;
        }
    }
}
