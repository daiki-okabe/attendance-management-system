<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String login_user = (String)request.getSession().getAttribute("login_user"); %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="勤怠管理システム" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/login' />">勤怠管理システム</a></h1>
                    <div>
                    <c:if test="${login_user != null}">
                        ログインユーザー：<c:out value="${login_user}" />
                       </c:if>
                                <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
            </div>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Okabe.</div>
    </div>
</body>
</html>