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
import models.Request;
import models.Request_Rest;
import utils.DBUtil;

/**
 * Servlet implementation class CreateRpRestRequestServlet
 */
@WebServlet("/rp_rest_create")
public class CreateRpRestRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRpRestRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         HttpSession session = ((HttpServletRequest) request).getSession();
         String login_user_name = session.getAttribute("login_user").toString();
         EntityManager em = DBUtil.createEntityManager();
         em.getTransaction().begin();

         Request_Rest rr = new Request_Rest();

         if(request.getParameter("user_id").toString().isBlank()||request.getParameter("user_name").toString().isBlank()||
                 request.getParameter("from").toString().isBlank()||request.getParameter("to").toString().isBlank()||
                 request.getParameter("rest_class").toString().isBlank()||request.getParameter("comment").toString().isBlank())
         {
             request.getSession().setAttribute("last_page","/req_paper_list");
             response.sendRedirect(request.getContextPath() + "/rp_rest_index");
             return;
         }

         Integer user_id=Integer.parseInt(request.getParameter("user_id"));
         List<Request_Rest> data = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_UserId",Request_Rest.class).setParameter("id",user_id).getResultList();
         Integer request_id=Integer.parseInt((user_id+ String.format("%04d", data.size())));

         rr.setUser_id(user_id);
         System.out.println(user_id);

         String user_name = request.getParameter("user_name");
         rr.setRequest_id(request_id);
         rr.setRequest_rest_id(request_id);

         rr.setUser_name(user_name);
         System.out.println(user_name);


         LocalDate from_date =LocalDate.parse( request.getParameter("from"));
         from_date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd" ));
         rr.setFrom_date(from_date);
         System.out.println(from_date);

         LocalDate to_date =LocalDate.parse( request.getParameter("to"));
         to_date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd" ));
         rr.setTo_date(to_date);
         System.out.println(to_date);

         Integer rest_class=Integer.parseInt(request.getParameter("rest_class"));
         rr.setRest_class(rest_class);

         String comment = request.getParameter("comment");
         rr.setComment(comment);
         System.out.println(comment);

         Integer del_flg=JpaConst.FLG_FALSE;
         rr.setDel_flg(del_flg);

         LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
         currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
         rr.setInp_date(currentTime);
         rr.setUpd_date(currentTime);

         rr.setInp_user(login_user_name);
         rr.setUpd_user(login_user_name);

         // データベースに保存
         em.persist(rr);

         request.getSession().setAttribute("edited_data_id", rr.getRequest_rest_id());

         session.setAttribute("request_id", request_id);
         session.setAttribute("user_id",user_id);

         Request r=new Request();

         r.setRequest_id(request_id);
         r.setUser_id(user_id);
         r.setPaper_id(JpaConst.PAPER_ID_REST);
         r.setProgress(JpaConst.FLG_FALSE);
         r.setAgain_flg(JpaConst.FLG_FALSE);
         r.setOk_flg(JpaConst.FLG_FALSE);
         r.setInp_date(currentTime);
         r.setInp_user(login_user_name);
         r.setUpd_date(currentTime);
         r.setUpd_user(login_user_name);
         r.setDel_flg(del_flg);

         em.merge(r);
         em.getTransaction().commit();
         em.close();


         response.sendRedirect(request.getContextPath() + "/rp_rest_index");
    }

}
