<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>ユーザー一覧</h2>
        <div style="height:400px; width:700px; overflow-y:scroll;">
        <form method="POST" >
            <table>
               <tr>
                    <th>ユーザーID</th> <th>ユーザー名</th> <th>所属部署ID</th> <th>権限</th> <th>ユーザー区分	</th> <th>パスワード	</th>
               </tr>
                <c:forEach var="users" items="${users}">

                <tr>
                    <td><input type="text" name="user_id${users.user_id}" id="USER_ID"  style="width:80px" value="${users.user_id}"/></td>
                        <td><input type="text" name="user_name${users.user_id}" id="USER_NAME"  style="width:80px"  value="${users.user_name}" /></td>
                            <td><input type="text" name="dept_id${users.user_id}" id="DEPT_ID" style="width:80px" value="${users.dept_id}" /></td>
                                <td><input type="text" name="user_role"${users.user_id} id="USER_ROLE"  style="width:80px"  value="${users.user_role}" /></td>
                                    <td><input type="text" name="user_class${users.user_id}" id="USER_CLASS" style="width:80px"  value="${users.user_class}" /></td>
                                        <td><input type="text" name="password${users.user_id}" id="PASSWORD" style="width:120px"   value="${users.password}" /></td>
                                            <td><a href="${pageContext.request.contextPath}/m_user_update?user_id=${users.user_id}">更新</a></td>
                                                <td><a href="${pageContext.request.contextPath}/m_user_delete?user_id=${users.user_id}">削除</a></td>
                </tr>
                </c:forEach>
            </table>
            </form>
    </div>

    <div>
    <form method="POST" action="${pageContext.request.contextPath}/m_user_create">
        <label for="USER_NAME">ユーザー名</label> <input type="text" name="user_name" id="USER_NAME" />
        <br /><br />

        <label for="DEPT_ID">所属部署ID</label> <input type="text" name="dept_id" id="DEPT_ID" />
        <br /><br />

        <label for="USER_ROLE">権限</label>       一般:<input type="radio" name="user_role" value="0"> 管理者<input type="radio" name="user_role" value="1">
        <br /><br />

        <label for="USER_CLASS">ユーザー区分</label> 社員:<input type="radio" name="user_class" value="1"> 契約社員:<input type="radio" name="user_class" value="1"> 協力会社社員:<input type="radio" name="user_class" value="1">
        <br /><br />

        <label for="PASSWORD">パスワード</label> <input type="text" name="password" id="PASSWORD" />
        <br /><br />

        <button type="submit">新規データとして登録</button>
        </form>
    </div>
    </c:param>
</c:import>