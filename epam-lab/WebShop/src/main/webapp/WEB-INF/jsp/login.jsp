<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"/>
    <title>Login</title>
</head>
<body>
<main class="register">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="register-block">
            <h1>Login</h1>
            <c:choose>

                <c:when test="${empty sessionScope.currentUser}">
            <span class="error">
                    <div id="error_message">
                            <c:if test="${not empty sessionScope.login_message_error}">
                                <fmt:message key="${sessionScope.login_message_error}"/>
                            </c:if>
                    </div>
                </span>
                    <form action="userLogin" novalidate class="reg-form" method="post" id="login-form">
                        <div class="form-row">
                            <label><fmt:message key="registrationLogin"/></label>
                            <input
                                    required
                                    type="text"
                                    id="login"
                                    name="login"/>
                        </div>

                        <div class="form-row">
                            <label><fmt:message key="password"/></label>
                            <input
                                    required
                                    type="password"
                                    id="password"
                                    name="password"/>
                        </div>

                        <div class="form-row">
                            <input type="submit" value="<fmt:message key="send"/>" class="submit-btn"/>
                        </div>
                    </form>
                </c:when>

                <c:otherwise>
                    <span>
                        <label><fmt:message key="successRegistration"/></label>
                    </span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
