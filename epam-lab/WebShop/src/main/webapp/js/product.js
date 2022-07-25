function requestBuilder(page) {
    var request = "http://localhost:8080/WebShop/products?";

    if (document.getElementById("min_price").value != null) {
        request = request + "min_price=" + document.getElementById("min_price").value;
    }

    if (document.getElementById("max_price").value != null) {
        request = request + "&max_price=" + document.getElementById("max_price").value;
    }

    if (document.getElementById("product_name").value !== "") {
        request = request + "&product_name=" + document.getElementById("product_name").value;
    }

    var categories = document.getElementsByName("category");
    for (var index1 = 0; index1 < categories.length; index1++) {
        if (categories[index1].checked) {
            request = request + "&category=" + categories[index1].value;
        }
    }

    var manufacturers = document.getElementsByName("manufacturer");
    for (var index2 = 0; index2 < manufacturers.length; index2++) {
        if (manufacturers[index2].checked) {
            request = request + "&manufacturer=" + manufacturers[index2].value;
        }
    }

    request = request + "&products_on_page=" + document.getElementById("products_on_page").value
    request = request + "&sort_field=" + document.getElementById("sort_field").value;
    request = request + "&sort_direction=" + document.getElementById("sort_direction").value;

    if (page !== null) {
        request = request + "&page_number=" + page;
    }

    window.location.assign(request)
}