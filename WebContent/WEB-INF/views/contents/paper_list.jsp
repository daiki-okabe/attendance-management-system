<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="view_area">
            <div id="request_form_list_wrap">
                <table>
                    <tr>
                        <th>申請フォーム一覧</th>
                    </tr>
                    <tr>
                        <th>
                            <table id="request_form_list">
                                <tr>
                                    <td>
                                        <a href="<c:url value='/rp_rest_index'/>" >	休暇申請書</a>
                                    </td>
                                </tr>
                            </table>
                        </th>
                    </tr>
                </table>
            </div>
            <div id="request_form_list_wrap">
                <table>
                    <tr>
                        <th>申請済フォーム一覧</th>
                    </tr>
                    <tr>
                        <th>
                            <table id="request_form_list">
                                <c:forEach var="request_list" items="${request_list}" varStatus="status">
                                   <tr>
                                       <td valign="top">
                                          <form name="send_form_view"  action="${pageContext.request.contextPath}${send_paper_name_list[status.index].url}" method="POST">
                                               <table>
                                                    <tr>
                                                        <th>
                                                            <c:if test="${request_list.ok_flg==0}">
                                                                <fmt:parseDate value="${request_list.inp_date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                                                <input  type="hidden" name="request_id" value="${request_list.request_id}" ></input>
                                                                <input  type="hidden" name="type" value="2" ></input>
                                                                <input  type="submit"  value="${send_paper_name_list[status.index].paper_name}_<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd" />"  ></input>
                                                           </c:if>
                                                        </th>
                                                    </tr>
                                               </table>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </th>
                    </tr>
                </table>
            </div>
            <div id="request_form_list_wrap">
                <table>
                    <tr>
                        <th>申請完了フォーム一覧</th>
                    </tr>
                    <tr>
                        <th>
                            <table id="request_form_list">
                                <c:forEach var="request_list" items="${request_list}" varStatus="status">
                                   <tr>
                                       <td valign="top">
                                          <form name="send_form_view"  action="${pageContext.request.contextPath}${send_paper_name_list[status.index].url}" method="POST">
                                               <table>
                                                    <tr>
                                                        <th>
                                                            <c:if test="${request_list.ok_flg==1}">
                                                                <fmt:parseDate value="${request_list.inp_date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                                                <input  type="hidden" name="request_id" value="${request_list.request_id}" ></input>
                                                                <input  type="hidden" name="type" value="2" ></input>
                                                                <input  type="submit"  value="${send_paper_name_list[status.index].paper_name}_<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd" />" ></input>
                                                            </c:if>
                                                        </th>
                                                    </tr>
                                               </table>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </th>
                    </tr>
                </table>
            </div>
            <div id="request_form_list_wrap">
                <table>
                    <tr>
                        <th>差戻しフォーム一覧</th>
                    </tr>
                    <tr>
                        <th>
                            <table id="request_form_list">
                                <c:forEach var="request_list" items="${request_list}" varStatus="status">
                                   <tr>
                                       <td valign="top">
                                          <form name="send_form_view"  action="${pageContext.request.contextPath}${send_paper_name_list[status.index].url}" method="POST">
                                               <table>
                                                    <tr>
                                                        <th>
                                                            <c:if test="${request_list.again_flg==1}">
                                                                <fmt:parseDate value="${request_list.inp_date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                                                <input  type="hidden" name="request_id" value="${request_list.request_id}" ></input>
                                                                <input  type="hidden" name="type" value="3" ></input>
                                                                <input  type="submit"  value="${send_paper_name_list[status.index].paper_name}_<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd" />" ></input>
                                                            </c:if>
                                                        </th>
                                                    </tr>
                                               </table>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </th>
                    </tr>
                </table>
            </div>
            <div id="request_form_list_wrap">
                    <table>
                        <tr>
                            <th>承認待ちフォーム一覧</th>
                        </tr>
                        <tr>
                            <th>
                                <table id="request_form_list">
                                    <c:forEach var="request_list" items="${approvalRequestList}" varStatus="status">
                                       <tr>
                                           <td valign="top">
                                              <form name="send_form_view"  action="${pageContext.request.contextPath}${get_paper_name_list[status.index].url}" method="POST">
                                                   <table>
                                                        <tr>
                                                            <th>
                                                                <c:if test="${request_list.ok_flg==0}">
                                                                    <fmt:parseDate value="${request_list.inp_date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                                                    <input  type="hidden" name="request_id" value="${request_list.request_id}" ></input>
                                                                    <input  type="hidden" name="type" value="1" ></input>
                                                                    <input  type="submit"  value="${get_paper_name_list[status.index].paper_name}_<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd" />"  ></input>
                                                                </c:if>
                                                            </th>
                                                        </tr>
                                                   </table>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </th>
                        </tr>
                    </table>
                </div>
            </div>
    </c:param>
</c:import>