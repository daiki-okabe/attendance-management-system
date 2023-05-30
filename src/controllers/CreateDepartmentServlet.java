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
import models.Department;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/m_department_create")
public class CreateDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDepartmentServlet() {
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
                !request.getParameter("department_id").isBlank()&&
                    !request.getParameter("department_name").isBlank())
        {

            Department d = new Department();

            Integer division_id =Integer.parseInt(request.getParameter("division_id"));
            d.setDivision_id(division_id);

            Integer department_id =Integer.parseInt(request.getParameter("department_id"));
            d.setDepartment_id(department_id);

            String department_name = request.getParameter("department_name");
            d.setDepartment_name(department_name);

            Integer del_flg=JpaConst.FLG_FALSE;
            d.setDel_flg(del_flg);

            LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
            currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
            d.setInp_date(currentTime);
            d.setUpd_date(currentTime);

            System.out.println(department_name);

            // データベースに保存
            em.persist(d);
            em.getTransaction().commit();

            // 自動採番されたIDの値を表示
            response.getWriter().append(Integer.valueOf(d.getDepartment_id()).toString());
        }
        else
        {
            System.out.println("入力エラー");
        }

        em.close();

        response.sendRedirect(request.getContextPath() + "/m_department_index");
    }

}
