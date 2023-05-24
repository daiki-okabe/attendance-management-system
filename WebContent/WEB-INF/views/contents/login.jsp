<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div >
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <label for="USER_ID">ユーザーID</label> <input type="text" name="user_id" id="USER_ID" />
        <br /><br />

        <label for="PASSWORD">パスワード</label> <input type="password" name="password" id="PASSWORD" />
        <br /><br />

        <button type="submit">ログイン</button>
        </form>
    </div>
    </c:param>
</c:import>