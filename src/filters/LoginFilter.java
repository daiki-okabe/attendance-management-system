package filters;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import utils.DBUtil;
/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) request).getServletPath();


        if (servletPath.matches("/css.*") ||servletPath.matches("/login") )
        {
            System.out.println("not need login");
        }
        else
        {
            HttpSession session = ((HttpServletRequest) request).getSession();

            //セッションからログインしている従業員の情報を取得
            String login_user = (String) session.getAttribute("login_user");

            if (login_user == null ) {
                //未ログイン

                    System.out.println("plzLogin");
                    //ログインページの表示またはログイン実行以外はログインページにリダイレクト
                    ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
                    return;
            }
            else
            {


                    //セッションにログインユーザーの権限をセット
                    EntityManager em = DBUtil.createEntityManager();
                    List<User> users = em.createNamedQuery("selectUser_UserName", User.class).setParameter("name",login_user).getResultList();
                    Integer login_user_role= users.get(0).getUser_role();
                    System.out.println(" login_user_role:" +login_user_role);
                    session.setAttribute("login_user_role", login_user_role);
                    em.close();


            }
        }

        //次のフィルタまたはサーブレットを呼び出し
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}