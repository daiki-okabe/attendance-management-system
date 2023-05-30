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
import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/m_division_create")
public class CreateDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDivisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        if( !request.getParameter("division_id").isBlank() &&
                !request.getParameter("division_name").isBlank())
        {

            Division d = new Division();

            Integer division_id =Integer.parseInt(request.getParameter("division_id"));
            d.setDivision_id(division_id);

            String division_name = request.getParameter("division_name");
            d.setDivision_name(division_name);

            Integer del_flg=JpaConst.FLG_FALSE;
            d.setDel_flg(del_flg);

            LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
            currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
            d.setInp_date(currentTime);
            d.setUpd_date(currentTime);

            System.out.println(division_name);

            // データベースに保存
            em.persist(d);
            em.getTransaction().commit();

            // 自動採番されたIDの値を表示
            response.getWriter().append(Integer.valueOf(d.getDivision_id()).toString());
        }
        else
        {
            System.out.println("入力エラー");
        }

        em.close();

        response.sendRedirect(request.getContextPath() + "/m_division_index");
    }

}
