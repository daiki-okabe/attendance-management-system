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

import constants.JpaConst;
import models.Department;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/m_department_delete")
public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDepartmentServlet() {
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

        Integer id=(Integer)(request.getAttribute("department_id")) ;
        System.out.println("department_id:"+id);
        List<Department> department = (List<Department>)em.createNamedQuery("getDepartment_deptId",Department.class).setParameter("dept_id",id).getResultList();
        Department current = em.find(Department.class,department.get(0).getId());

        current.setDel_flg(JpaConst.FLG_TRUE);
        LocalDateTime currentTime =LocalDateTime.now();     // 現在の日時を取得
        currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm" ));
        current.setUpd_date(currentTime);


        // データベースを更新
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("department_id");

        // indexページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/m_department_index");
    }

}
