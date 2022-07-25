<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jspf/headContent.jspf" %>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon"/>
    <script src="<c:url value="/js/cartUpdate.js"/>"></script>
    <script src="<c:url value="/js/product.js"/>"></script>

    <title>Shop</title>
</head>
<body>
<main class="shop">
    <div class="wrapper flex">
        <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
        <br><br>
        <div class="form-filter">
            <form>
                <div class="price-range">
                    <div class="filter-form-row">
                        <label><fmt:message key="minPrice"/>:</label>
                        <label>
                            <input type="number" id="min_price" name="min_price"
                                   value="${not empty requestScope.min_priceInput ? " " : requestScope.minPrice}"/>
                        </label>
                    </div>
                    <div class="filter-form-row">
                        <label><fmt:message key="maxPrice"/>:</label>
                        <label>
                            <input type="number" id="max_price" name="max_price"
                                   value="${not empty requestScope.max_priceInput ? " " : requestScope.maxPrice}"/>
                        </label>
                    </div>
                </div>
                <div class="filter-form-row">
                    <label><fmt:message key="productName"/>:</label>
                    <label for="product_name"></label><input
                        value="${requestScope.productName}"
                        type="text"
                        id="product_name"
                        name="product_name"
                />
                </div>
                <div class="filter-form-row">
                    <label><fmt:message key="manufacturer"/>:</label>
                    <div>
                        <c:forEach var="manufacturer" items="${sessionScope.manufacturerList}">
                            <input type="checkbox" id="${manufacturer.name}" name="manufacturer"
                                   value="${manufacturer.name}"
                            <c:forEach var="manufacturerName" items="${requestScope.manufacturers}">
                                   <c:if test="${manufacturer.name eq manufacturerName}">checked </c:if>
                            </c:forEach>>
                            <label for="${manufacturer.name}">${manufacturer.name}</label><br>
                        </c:forEach>

                    </div>
                </div>
                <br>
                <h1><fmt:message key="category"/>:</h1>
                <div>
                    <c:forEach var="category" items="${sessionScope.categoryList}">
                        <input type="checkbox" id="${category.name}" name="category" value=${category.name}
                        <c:forEach var="categoryName" items="${requestScope.categories}">
                                <c:if test="${category.name eq categoryName}">checked </c:if>
                            ${category eq categoryName ? 'checked' : ''}
                        </c:forEach>>
                        <label for="${category.name}"><fmt:message key="${category.name}"/> </label><br>
                    </c:forEach>

                </div>
                <br>
                <input type="button" value="<fmt:message key="search"/>" onclick="requestBuilder()"><br><br>
            </form>
        </div>
        <div class="shop-block">
            <h1><fmt:message key="products"/></h1>
            <div class="form-sort">
                <form>
                    <div class="page-size">
                        <span><fmt:message key="pageSize"/>:</span>
                        <label>
                            <select name="products_on_page" id="products_on_page" onchange="requestBuilder()">
                                <option value="${requestScope.productsOnPage}" selected="selected"
                                        hidden="hidden">${requestScope.productsOnPage}</option>
                                <option value="3">3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                                <option value="12">12</option>
                            </select>
                        </label>
                    </div>
                    <div class="sort">
                        <span><fmt:message key="sort"/>:</span>
                        <label>
                            <select name="sort_field" id="sort_field" onchange="requestBuilder()">
                                <option value="${requestScope.sortField}" selected="selected"
                                        hidden="hidden"><fmt:message key="${requestScope.sortField}"/></option>
                                <option value="name"><fmt:message key="name"/></option>
                                <option value="price"><fmt:message key="price"/></option>
                            </select>
                        </label>
                        <label>
                            <select name="sort_direction" id="sort_direction" onchange="requestBuilder()">
                                <option value="${requestScope.isAscending}" selected="selected" hidden="hidden">
                                    <c:choose>
                                        <c:when test="${requestScope.isAscending}">
                                            &#8593
                                        </c:when>
                                        <c:otherwise>
                                            &#8595
                                        </c:otherwise>
                                    </c:choose>
                                </option>
                                <option value="true">&#8593</option>
                                <option value="false">&#8595</option>
                            </select>
                        </label>
                    </div>
                </form>
            </div>
            <div class="product-wrapper">
                <c:forEach var="product" items="${requestScope.productList}">
                    <div class="product-item">
                        <div class="name-price">
                            <span class="prod-name">${product.manufacturerName}</span>
                            <span class="prod-name">${product.name}</span>
                            <em class="prod-price">${product.price},00</em>
                        </div>
                        <span>${product.description}</span>
                        <span>(<fmt:message key="${product.categoryName}"/>)</span><br>
                        <span><button onclick="addProductToCart(${product.id})" class="add-btn"><fmt:message key="addProduct"/></button> </span><br><br>
                        <span><img src="images/products/${product.name}.jpg" alt="${product.description}"/></span>
                    </div>
                </c:forEach>
                <span class="inform-no-product">
                    <div id="info_message">
                        <c:if test="${not empty requestScope.noProducts}"><br>
                            ${requestScope.noProducts}<br><br>
                        </c:if>
                    </div>
                </span>
            </div>
            <ul class="pager">
                <li><a href="#" onclick="requestBuilder(1);event.preventDefault();"><fmt:message key="begin"/></a></li>
                <c:forEach var="page" items="${requestScope.pages}">
                    <c:choose>
                        <c:when test="${page eq requestScope.pageNumber}">
                            <li>${requestScope.pageNumber}</li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="#" onclick="requestBuilder(${page});event.preventDefault();"> ${page} </a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <li><a href="#" onclick="requestBuilder(${requestScope.numberOfPages});event.preventDefault();">
                    <fmt:message key="end"/>
                    (${requestScope.numberOfPages})</a></li>
            </ul>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>