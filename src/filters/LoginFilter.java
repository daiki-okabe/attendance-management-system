package filters;

import java.io.IOException;

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