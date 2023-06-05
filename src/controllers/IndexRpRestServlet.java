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

            if(request.getSession().getAttribute("created_data_id")!=null)
            {
                EntityManager em = DBUtil.createEntityManager();
                List<Request_Rest> data = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_RequestId",Request_Rest.class).setParameter("id",request.getSession().getAttribute("created_data_id")).getResultList();
                request.getSession().removeAttribute("created_data_id");

                request.setAttribute("user_id", data.get(0).getUser_id());
                request.setAttribute("user_name", data.get(0).getUser_name());
                request.setAttribute("from", data.get(0).getFrom_date());
                request.setAttribute("to", data.get(0).getTo_date());
                request.setAttribute("rest_class", data.get(0).getRest_class());
                request.setAttribute("comment", data.get(0).getComment());
                request.setAttribute("return_reason", data.get(0).getReturn_reason());
            }


        }
        request.setAttribute("redo_flg", true);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/rp_rest.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        List<Request_Rest> data = (List<Request_Rest>)em.createNamedQuery("selectRequestRest_RequestId",Request_Rest.class).setParameter("id", Integer.parseInt(request.getParameter("request_id"))).getResultList();

        request.setAttribute("user_id", data.get(0).getUser_id());
        request.setAttribute("user_name", data.get(0).getUser_name());
        request.setAttribute("from", data.get(0).getFrom_date());
        request.setAttribute("to", data.get(0).getTo_date());
        request.setAttribute("rest_class", data.get(0).getRest_class());
        request.setAttribute("comment", data.get(0).getComment());
        request.setAttribute("return_reason", data.get(0).getReturn_reason());
        request.setAttribute("user_type", JpaConst.requestState.VIEW.ordinal());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/rp_rest.jsp");
        rd.forward(request, response);
    }

}
