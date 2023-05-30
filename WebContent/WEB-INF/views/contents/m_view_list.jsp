<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="view_area">
                <table id="list_center">
                    <tr>
                        <td>
                            <a href="<c:url value='/m_user_index'/>" >	ユーザーマスタ</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="<c:url value='m_division_index'/>" >事業部マスタ</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="<c:url value='m_department_index'/>" >部署マスタ</a>
                        </td>
                    </tr>
                </table>
            </div>
    </c:param>
</c:import>