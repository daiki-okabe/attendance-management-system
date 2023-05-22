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

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/m_user_index")
public class IndexUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<User> users = em.createNamedQuery("getAllUsers", User.class).getResultList();

        em.close();

        request.setAttribute("users", users);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/m_user_view.jsp");
        rd.forward(request, response);
    }
}
