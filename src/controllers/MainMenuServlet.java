package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import utils.DBUtil;

/**
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/main_menu")
public class MainMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer login_user_id = (Integer) session.getAttribute("login_user_id");
        EntityManager em = DBUtil.createEntityManager();
        List<Attendance> attendances = (List<Attendance>)em.createNamedQuery("selectAttendance_Today",Attendance.class).setParameter("id",login_user_id).setParameter("date",LocalDate.now()).getResultList();
        em.close();

        request.setAttribute("current_datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" )));

        if(attendances.size()>0)
        {
            System.out.println(LocalDate.now().toString()+":"+attendances.get(0).getDate());
            if(attendances.get(0).getStart_date()!=null)
            {
                request.setAttribute("start_date",attendances.get(0).getStart_date().format(DateTimeFormatter.ofPattern("MM/dd HH:mm" )));
            }
            if(attendances.get(0).getEnd_date()!=null)
            {
                request.setAttribute("end_date",attendances.get(0).getEnd_date().format(DateTimeFormatter.ofPattern("MM/dd HH:mm" )));
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/main_menu.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         HttpSession session = ((HttpServletRequest) request).getSession();
        Attendance pull_request=new Attendance();
        Integer login_user_id = (Integer)session.getAttribute("login_user_id");
        String btn_type=request.getParameter("btn_type");

        LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
        LocalDate today=LocalDate.now();

        Integer dayOfWeek=currentTime.getDayOfWeek().getValue();

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        List<Attendance> attendances = (List<Attendance>)em.createNamedQuery("selectAttendance_Today",Attendance.class).setParameter("id",login_user_id).setParameter("date",LocalDate.now()).getResultList();

        System.out.println("attendances.size():"+attendances.size());
        if(attendances.size()>0)
        {
            System.out.println("更新");

            pull_request= em.find(Attendance.class,attendances.get(0).getAttendance_id() );

            if(btn_type.equals("出勤")&&pull_request.getStart_date()==null) {
                System.out.println("出勤");
                pull_request.setStart_date(currentTime);
                pull_request.setUpd_date(currentTime);
                pull_request.setUpd_user(login_user_id.toString());
            }

            if(btn_type.equals("退勤")&&pull_request.getEnd_date()==null) {
                System.out.println("退勤");
                pull_request.setEnd_date(currentTime);
                pull_request.setUpd_date(currentTime);
                pull_request.setUpd_user(login_user_id.toString());
            }

            em.getTransaction().commit();
        }
        else
        {
            System.out.println("新規");

            pull_request.setUser_id(login_user_id);
            pull_request.setDate(today);
            pull_request.setWork_class(JpaConst.work_class.work.ordinal());

            if(btn_type.equals("出勤")&&pull_request.getStart_date()==null) {
                System.out.println("出勤");
                pull_request.setStart_date(currentTime);
            }

            if(btn_type.equals("退勤")&&pull_request.getEnd_date()==null) {
                System.out.println("退勤");
                pull_request.setEnd_date(currentTime);
            }

            if(dayOfWeek==JpaConst.DayOfWeek.SATURDAY.ordinal()||dayOfWeek==JpaConst.DayOfWeek.SUNDAY.ordinal())
            {
                pull_request.setWork_holiday_flg(JpaConst.FLG_TRUE);
            }
            else
            {
                pull_request.setWork_holiday_flg(JpaConst.FLG_FALSE);
            }

            pull_request.setInp_date(currentTime);
            pull_request.setInp_user(login_user_id.toString());
            pull_request.setUpd_date(currentTime);
            pull_request.setUpd_user(login_user_id.toString());
            pull_request.setDel_flg(JpaConst.FLG_FALSE);

            em.persist(pull_request);
            em.getTransaction().commit();
        }


        em.close();
        response.sendRedirect(request.getContextPath() + "/main_menu");
    }

}
