package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
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
 * Servlet implementation class CreateUpdateWorkLogServlet
 */
@WebServlet("/create_update_work_log")
public class CreateUpdateWorkLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUpdateWorkLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Attendance pull_request=new Attendance();
        Integer login_user_id = (Integer)session.getAttribute("login_user_id");
        EntityManager em = DBUtil.createEntityManager();

        Integer work_class = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime  currentTime = LocalDateTime.parse( LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss" )),dtf);
        LocalDateTime start_date= (LocalDateTime)( request.getSession().getAttribute("start_date"));
        LocalDateTime end_date= (LocalDateTime)( request.getSession().getAttribute("end_date"));
        System.out.println("start_date:"+start_date);
        System.out.println("end_date:"+end_date);
        LocalDate today=(LocalDate)( request.getSession().getAttribute("today"));
        System.out.println("today:"+today);
        Integer dayOfWeek=today.getDayOfWeek().getValue();

        em.getTransaction().begin();

        List<Attendance> attendances = (List<Attendance>)em.createNamedQuery("selectAttendance_Today",Attendance.class).setParameter("id",login_user_id).setParameter("date",today).getResultList();

        System.out.println("attendances.size():"+attendances.size());
        if(attendances.size()>0)
        {
            System.out.println("更新");

            pull_request= em.find(Attendance.class,attendances.get(0).getAttendance_id() );

            if(request.getSession().getAttribute("work_type")!=null)
            {
                work_class=Integer.parseInt( request.getSession().getAttribute("work_type").toString());
            }

            if(work_class!=null) {
                pull_request.setWork_class(work_class);
                pull_request.setUpd_date(currentTime);
                pull_request.setUpd_user(login_user_id.toString());
            }

            if(start_date!=null) {
                pull_request.setStart_date(start_date);
                pull_request.setUpd_date(currentTime);
                pull_request.setUpd_user(login_user_id.toString());
            }

            if(end_date!=null) {
                pull_request.setEnd_date(end_date);
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

            if(pull_request.getStart_date()==null) {
                pull_request.setStart_date(start_date);
            }

            if(pull_request.getEnd_date()==null) {
                pull_request.setEnd_date(end_date);
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

        response.sendRedirect(request.getContextPath() + "/work_log_edit");
        return;
    }

}
