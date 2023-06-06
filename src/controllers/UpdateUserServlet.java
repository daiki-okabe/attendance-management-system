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

        Integer id=(Integer)(request.getAttribute("id")) ;
        System.out.println("id:"+id);
        User current = em.find(User.class,id );
        Integer login_user_id=(Integer)(request.getSession().getAttribute("login_user_id"));

            if( !request.getParameter("user_name").isBlank() &&
                    !StringCheck.IsBlankOrNotNumelic(request.getParameter("dept_id")) &&
                        !StringCheck.IsBlankOrNotNumelic(request.getParameter("user_role"))&&
                            !StringCheck.IsBlankOrNotNumelic(request.getParameter("user_rank"))&&
                                !StringCheck.IsBlankOrNotNumelic(request.getParameter("user_class")))
            {
                String user_name=request.getParameter("user_name");

                if(id.equals(login_user_id))
                {
                    request.getSession().setAttribute("login_user",user_name);
                }

                Integer dept_id=Integer.parseInt(request.getParameter("dept_id"));
                Integer user_rank=Integer.parseInt(request.getParameter("user_rank"));
                Integer user_role=Integer.parseInt(request.getParameter("user_role"));
                Integer user_class= Integer.parseInt(request.getParameter("user_class"));
                if(!request.getParameter("password").isBlank())
                {
                    String password=EncryptUtil.getPasswordEncrypt(request.getParameter("password"), JpaConst.PEPPER) ;
                    current.setPassword(password);
                    System.out.println("パスワードセット");
                }


                current.setUser_name(user_name);
                current.setDept_id(dept_id);
                current.setUser_rank(user_rank);
                current.setUser_role(user_role);
                current.setUser_class(user_class);
                LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
                currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
                current.setUpd_date(currentTime);


                // データベースを更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                System.out.println("更新しました");

                // セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("id");
            }
            else
            {
                System.out.println("更新できません");
            }




        // indexページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/m_user_index");
    }

}
