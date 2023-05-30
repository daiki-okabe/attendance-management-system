<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="view_area">
        <div id="top">
            <h2 >ユーザー一覧</h2>
        </div>
        <div id="master_maintenance">
            <table id="editable_view">
               <tr>
                    <th >
                         <table>
                             <tr >
                                 <th style="width:50px"id="solid_border">ID</th>
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
                                 <th style="width:50px" id="solid_border"><label  for="USER_ID">${users.user_id}　</label> <input type="hidden" name="user_id" id="USER_ID"  value="${users.user_id}" /></th>
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
            <table>
                <tr>
                    <td>
                        <label for="USER_NAME"  id="form_header">ユーザー名</label>
                    </td>
                    <td>
                         <input type="text" name="user_name" id="USER_NAME" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="DEPT_ID" id="form_header">所属部署ID</label>
                    </td>
                    <td>
                        <input type="text" name="dept_id" id="DEPT_ID" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="USER_ROLE"  id="form_header">権限</label>
                    </td>
                    <td>
                        一般:<input type="radio" name="user_role" value="0">
                        管理者:<input type="radio" name="user_role" value="1">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="USER_CLASS" id="form_header">ユーザー区分</label>
                    </td>
                    <td>
                        社員:<input type="radio" name="user_class" value="1">
                        契約社員:<input type="radio" name="user_class" value="2">
                        協力会社社員:<input type="radio" name="user_class" value="3">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="PASSWORD" id="form_header">パスワード</label>
                    </td>
                    <td>
                        <input type="text" name="password" id="PASSWORD" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit">新規データとして登録</button>
                    </td>
                </tr>
            </table>
        </form>


    </div>
     </div>
     </div>
    </c:param>
</c:import>