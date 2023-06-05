package controllers;

import java.io.IOException;
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
import models.ApprovalClearance;
import models.Request;
import models.Request_Rest;
import utils.DBUtil;

/**
 * Servlet implementation class CreateRpRestRequestServlet
 */
@WebServlet("/rp_rest_update")
public class UpdateRpRestRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRpRestRequestServlet() {
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
         Integer login_user = (Integer)session.getAttribute("login_user_id");
         EntityManager em = DBUtil.createEntityManager();
         em.getTransaction().begin();

         Integer request_id=Integer.parseInt(request.getParameter("request_id"));

         String approval_type= request.getParameter("approval_type");

         List<Request_Rest> data = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_RequestId",Request_Rest.class).setParameter("id",request_id).getResultList();
         Request_Rest rr = data.get(0);

         rr.setRequest_id(request_id);

         String return_reason= request.getParameter("return_reason");
         rr.setReturn_reason(return_reason);
         System.out.println(return_reason);

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

         request.getSession().setAttribute("edited_data_id", request_id);

         session.setAttribute("request_id", request_id);
         session.setAttribute("user_id",login_user);
         session.setAttribute("last_page", "/rp_rest_create");

         Request r=(Request) em.createNamedQuery("selectRequest_RequestId",Request.class).setParameter("id",request_id).getResultList().get(0);

         r.setRequest_id(request_id);
         r.setUser_id(login_user);
         r.setPaper_id(JpaConst.PAPER_ID_REST);

         if(approval_type.equals("承認")) {
             r.setAgain_flg(JpaConst.FLG_FALSE);
             r.setProgress( r.getProgress()+1);

             if(em.createNamedQuery("selectClearance_PaperIdAndProgress",ApprovalClearance.class).setParameter("id",request_id).setParameter("progress",r.getProgress()).getResultList().size()==0){
                 r.setOk_flg(JpaConst.FLG_TRUE);
             }
             else{
                 r.setOk_flg(JpaConst.FLG_FALSE);
             }
         }
         else {
             r.setAgain_flg(JpaConst.FLG_TRUE);
             r.setProgress(JpaConst.FLG_FALSE);
         }

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
