<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div id="view_area">
        <div id="top">
            <h2 >部署一覧</h2>
        </div>
        <div id="master_maintenance">
            <table id="editable_view">
               <tr>
                    <th >
                         <table>
                             <tr >
                                 <th style="width:80px"id="solid_border">事業部ID</th>
                                 <th style="width:80px"id="solid_border">部署ID</th>
                                 <th style="width:123px"id="solid_border"> 部署名<th>
                                 <th style="width:40px"></th>
                                 <th style="width:40px"></th>
                             </tr>
                          </table>
                      </th>
               </tr>
            <c:forEach var="departments" items="${departments}">
               <tr>
                   <td valign="top">
                        <form method="POST" action="${pageContext.request.contextPath}/m_department_upd_del?id=${departments.department_id}">
                            <table>
                             <tr>
                                 <th style="width:80px" id="solid_border"><label  for="DIVISION_ID">${departments.division_id}</label>
                                     <input type="hidden" name="division_id" id="DIVISION_ID"  value="${departments.division_id}"/>
                                 </th>
                                 <th style="width:80px" id="solid_border"><label  for="DEPARTMENT_ID">${departments.department_id}
                                     </label> <input type="hidden" name="department_id" id="DEPARTMENT_ID"  value="${departments.department_id}" />
                                 </th>
                                 <th style="width:80px">
                                     <input type="text" name="department_name" id="DEPARTMENT_NAME"   value="${departments.department_name}" style="width:120px"/>
                                 </th>
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
            <form method="POST" action="${pageContext.request.contextPath}/m_department_create">
                <table>
                    <tr>
                        <td>
                            <label for="DIVISION_ID"  id="form_header">事業部ID</label>
                        </td>
                        <td>
                             <input type="text" name="division_id" id="DIVISION_ID" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="DEPARTMENT_ID" id="form_header">部署ID</label>
                        </td>
                        <td>
                            <input type="text" name="department_id" id="DEPARTMENT_ID" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="DEPARTMENT_NAME"  id="form_header">部署名</label>
                        </td>
                        <td>
                            <input type="text" name="department_name" id="DEPARTMENT_NAME" />
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