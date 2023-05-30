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

import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/m_division_index")
public class IndexDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexDivisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Division> divisions = em.createNamedQuery("getAllDivision", Division.class).getResultList();
        request.getSession().setAttribute("need_login",true);

        em.close();

        request.setAttribute("divisions", divisions);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/m_division_view.jsp");
        rd.forward(request, response);
    }
}
