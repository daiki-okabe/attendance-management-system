<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <div id="header_menu">
                <div id="app_title">
                <c:if test="${login_user == null}">
                    <a>勤怠管理システム</a>
                </c:if>
                <c:if test="${login_user != null}">
                    <a href="<c:url value='/main_menu' />">勤怠管理システム</a>
                </c:if>
                </div>

                <c:if test="${login_user != null}">
                    <div id="view_menu">
                        <a href="<c:url value='/attendance_edit_index'/>">勤務実績</a>
                            <c:if test="${login_user_role == 1}">
                                <a href="<c:url value='/m_user_index' />">マスタメンテ</a>
                            </c:if>
                    </div>
                    <div id="login_user">
                        ログインユーザー：<c:out value="${login_user}" />
                    </div>
                    <div id="logout">
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
               </c:if>
            </div>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Okabe.</div>
    </div>
</body>
</html>