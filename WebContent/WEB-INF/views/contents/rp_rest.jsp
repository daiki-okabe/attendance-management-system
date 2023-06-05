<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="view_area">
            <div id="request_form">
                <p id="request_form_title">休暇申請書</p>

               <c:if test="${user_type==0}">
                   <form method="POST" action="${pageContext.request.contextPath}/rp_rest_create">

                        <div id="request_form_header">
                            <div id="request_form_label">申請者ID</div> <input type="text" name="user_id" id="request_form_text"  class="text_id" oninput="value = value.replace(/[^0-9]+/i,'');" maxlength="3"></input>
                            <div id="request_form_label">申請者名</div> <input type="text" name="user_name"id="request_form_text" maxlength="200"></input>
                        </div>

                        <br><br>
                        <div id="request_form_body">
                            <div id="request_form_label">期間</div>
                                <input type="date"  name="from" id="request_form_text" class="timeAndDate"></input>
                             <div id="request_form_label">～</div>
                                <input type="date" name="to" id="request_form_text" class="timeAndDate"></input>

                             <div id="request_br"></div>

                              <div id="request_form_label" >区分</div>
                                  <select name="rest_class" class="request_form_select">
                                      <option value="1">有給休暇</option>
                                      <option value="2">年休</option>
                                      <option value="3">リフレッシュ休暇</option>
                                      <option value="4">休職</option>
                                  </select>

                                 <div id="request_br"></div>

                                 <div id="request_form_label">コメント</div>
                                 <textarea  name="comment" id="request_form_text_large"  maxlength="200"></textarea>

                                 <div id="request_br"></div>

                                    <input type="submit"  value="申請" class="btn--black--small"></input>

                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                                 <div id="request_br"></div>
                        </div>
                    </form>
                </c:if>

                <c:if test="${user_type==1or user_type==2 }">
                    <div id="request_form_header">
                        <div id="request_form_label">申請者ID</div> <input type="text"  disabled id="request_form_text"  class="text_id" value = "${user_id}" ></input>
                        <div id="request_form_label">申請者名</div> <input type="text" disabled  id="request_form_text" value = "${user_name}" ></input>
                    </div>

                    <br><br>
                    <div id="request_form_body">
                        <div id="request_form_label">期間</div>
                            <input type="date"  value="${from}" id="request_form_text"  disabled class="timeAndDate"></input>
                         <div id="request_form_label">～</div>
                            <input type="date" value="${to}" id="request_form_text"  disabled class="timeAndDate"></input>

                         <div id="request_br"></div>

                         <div id="request_form_label" >区分</div>
                         <select name="rest_class" disabled  class="request_form_select">
                             <c:choose>
                                    <c:when test="${rest_class ==1}">
                                             <option >有給休暇</option>
                                    </c:when>
                                    <c:when test="${rest_class ==2}">
                                             <option>年休</option>
                                    </c:when>
                                    <c:when test="${rest_class ==3}">
                                             <option>リフレッシュ休暇</option>
                                    </c:when>
                                    <c:when test="${rest_class ==4}">
                                             <option>休職</option>
                                    </c:when>
                            </c:choose>
                         </select>

                         <div id="request_br"></div>

                         <div id="request_form_label">コメント</div>
                         <textarea id="comment"  disabled class="request_form_text_large"  maxlength="200" ></textarea>
                         <script>
                              document.getElementById('comment').value = "${comment}";
                         </script>

                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                         <div id="request_br"></div>
                      </div>


                      <form method="POST" action="${pageContext.request.contextPath}/rp_rest_update">
                          <div id="request_form_footer">
                                  <c:if test="${user_type==1}">
                                     <div id="request_form_label">差戻事由</div>
                                         <textarea  name="return_reason" class="request_form_text_large"  maxlength="200"></textarea>
                                  </c:if>
                                  <c:if test="${user_type==2 and redo_flg==1}">
                                     <div id="request_form_label">差戻事由</div>
                                         <textarea  name="return_reason" disabled class="request_form_text_large"  maxlength="200">${return_reason}</textarea>
                                         <script>
                                              document.getElementById('return_reason').value = "${return_reason}";
                                          </script>
                                  </c:if>
                                  <c:if test="${user_type==1}">
                                     <div id="request_br"></div>
                                     <input type="hidden" name="request_id" value="${request_id}" ></input>
                                     <input type="submit" name="approval_type"  value="承認" class="btn--black--small"></input>
                                     <input type="submit" name="approval_type"  value="差戻" class="btn--black--small"></input>
                                  </c:if>
                          </div>
                      </form>

                </c:if>

            </div>
        </div>
    </c:param>
</c:import>