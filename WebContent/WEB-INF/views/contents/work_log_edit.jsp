<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
  $( function() {
      $( "#datepicker" ).datepicker({
          dateFormat: 'yy/mm/dd',
              changeYear: true,
              changeMonth: true,
              onSelect: function (dateText, inst) {
                    // 任意のイベントを書く
                    $("#selected_date").val(dateText);
                    $("#post_selected_date").submit();
                  }
  } );});
  </script>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="view_area">
            <div id="top">
                <div>
                    <form method="POST" action="${pageContext.request.contextPath}/work_log_edit">
                             <input type="submit"  name="type"  value=&lt; id="header_arrow" />
                             <div id="header_date">${today}</div>
                             <input type="hidden"  name="today"  value="${today}" id="current_date"/>
                             <input type="submit"  name="type"  value=&gt; id="header_arrow"/>
                    </form>
                </div><br>
                <div id="header_section">${division} ${department}</div>
                <div id="header_id">ID:${user_id}</div>
                <div id="header_name">${user_name}</div>
            </div>

            <div id="main">
                <div id="calendar">
                         <form method="POST" action="${pageContext.request.contextPath}/work_log_edit" id="post_selected_date">
                             <div id="datepicker"></div>
                             <input type="hidden" name="selected_date"  id="selected_date" >
                         </form>
                </div>
                <div id="create_data_form">
                    <form method="POST" action="${pageContext.request.contextPath}/work_log_edit">
                        <table>
                            <tr>
                                <td id="stats_header">勤怠データ編集</td>
                            </tr>
                             <tr>
                                 <td>
                                     <div id="form_header">
                                         <label>出勤/休暇</label>
                                    </div>
                                     <div id="form_input">
                                     <select name="work_type"  id="work_type_list">
                                         <option value=""  disabled selected>${work_class}</option>
                                        <option value="1">出勤</option>
                                        <option value="2">有給休暇</option>
                                        <option value="3">欠勤</option>
                                        <option value="4">休職</option>
                                    </select>
                                    </div>
                                 </td>
                             </tr>
                             <tr>
                                 <td>
                                     <div id="form_header">
                                         <label >出退勤時刻</label>
                                     </div>
                                     <div id="form_input">
                                         <input type="time" name="start_date"  value="${start_date}" />
                                         <label >~</label>
                                         <input type="time" name="end_date" value="${end_date}"/>
                                    </div>
                                 </td>
                             <tr/>
                             <tr>
                                 <td>
                                     <div id="form_header"></div>
                                     <div id="form_input">
                                         <input type="hidden"  name="today"  value="${today}" id="current_date"/>
                                         <input type="submit" name="type" value="登録" class="btn btn--green"/>
                                     </div>
                                 </td>
                             <tr/>
                          </table>
                    </form>
                </div>

                <div id="stats">
                    <div id="stats_wrap">
                        <div id="stats_header">月次稼働状況(${stats_year}/${stats_month})</div>
                        <table id="stats_table">
                            <tr><td id="stats_view_header">・累計勤務時間</td>			<td id="stats_view_output">${worked_hour_stats}</td></tr>
                            <tr><td id="stats_view_header">・累計残業時間</td>			<td id="stats_view_output">${aditional_worked_hour_stats}</td></tr>
                            <tr><td id="stats_view_header">・所定労働時間</td>			<td id="stats_view_output">${default_work_hour}</td></tr>
                            <tr><td id="stats_view_header">・有給休暇消化日数</td>	<td id="stats_view_output">${used_horiday_counts}</td></tr>
                            <tr><td id="stats_view_header">・休日稼働日数</td>			<td id="stats_view_output">${worked_holiday_count}</td></tr>
                        </table>
                    </div>
                </div>
            </div>

            <div id="bottom">

            </div>
        </div>
    </c:param>
</c:import>