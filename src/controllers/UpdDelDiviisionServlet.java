package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdDelUserServlet
 */
@WebServlet("/m_division_upd_del")
public class UpdDelDiviisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdDelDiviisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;

        String type=request.getParameter("type");
        System.out.println(type);

        Integer id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        request.setAttribute("id", id);

        System.out.println(request.getParameter("division_name"));

        if( type.equals("更新") )
        {

            System.out.println("更新します");
            rd = request.getRequestDispatcher("/m_division_update");
            rd.forward(request, response);

        }
        else if( type.equals("削除"))
        {
            System.out.println("削除します");
            rd = request.getRequestDispatcher("/m_division_delete");
            rd.forward(request, response);
        }
        else
        {
            System.out.println("処理失敗");
            response.sendRedirect(request.getContextPath() + "/m_division_index");
        }
    }
}
