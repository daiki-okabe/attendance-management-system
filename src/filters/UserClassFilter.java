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

import constants.JpaConst;
import models.User;
import utils.DBUtil;

/**
 * Servlet Filter implementation class UserClassFilter
 */
@WebFilter("/*")
public class UserClassFilter implements Filter {

    /**
     * Default constructor.
     */
    public UserClassFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) request).getServletPath();
        boolean adminOnly=false;

        for(JpaConst.adminOnlyMenu menu : JpaConst.adminOnlyMenu.values())
        {
            if (servletPath.matches("/"+menu.toString()) )
            {
                adminOnly=true;
                break;
            }
        }

        if(!adminOnly)
        {
            //次のフィルタまたはサーブレットを呼び出し
            chain.doFilter(request, response);
            return;
        }

            HttpSession session = ((HttpServletRequest) request).getSession();

            //セッションからログインしている従業員の情報を取得
            String login_user = (String) session.getAttribute("login_user");
            if (login_user == null ) {
                 chain.doFilter(request, response);
                 return;
            }
            else
            {
                EntityManager em = DBUtil.createEntityManager();
                List<User> users = em.createNamedQuery("selectName", User.class).setParameter("name",login_user).getResultList();

                if(users.get(0).getUser_role()==JpaConst.ROLE_ADMIN)
                {
                    //次のフィルタまたはサーブレットを呼び出し
                    System.out.println("管理者である");
                    chain.doFilter(request, response);
                    return;
                }
            }
            System.out.println("管理者ではない");
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
