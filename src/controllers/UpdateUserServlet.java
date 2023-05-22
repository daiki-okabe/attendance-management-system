package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/m_user_update")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = new User();

        String id=request.getParameter("user_id");
        System.out.println(id);

        String user_name = request.getParameter("user_name");
        u.setUser_name(user_name);
        System.out.println(user_name);

        Integer dept_id = Integer.parseInt(request.getParameter("dept_id")) ;
        u.setDept_id(dept_id);
        System.out.println(dept_id);

       Integer user_role =  Integer.parseInt(request.getParameter("user_role"));
       u.setUser_role(user_role);

        Integer user_class =  Integer.parseInt(request.getParameter("user_class"));
        u.setUser_class(user_class);

        String password = request.getParameter("password"+id);
        u.setPassword(password);

        LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
        currentTime.format(DateTimeFormatter.ofPattern("yyyy/-MM/dd HH:mm" ));
        u.setUpd_date(currentTime);

        System.out.println(user_class);
        System.out.println(password);

        // データベースを更新
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("message_id");

        // indexページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/index");
    }

}
