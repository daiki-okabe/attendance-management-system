<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String login_user = (String)request.getSession().getAttribute("login_user"); %>
<% Integer login_user_role = (Integer)request.getSession().getAttribute("login_user_role"); %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="勤怠管理システム" /></title>
    <link rel="stylesheet" href="<c:url value='/CSS/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/CSS/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
                <div id="header_left">

<p id="app_title">
                        <c:if test="${login_user == null}">
                            <a>勤怠管理システム</a>
                        </c:if>
                        <c:if test="${login_user != null}">
                            <a href="<c:url value='/main_menu' />">勤怠管理システム</a>
                        </c:if>
                    </p>

                    <c:if test="${login_user != null}">
                        <p id="view_menu">
                            <a href="<c:url value='/work_log_edit'/>">勤務実績</a>
                            <a href="<c:url value='/req_paper_list'/>">各種申請</a>
                                <c:if test="${login_user_role == 2}">
                                    <a href="<c:url value='/master_mentenance_list' />">マスタメンテ</a>
                                </c:if>
                           </p>
                   </c:if>
               </div>


                <p id="header_right">
                    <c:if test="${login_user != null}">
                        <a id="login_user">ログインユーザー：<c:out value="${login_user}" /></a>
                        <a id="logout" href="<c:url value='/logout' />">ログアウト</a>
                   </c:if>
                </p>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Okabe.</div>
    </div>
</body>
</html>