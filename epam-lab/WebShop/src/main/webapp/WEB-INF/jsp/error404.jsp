<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tgcp" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <title>Forbidden Access</title>
</head>
<body>
<main class="register">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="register-block">
            <h1><fmt:message key="resourceNotFound"/></h1>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
