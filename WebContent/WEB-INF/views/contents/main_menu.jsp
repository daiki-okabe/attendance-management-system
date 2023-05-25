<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div id="view_area">

        <table id="solid_border">
               <tr>
                   <th>
                            <label id="label_timestump">打刻</label>
                   </th>
               </tr>
               <tr>
                    <th>
                        <label id="main_menu_clock">${current_datetime}</label>
                    </th>
               </tr>
               <tr>
                    <th >
                        <table>
                                   <tr>
                                        <th >
                                             <form method="POST" action="${pageContext.request.contextPath}/main_menu">
                                                 <table>
                                                     <tr>
                                                         <th >
                                                                    <input type="submit"name="btn_type" value="出勤" class="btn btn--orange">
                                                         </th>
                                                         <th style="width:200;">
                                                                    <input type="submit"name="btn_type" value="退勤" class="btn btn--blue">
                                                        </th>
                                                    </tr>
                                                  </table>
                                              </form>
                                          </th>
                                   </tr>
                                   <tr>
                                           <td>
                                               <table>
                                                     <tr>
                                                         <td id="main_menu_time_stump">
                                                                    <label >${start_date}</label>
                                                         </td>
                                                         <td  id="main_menu_time_stump">
                                                                    <label>${end_date}</label>
                                                        </td>
                                                    </tr>
                                                  </table>
                                           </td>
                                   </tr>
                        </table>
                    </th>
                </tr>
                <tr>
                    <th >
                        <a href="<c:url value='/attendance_edit_index'/>" id="mainmenu_edit_link">勤怠情報を編集する</a>
                    </th>
                </tr>
        </table>
    </div>
    </c:param>
</c:import>