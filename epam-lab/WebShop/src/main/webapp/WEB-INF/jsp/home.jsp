<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tgcp" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"/>
    <title>Main</title>
</head>
<body>
<main class="intro">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="welcome-box">
            <h1><fmt:message key="welcomeToShop"/></h1>
            <p>
                <fmt:message key="slogan"/>
            </p>
            <p>
                <fmt:message key="welcomeText"/>
            </p>
            <br>
            <span class="inform">
                    <div id="info_message">
                        <c:if test="${not empty sessionScope.message_info}">
                            ${sessionScope.message_info}
                        </c:if>
                    </div>
                </span>
        </div>

    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>