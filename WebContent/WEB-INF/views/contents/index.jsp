<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>ユーザー一覧</h2>
        <div style="height:400px; width:550px; overflow-y:scroll;">
            <table>
               <tr>
                    <th>ユーザーID</th> <th>ユーザー名</th> <th>所属部署ID</th> <th>権限</th> <th>ユーザー区分	</th> <th>パスワード	</th>
               </tr>
                <c:forEach var="users" items="${users}">
                <tr>
                    <td><c:out value="${users.user_id}" /></td>
                        <td><c:out value="${users.user_name}" /></td>
                            <td><c:out value="${users.dept_id}" /></td>
                                <td><c:out value="${users.user_role}" /></td>
                                    <td><c:out value="${users.user_class}" /></td>
                                        <td><c:out value="${users.password}" /></td>
                </tr>
                </c:forEach>
            </table>
    </div>

    </c:param>
</c:import>