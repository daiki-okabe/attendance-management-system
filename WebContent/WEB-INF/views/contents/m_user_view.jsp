<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>ユーザー一覧</h2>
        <div id="master_maintenance">
            <table id="editable_view">
               <tr>
                    <th >
                         <table>
                             <tr >
                                 <th style="width:30px"id="solid_border">ID</th>
                                 <th style="width:123px"id="solid_border"> ユーザー名<th>
                                 <th style="width:99px"id="solid_border">所属部署ID</th>
                                 <th style="width:83px"id="solid_border">権限</th>
                                 <th style="width:98px"id="solid_border">ユーザー区分</th>
                                 <th style="width:123px"id="solid_border">パスワード</th>
                                 <th style="width:40px"></th>
                                 <th style="width:40px"></th>
                             </tr>
                          </table>
                      </th>
               </tr>
            <c:forEach var="users" items="${users}">
               <tr>
                   <td valign="top">
                        <form method="POST" action="${pageContext.request.contextPath}/m_user_upd_del?id=${users.user_id}">
                            <table>
                             <tr>
                                 <th style="width:30px" id="solid_border"><label  for="USER_ID">${users.user_id}　</label> <input type="hidden" name="user_id" id="USER_ID"  value="${users.user_id}" /></th>
                                 <th style="width:80px"><input type="text" name="user_name" id="USER_NAME"   value="${users.user_name}" style="width:120px"/></th>
                                 <th style="width:96px"><input type="text" name="dept_id" id="DEPT_ID"  value="${users.dept_id}" style="width:96px"/></th>
                                 <th style="width:80px"><input type="text" name="user_role" id="USER_ROLE" value="${users.user_role}" style="width:80px"/></th>
                                 <th style="width:96px"><input type="text" name="user_class" id="USER_CLASS"  value="${users.user_class}" style="width:96px"/></th>
                                 <th style="width:120px"><input type="text" name="password" id="PASSWORD"  value="${users.password}" style="width:120px"/></th>
                                 <th style="width:40px"><input type="submit"  name="type"  value="更新"/></th>
                                 <th style="width:40px"><input type="submit"  name="type"  value="削除"/></th>
                             </tr>
                          </table>
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

   

    <div id="create_data_form">
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
     </div>
    </c:param>
</c:import>