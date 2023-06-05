package utils;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.JpaConst;
import models.Request;

public class CreateRequest {
    public static void row(HttpServletRequest request)
    {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String login_user_name = session.getAttribute("login_user").toString();
        Integer user_id=Integer.parseInt(session.getAttribute("user_id").toString());
        Integer request_id=Integer.parseInt(session.getAttribute("request_id").toString());
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        Request r=new Request();
        r.setRequest_id(request_id);
        r.setUser_id(user_id);
        r.setPaper_id(JpaConst.PAPER_ID_REST);
        r.setProgress(JpaConst.FLG_FALSE);
        r.setAgain_flg(JpaConst.FLG_FALSE);
        r.setOk_flg(JpaConst.FLG_FALSE);
        r.setInp_date(LocalDateTime.now());
        r.setInp_user(login_user_name);
        r.setUpd_date(LocalDateTime.now());
        r.setUpd_user(login_user_name);

        em.persist(r);
        em.getTransaction().commit();
        em.close();
    }
}
