package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Paper;
import models.Request;
import utils.DBUtil;

/**
 * Servlet implementation class MasterMentenanceListServlet
 */
@WebServlet("/req_paper_list")
public class ReqestPaperListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReqestPaperListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer login_user_id = Integer.parseInt( session.getAttribute("login_user_id").toString());
        EntityManager em = DBUtil.createEntityManager();
        List<Request> RequestList = (List<Request>)em.createNamedQuery("selectRequest_UserId",Request.class).setParameter("id",login_user_id).getResultList();
        List<Paper> SendPaperNameList = new ArrayList<Paper>();

        for (int i=0; i<RequestList.size();i++) {
            List<Paper> temp=(List<Paper>)em.createNamedQuery("selectPaper_PaperId",Paper.class).setParameter("id",RequestList.get(i).getPaper_id()).getResultList();
            SendPaperNameList.add(temp.get(0)) ;
        }

        request.setAttribute("request_list", RequestList);
        request.setAttribute("send_paper_name_list", SendPaperNameList);
        System.out.println("RequestList.size():"+RequestList.size());
        System.out.println(RequestList.get(0).getInp_date());
        System.out.println("SendPaperNameList.size():"+SendPaperNameList.size());


        request.getSession().setAttribute("last_page", "/req_paper_list");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/paper_list.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
