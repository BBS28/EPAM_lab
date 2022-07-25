<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <title>Registration</title>
</head>
<body>
<main class="register">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="register-block">
            <h1><fmt:message key="customerRegistration"/></h1>
            <span class="error">
                    <div id="error_message">
                        <c:if test="${not empty sessionScope.message_error}">
                            <fmt:message key="registrationError"/>: <fmt:message key="${sessionScope.message_error}"/>
                        </c:if>
                    </div>
                </span>
            <form action="userRegistration" novalidate class="reg-form" method="post" id="reg-form"
                  enctype='multipart/form-data'>
                <div class="form-row">
                    <label><fmt:message key="firstName"/></label>
                    <input
                            value="${sessionScope.temp_first_name}"
                            required
                            type="text"
                            id="first_name"
                            name="first_name"
                            pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{1,}$"
                    />
                    <span class="error" id="error_first_name">
                        <div id="error_message_first_name">
                        <c:if test="${not empty sessionScope.error_message_first_name}">
                            <fmt:message key="${sessionScope.error_message_first_name}"/>
                        </c:if>
                        </div>
                    </span>
                </div>
                <div class="form-row">
                    <label><fmt:message key="lastName"/></label>
                    <input
                            value="${sessionScope.temp_last_name}"
                            required
                            type="text"
                            id="last_name"
                            name="last_name"
                            pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{1,}$"
                    />
                    <span class="error" id="error_last_name">
                        <div id="error_message_last_name">
                        <c:if test="${not empty sessionScope.error_message_last_name}">
                            <fmt:message key="${sessionScope.error_message_last_name}"/>
                        </c:if>
                        </div>
                    </span>
                </div>
                <div class="form-row">
                    <label><fmt:message key="email"/></label>
                    <input
                            value="${sessionScope.temp_email}"
                            required
                            type="email"
                            id="email"
                            name="email"
                            pattern="^\S+@\S+\.\S+$"
                    />
                    <span class="error" id="error_email">
                        <div id="error_message_email">
                        <c:if test="${not empty sessionScope.error_message_email}">
                            <fmt:message key="${sessionScope.error_message_email}"/>
                        </c:if>
                        </div>
                    </span>
                </div>
                <div class="form-row">
                    <label><fmt:message key="registrationLogin"/></label>
                    <input
                            value="${sessionScope.temp_login}"
                            required
                            type="text"
                            id="login"
                            name="login"
                            pattern="^[A-Za-z][A-Za-z0-9_]{7,29}$"
                    />
                    <span class="error" id="error_login">
                        <div id="error_message_login">
                        <c:if test="${not empty sessionScope.error_message_login}">
                            <fmt:message key="${sessionScope.error_message_login}"/>
                        </c:if>
                        </div>
                    </span>
                </div>
                <div class="form-row">
                    <label><fmt:message key="password"/></label>
                    <input
                            required
                            type="password"
                            id="password"
                            name="password"
                            pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                    />
                    <span class="error" id="error_password">
                        <div id="error_message_password">
                        <c:if test="${not empty sessionScope.error_message_password}">
                            <fmt:message key="${sessionScope.error_message_password}"/>
                        </c:if>
                        </div>
                    </span>
                </div>
                <div class="form-row">
                    <label for="mailing"> <fmt:message key="mailing"/></label>
                    <input type="checkbox" id="mailing" name="mailing" value="confirm">
                </div>
                <div class="form-row">
                    <label><fmt:message key="avatar"/></label>
                    <div class="inputfile-box">
                        <input id="file"
                               type="file"
                               class="inputfile"
                               accept="image/jpeg"
                               value="insert"
                               placeholder="insert
                               file" name="avatar"
                               onChange="uploadFile(this)"/>
                        <label for="file">
                            <span id="file-name" class="file-box"></span>
                            <span class="file-button"><fmt:message key="selectFile"/></span>
                        </label>
                    </div>
                </div>
                <tgcp:captcha/>
                <div class="form-row">
                    <input type="submit" value="<fmt:message key="send"/>" class="submit-btn"/>
                </div>
            </form>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script src="js/registrationJS.js"></script>
<script src="js/avatar.js"></script>
<%--<script src="js/registrationJQuery.js"></script>--%>
</body>
</html>
