package com.epam.shchehlov.servlet;

import com.epam.shchehlov.entity.Cart;
import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.service.ProductService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_ERROR_INPUT;
import static com.epam.shchehlov.constant.Constant.CLEAR;
import static com.epam.shchehlov.constant.Constant.DELETE;
import static com.epam.shchehlov.constant.Constant.PARAMETER_ACTION;
import static com.epam.shchehlov.constant.Constant.PARAMETER_PRODUCT_ID;
import static com.epam.shchehlov.constant.Constant.PARAMETER_QUANTITY;
import static com.epam.shchehlov.constant.Constant.PRODUCT_SERVICE;
import static com.epam.shchehlov.constant.Constant.PROPERTY_AMOUNT;
import static com.epam.shchehlov.constant.Constant.PROPERTY_LOCALE;
import static com.epam.shchehlov.constant.Constant.PROPERTY_PRODUCT_ID;
import static com.epam.shchehlov.constant.Constant.PROPERTY_QUANTITY;
import static com.epam.shchehlov.constant.Constant.PROPERTY_TOTAL;
import static com.epam.shchehlov.constant.Constant.PROPERTY_VALUE;
import static com.epam.shchehlov.constant.Constant.QUANTITY_MAX;
import static com.epam.shchehlov.constant.Constant.QUANTITY_MIN;
import static com.epam.shchehlov.constant.Constant.REFERER;
import static com.epam.shchehlov.constant.Constant.SESSION_ATTRIBUTE_CART;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateCartServlet.class);
    private static final String PRODUCT_ID = "productId ==> ";
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(ATTRIBUTE_ERROR_INPUT);
        Cart cart = getCart(session);

        String action = request.getParameter(PARAMETER_ACTION);
        if (CLEAR.equals(action)) {
            cart.clearCart();
        } else {
            long productId = getProductId(request);
            logger.debug(PRODUCT_ID + productId);
            Product product = productService.getProduct(productId);
            if (DELETE.equals(action)) {
                cart.deleteProduct(product);
            }
        }

        logger.debug("cart ==> " + cart);
        session.setAttribute(SESSION_ATTRIBUTE_CART, cart);
        response.sendRedirect(request.getHeader(REFERER));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("updateCart doPost started");
        HttpSession session = request.getSession();

        Cart cart = getCart(session);
        long productId = getProductId(request);
        logger.debug(PRODUCT_ID + productId);
        Product product = productService.getProduct(productId);

        if (request.getParameter(PARAMETER_QUANTITY) != null) {
            updateProduct(Integer.parseInt(request.getParameter(PARAMETER_QUANTITY)), cart, product, response);
        } else {
            addProduct(session, cart, product, response);
        }
    }

    private void updateProduct(int quantity, Cart cart, Product product, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        JsonObject jsonObject = new JsonObject();

        if (quantity <= QUANTITY_MIN || quantity > QUANTITY_MAX) {
            quantity = cart.getProductAmount(product);
        } else {
            cart.addProduct(product, quantity);
        }
        int totalPrice = cart.getTotalPrice();
        logger.debug("totalPrice ==> " + totalPrice);
        int value = product.getPrice() * quantity;

        jsonObject.addProperty(PROPERTY_LOCALE, response.getLocale().getLanguage());
        jsonObject.addProperty(PROPERTY_QUANTITY, quantity);
        jsonObject.addProperty(PROPERTY_TOTAL, totalPrice);
        jsonObject.addProperty(PROPERTY_PRODUCT_ID, product.getId());
        jsonObject.addProperty(PROPERTY_VALUE, value);

        handleWriter(printWriter, jsonObject);
    }

    private void addProduct(HttpSession session, Cart cart, Product product, HttpServletResponse response) throws IOException {
        if (cart.getProductList().contains(product)) {
            cart.addProduct(product, cart.getProductAmount(product) + 1);
        } else {
            cart.addProduct(product, 1);
        }

        int amount = cart.getAllProductsAmount();
        logger.debug("amount ==> " + amount);

        PrintWriter printWriter = response.getWriter();
        JsonObject jsonObject = new JsonObject();
        session.setAttribute(SESSION_ATTRIBUTE_CART, cart);
        jsonObject.addProperty(PROPERTY_AMOUNT, amount);
        jsonObject.addProperty(PROPERTY_LOCALE, response.getLocale().getLanguage());
        handleWriter(printWriter, jsonObject);
    }

    private Cart getCart(HttpSession session) {
        if (session.getAttribute(SESSION_ATTRIBUTE_CART) == null) {
            return new Cart();
        } else {
            return (Cart) session.getAttribute(SESSION_ATTRIBUTE_CART);
        }
    }

    private long getProductId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter(PARAMETER_PRODUCT_ID));
    }

    private void handleWriter(PrintWriter printWriter, JsonObject jsonObject) {
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }
}
