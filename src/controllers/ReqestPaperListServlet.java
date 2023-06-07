package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.JpaConst;
import models.ApprovalClearance;
import models.Paper;
import models.Request;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class MasterMentenanceListServlet
 */
@WebServlet("/req_paper_list")
public class ReqestPaperListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReqestPaperListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer login_user_id = Integer.parseInt( session.getAttribute("login_user_id").toString());


        EntityManager em = DBUtil.createEntityManager();
        List<Request> myRequestList = (List<Request>)em.createNamedQuery("selectRequest_UserId",Request.class).setParameter("id",login_user_id).getResultList();
        List<Paper> SendPaperNameList = new ArrayList<Paper>();

        for (int i=0; i<myRequestList.size();i++) {
            List<Paper> temp=(List<Paper>)em.createNamedQuery("selectPaper_PaperId",Paper.class).setParameter("id",myRequestList.get(i).getPaper_id()).getResultList();
            SendPaperNameList.add(temp.get(0)) ;
        }


        List<Request> allRequestList = (List<Request>)em.createNamedQuery("getWaitingRequest",Request.class).getResultList();
        System.out.println("allRequestList.size():"+allRequestList.size());
        List<Request> approvalRequestList = new ArrayList<Request>();
        List<Paper> GetPaperNameList = new ArrayList<Paper>();

        User me=em.createNamedQuery("selectUser_UserId",User.class).setParameter("id",login_user_id).getResultList().get(0);

        for (int i=0; i<allRequestList.size();i++) {
            User requester=em.createNamedQuery("selectUser_UserId",User.class).setParameter("id",allRequestList.get(i).getUser_id()).getResultList().get(0);

            List<ApprovalClearance> paperClealance =(List<ApprovalClearance>)em.createNamedQuery("selectClearance_PaperIdAndProgress",ApprovalClearance.class)
                    .setParameter("id",allRequestList.get(i).getPaper_id())
                        .setParameter("progress",allRequestList.get(i).getProgress()).getResultList();

            List<Paper> temp=(List<Paper>)em.createNamedQuery("selectPaper_PaperId",Paper.class).setParameter("id",allRequestList.get(i).getPaper_id()).getResultList();

            System.out.println("paperClealance.size():"+paperClealance.size());
            System.out.println("allRequestList.get(i).getPaper_id():"+allRequestList.get(i).getPaper_id());
            System.out.println("allRequestList.get(i).getProgress():"+allRequestList.get(i).getProgress());

            if(requester.getDept_id().equals(me.getDept_id())) {
                if( me.getUser_rank().equals(paperClealance.get(0).getApproval_clearance())){
                    approvalRequestList.add(allRequestList.get(i));
                    GetPaperNameList.add(temp.get(0)) ;
                }
            }
            else if(paperClealance.get(i).getApproval_dept_id().equals(JpaConst.DEPT_MANAGE_1) && me.getDept_id().equals(JpaConst.DEPT_MANAGE_1)) {
                if(me.getUser_rank().equals(paperClealance.get(0).getApproval_clearance())) {
                    approvalRequestList.add(allRequestList.get(i));
                    GetPaperNameList.add(temp.get(0)) ;
                }
            }
            System.out.println("i:"+i);
        }

        request.setAttribute("request_list", myRequestList);
        request.setAttribute("get_paper_name_list", GetPaperNameList);
        request.setAttribute("send_paper_name_list", SendPaperNameList);
        request.setAttribute("approvalRequestList", approvalRequestList);
        System.out.println("RequestList.size():"+myRequestList.size());
        System.out.println("SendPaperNameList.size():"+SendPaperNameList.size());


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/contents/paper_list.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
