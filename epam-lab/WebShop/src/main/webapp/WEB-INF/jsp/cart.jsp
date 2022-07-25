<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tgcp" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <script src="<c:url value="/js/cartUpdate.js"/>"></script>
    <title>Cart</title>
</head>
<body>

<main class="register">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="register-block">
            <h1><fmt:message key="shoppingCart"/></h1>
            <c:if test="${not empty sessionScope.cart.orderedProductsList}">
                <table class="cart-table">
                    <tr class="cart-table-head">
                        <td><fmt:message key="productCart"/></td>
                        <td><fmt:message key="priceCart"/></td>
                        <td><fmt:message key="quantityCart"/></td>
                        <td><fmt:message key="valueCart"/></td>
                        <td><fmt:message key="deleteCart"/></td>
                    </tr>
                    <c:forEach var="product" items="${sessionScope.cart.orderedProductsList}">
                        <tr>
                            <td>${product.productName}</td>
                            <td>${product.productPrice},00</td>
                            <td>
                                <input class="cart-number" type="number" id='number_of_product_${product.productId}'
                                       name="number_of_product" min="1" max="20"
                                       value="${product.numberOfProducts}"
                                       onchange="updateQuantity(${product.productId}, this.value)"
                                       onkeypress="return false">
                            </td>
                            <td id="product_value_${product.productId}">${product.productTotalPrice},00</td>
                            <td><a href="updateCart?action=delete&productId=${product.productId}"
                                   class="clear-btn">X</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <span class="error">
                        <div id="error_input">
                        <c:if test="${not empty sessionScope.error_input}">
                            ${sessionScope.error_input}
                        </c:if>
                        </div>
                    </span>
                </table>

                <div class="cart-clear">
                    <label><a href="updateCart?action=clear" class="clear-btn"><fmt:message
                            key="clearCart"/></a></label>
                </div>
                <div class="cart-total">
                    <label><fmt:message key="totalPrice"/>: <span class="cart-table"
                                                                  id="total_price">${sessionScope.cart.totalPrice},00</span></label>
                </div>
                <c:if test="${sessionScope.currentUser != null}">
                    <div class="order-pos">
                        <label><a href="order" class="order-btn"><fmt:message key="createOrder"/></a> </label>
                    </div>
                </c:if>
                <c:if test="${sessionScope.currentUser == null}">
                    <div class="sign-in">
                        <div>
                            <label><fmt:message key="logInToOrder"/></label>
                        </div>
                        <div>
                            <label><a href="userRegistration"><fmt:message key="newRegistration"/></a></label>
                        </div>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${empty sessionScope.cart.orderedProductsList}">
                <div>
                    <label class="cart-message"><fmt:message key="noItems"/></label>
                </div>
            </c:if>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
