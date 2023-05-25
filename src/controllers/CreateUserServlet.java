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

import constants.JpaConst;
import models.User;
import utils.DBUtil;
import utils.EncryptUtil;
import utils.StringCheck;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/m_user_create")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        if( !request.getParameter("user_name").isBlank() &&
                !StringCheck.IsBlankOrNotNumelic(request.getParameter("dept_id")) &&
                    request.getParameter("user_role") != null&&
                        request.getParameter("user_class") != null &&
                            !request.getParameter("password").isBlank())
        {

        User u = new User();

        String user_name = request.getParameter("user_name");
        u.setUser_name(user_name);

        Integer dept_id = Integer.parseInt(request.getParameter("dept_id")) ;
        u.setDept_id(dept_id);

       Integer user_role =  Integer.parseInt(request.getParameter("user_role"));
       u.setUser_role(user_role);

        Integer user_class =  Integer.parseInt(request.getParameter("user_class"));
        u.setUser_class(user_class);

        String password = request.getParameter("password");
        u.setPassword(EncryptUtil.getPasswordEncrypt(password, JpaConst.PEPPER) );

        Integer del_flg=JpaConst.FLG_FALSE;
        u.setDel_flg(del_flg);

        LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
        currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
        u.setInp_date(currentTime);
        u.setUpd_date(currentTime);

        System.out.println(user_name);
        System.out.println(user_role);
        System.out.println(user_class);
        System.out.println(password);

        // データベースに保存
        em.persist(u);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(u.getUser_id()).toString());
        }
        else
        {
            System.out.println("入力エラー");
        }

        em.close();

        response.sendRedirect(request.getContextPath() + "/m_user_index");
    }

}
