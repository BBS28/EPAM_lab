<%@ attribute name="hidden_field" type="java.lang.String" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>

<div class="captcha">
    <label><fmt:message key="captcha"/></label>
    <img src="captcha?hidden_field=${hidden_field}" alt="captcha">
    <c:if test="${applicationScope.captchaType == 'field'}">
        <input type='hidden' name='hidden_field' value="${hidden_field}">
    </c:if>
    <label>
        <input type='text' name='user_captcha' placeholder="<fmt:message key="enterNumbers"/>">
    </label>
</div>
<br>