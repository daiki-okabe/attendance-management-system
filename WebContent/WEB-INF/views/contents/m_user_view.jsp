<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>ユーザー一覧</h2>
        <div style="height:400px; width:720px; overflow-y:scroll;">

            <table>
               <tr>
                    <th >ID　 ユーザー名   所属部署ID 権限　　　   ユーザー区分 パスワード　　　　　　　　　　  </th>
               </tr>
            <c:forEach var="users" items="${users}">
               <tr>
                   <td>
                        <form method="POST" action="${pageContext.request.contextPath}/m_user_upd_del?id=${users.user_id}">
                               <label  for="USER_ID">${users.user_id}　</label> <input type="hidden" name="user_id" id="USER_ID"  value="${users.user_id}" />
                               <input type="text" name="user_name" id="USER_NAME"  style="width:80px"  value="${users.user_name}" />
                               <input type="text" name="dept_id" id="DEPT_ID" style="width:80px" value="${users.dept_id}" />
                               <input type="text" name="user_role" id="USER_ROLE"  style="width:80px"  value="${users.user_role}" />
                               <input type="text" name="user_class" id="USER_CLASS" style="width:80px"  value="${users.user_class}" />
                               <input type="text" name="password" id="PASSWORD" style="width:120px"   value="${users.password}" />
                               <input type="submit"  name="type"  value="更新"/>
                               <input type="submit"  name="type"  value="削除"/>
                        </form>
                   </td>
               </tr>
            </c:forEach>
            </table>
        <script>
        function confirmUpdate() {
                document.forms[1].submit();
        }
        </script>

    </div>

    <div >
    <form method="POST" action="${pageContext.request.contextPath}/m_user_create">
        <label for="USER_NAME">ユーザー名　</label> <input type="text" name="user_name" id="USER_NAME" />
        <br /><br />

        <label for="DEPT_ID">所属部署ID　</label> <input type="text" name="dept_id" id="DEPT_ID" />
        <br /><br />

        <label for="USER_ROLE" >権限　　　　</label>       	一般:<input type="radio" name="user_role" value="0">
                                                                                        管理者:<input type="radio" name="user_role" value="1">
        <br /><br />

        <label for="USER_CLASS">ユーザー区分</label> 	社員:<input type="radio" name="user_class" value="1">
                                                                                                    契約社員:<input type="radio" name="user_class" value="2">
                                                                                                    協力会社社員:<input type="radio" name="user_class" value="3">
        <br /><br />

        <label for="PASSWORD">パスワード　</label> <input type="text" name="password" id="PASSWORD" />
        <br /><br />

        <button type="submit">新規データとして登録</button>
        </form>
    </div>
    </c:param>
</c:import>