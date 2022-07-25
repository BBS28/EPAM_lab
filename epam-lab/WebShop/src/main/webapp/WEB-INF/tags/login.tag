<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>

<c:choose>
    <c:when test="${empty sessionScope.currentUser}">
        <form action="userLogin" novalidate method="post" id="login-form">
            <label>
                <input
                        type='text'
                        name='login'
                        class="login-bar"
                        placeholder='<fmt:message key="registrationLogin"/>'>
            </label>
            <label>
                <input
                        type='password'
                        name='password'
                        class="login-bar"
                        placeholder='<fmt:message key="password"/>'>
            </label>
            <input type="submit" value="<fmt:message key="login"/>" class="login-btn" formnovalidate/>
        </form>
    </c:when>
    <c:otherwise>
        <li>
            <img src=userAvatar class="avatar" alt="${sessionScope.currentUser}">
        </li>
        <li>
            <a type="logo-name">${sessionScope.currentUser}</a>
        </li>
        <li>
            <a href="userLogout" type="button" class="logout-btn"><fmt:message key="logout"/></a>
        </li>
    </c:otherwise>
</c:choose>
