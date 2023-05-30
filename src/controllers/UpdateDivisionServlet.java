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

import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateDivisionServlet
 */
@WebServlet("/m_division_update")
public class UpdateDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDivisionServlet() {
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
        List<Division> division = (List<Division>)em.createNamedQuery("getDivision_divisionId",Division.class).setParameter("div_id",id).getResultList();
        Division current = em.find(Division.class,division.get(0).getId() );

            if( !request.getParameter("division_name").isBlank())
            {
                String division_name=request.getParameter("division_name");
                current.setDivision_name(division_name);

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
        response.sendRedirect(request.getContextPath() + "/m_division_index");
    }

}
