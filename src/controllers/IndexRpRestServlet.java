package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.JpaConst;
import models.Request;
import models.Request_Rest;
import utils.DBUtil;

/**
 * Servlet implementation class RequestPaperRestServlet
 */
@WebServlet("/rp_rest_index")
public class IndexRpRestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexRpRestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("last_page")=="/req_paper_list")
        {
            request.setAttribute("user_type", JpaConst.requestState.CREATE.ordinal());
        }
        else
        {
            request.setAttribute("user_type", JpaConst.requestState.VIEW.ordinal());
            if(request.getSession().getAttribute("edited_data_id")!=null)
            {
                EntityManager em = DBUtil.createEntityManager();
                List<Request_Rest> rr = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_RequestId",Request_Rest.class).setParameter("id",request.getSession().getAttribute("edited_data_id")).getResultList();
                request.getSession().removeAttribute("created_data_id");

                request.setAttribute("user_id", rr.get(0).getUser_id());
                request.setAttribute("user_name", rr.get(0).getUser_name());
                request.setAttribute("from", rr.get(0).getFrom_date());
                request.setAttribute("to", rr.get(0).getTo_date());
                request.setAttribute("rest_class", rr.get(0).getRest_class());
                request.setAttribute("comment", rr.get(0).getComment());
                request.setAttribute("return_reason", rr.get(0).getReturn_reason());
            }
        }



        System.out.println("get:"+request.getSession().getAttribute("edited_data_id"));
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/rp_rest.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("post");
        Integer request_id=Integer.parseInt(request.getParameter("request_id"));
        System.out.println("id;"+request_id);

        EntityManager em = DBUtil.createEntityManager();
        List<Request_Rest> rr = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_RequestId",Request_Rest.class).setParameter("id", request_id).getResultList();
        List<Request> r = (List<Request>)em.createNamedQuery("selectRequest_RequestId",Request.class).setParameter("id",request_id).getResultList();


        request.setAttribute("request_id", request_id);
        request.setAttribute("user_id", rr.get(0).getUser_id());
        request.setAttribute("user_name", rr.get(0).getUser_name());
        request.setAttribute("from", rr.get(0).getFrom_date());
        request.setAttribute("to", rr.get(0).getTo_date());
        request.setAttribute("rest_class", rr.get(0).getRest_class());
        request.setAttribute("comment", rr.get(0).getComment());
        request.setAttribute("return_reason", rr.get(0).getReturn_reason());
        request.setAttribute("redo_flg", r.get(0).getAgain_flg());
        System.out.println("get(0).getAgain_flg():"+r.get(0).getAgain_flg());

        if(Integer.parseInt(request.getParameter("type"))==JpaConst.requestState.VIEW.ordinal())
        {
            request.setAttribute("user_type", JpaConst.requestState.VIEW.ordinal());
        }
        else if(Integer.parseInt(request.getParameter("type"))==JpaConst.requestState.APPLOVAL.ordinal())
        {
            request.setAttribute("user_type", JpaConst.requestState.APPLOVAL.ordinal());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/rp_rest.jsp");
        rd.forward(request, response);
    }

}
