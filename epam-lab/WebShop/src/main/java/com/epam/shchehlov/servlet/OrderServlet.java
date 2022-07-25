package com.epam.shchehlov.servlet;

import com.epam.shchehlov.entity.Cart;
import com.epam.shchehlov.entity.attribute.DeliveryPayment;
import com.epam.shchehlov.entity.Order;
import com.epam.shchehlov.entity.attribute.OrderStatus;
import com.epam.shchehlov.service.OrderService;
import com.epam.shchehlov.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.CART_PAGE;
import static com.epam.shchehlov.constant.Constant.CREATED_ORDER_PAGE;
import static com.epam.shchehlov.constant.Constant.CURRENT_USER;
import static com.epam.shchehlov.constant.Constant.DELIVERY_PAYMENT;
import static com.epam.shchehlov.constant.Constant.ORDER_CREATED;
import static com.epam.shchehlov.constant.Constant.ORDER_PAGE;
import static com.epam.shchehlov.constant.Constant.ORDER_SERVICE;
import static com.epam.shchehlov.constant.Constant.PAYMENT_DETAILS;
import static com.epam.shchehlov.constant.Constant.SESSION_ATTRIBUTE_CART;
import static com.epam.shchehlov.constant.Constant.SESSION_ATTRIBUTE_ORDER;
import static com.epam.shchehlov.constant.Constant.USER_SERVICE;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE);
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ORDER_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SESSION_ATTRIBUTE_CART);

        if (cart.getAllProductsAmount() == 0) {
            response.sendRedirect(CART_PAGE);
            return;
        }

        DeliveryPayment deliveryPayment = DeliveryPayment.valueOf(request.getParameter(DELIVERY_PAYMENT));
        String paymentDetails = request.getParameter(PAYMENT_DETAILS);
        String currentUserLogin = (String) session.getAttribute(CURRENT_USER);
        long userId = userService.getUser(currentUserLogin).getId();

        Order order = new Order();
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setStateDetail(ORDER_CREATED);
        order.setDeliveryPayment(deliveryPayment);
        order.setPaymentDetails(paymentDetails);
        order.setUserId(userId);
        order.setProductList(cart.getOrderedProductsList());
        order = orderService.addOrder(order);
        session.setAttribute(SESSION_ATTRIBUTE_ORDER, order);
        session.removeAttribute(SESSION_ATTRIBUTE_CART);
        request.getRequestDispatcher(CREATED_ORDER_PAGE).forward(request, response);
    }
}
