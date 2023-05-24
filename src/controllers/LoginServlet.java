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
import models.User;
import utils.DBUtil;
import utils.EncryptUtil;
import utils.StringCheck;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Loading Top Page...");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        System.out.println(request.getParameter("user_id"));
        System.out.println(request.getParameter("password"));
        if(!StringCheck.IsBlankOrNotNumelic(request.getParameter("user_id"))  && !request.getParameter("password").isBlank())
        {
            Integer user_id=Integer.parseInt( request.getParameter("user_id"));
            String password=EncryptUtil.getPasswordEncrypt(request.getParameter("password"), JpaConst.PEPPER);
            List<User> users = em.createNamedQuery("loginQuely", User.class).setParameter("id",user_id).setParameter("password",password).getResultList();

                request.getSession().setAttribute("need_login",false);

               em.close();
               System.out.println("size:"+users.size());

               if(users.size()==1)
               {
                   System.out.println("ログイン成功");
                   request.getSession().setAttribute("login_user",users.get(0).getUser_name());
                   response.sendRedirect(request.getContextPath() + "/m_user_index");
               }
               else
               {
                   System.out.println("ログイン失敗");
                   response.sendRedirect(request.getContextPath() + "/login.jsp");
               }
        }
        else
        {
            System.out.println("入力エラー");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
