<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tgcp" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"/>
    <title>Order</title>
</head>
<body>
<main class="register">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

        <div class="register-block">
            <h1><fmt:message key="order"/></h1>
            <form action="order" novalidate class="reg-form" method="post" id="createOrder">
                <div class="form-row">
                    <label>Delivery / payment</label>
                    <select name="delivery_payment" id="delivery_payment">
                        <option value="ADDRESS_CASH"><fmt:message key="targetedCash"/></option>
                        <option value="ADDRESS_CASHLESS"><fmt:message key="targetedCashless"/></option>
                        <option value="SHOP_CASH"><fmt:message key="selfCash"/></option>
                        <option value="SHOP_CASHLESS"><fmt:message key="selfCashless"/></option>
                    </select>
                </div>
                <div class="form-row">
                    <label><fmt:message key="paymentDetails"/></label>
                    <input
                            required
                            id="payment_details"
                            name="payment_details"
                    />
                </div>
                <div class="form-row">
                    <input type="submit" value="<fmt:message key="createOrder"/>" class="submit-btn"/>
                </div>
            </form>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>