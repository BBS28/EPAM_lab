function addProductToCart(productId) {
    console.log("addProductToCart:" + productId)
    $.ajax({
        type: "post",
        url: "updateCart",
        data: ({
            productId: productId
        }),
        success: responseAddToCart
    });
}

function updateQuantity(productId, quantity) {
    console.log("!" + productId + "  " + quantity)
    $.ajax({
        type: "post",
        url: "updateCart",
        data: ({
            productId: productId,
            quantity: quantity
        }),
        success: responseUpdateQuantity
    });
}

function responseAddToCart(jsonObject) {
    var parse = JSON.parse(jsonObject);
    console.log(parse);
    console.log(parse.locale);

    var cartLink = document.getElementById("cart_link");

    var cartString;
    if("en" === parse.locale) {
        cartString = "Cart"
    }
    if("ru" === parse.locale) {
        cartString = "Корзина"
    }

    cartLink.innerText = cartString + '(' + parse.amount + ')';
}

function responseUpdateQuantity(jsonObject) {
    var parse = JSON.parse(jsonObject);
    console.log(parse)

    var quantity = document.getElementById("number_of_product_" + parse.productId);
    var totalPrice = document.getElementById("total_price");
    var value = document.getElementById("product_value_" + parse.productId);

    quantity.innerText = quantity.textContent = parse.quantity;
    value.innerText = parse.value + ",00";
    totalPrice.innerText = parse.total + ",00";
}
