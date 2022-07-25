package com.epam.shchehlov.servlet;

import com.epam.shchehlov.bean.ProductFilterBean;
import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.service.ProductService;
import com.epam.shchehlov.util.PaginationUtils;
import com.epam.shchehlov.util.ProductFilterBeanHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_CATEGORIES;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_IS_ASCENDING;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_MANUFACTURERS;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_MAX_PRICE;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_MIN_PRICE;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_NO_PRODUCTS;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_NUMBER_OF_PAGES;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_PAGES;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_PAGE_NUMBER;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_PRODUCT_LIST;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_PRODUCT_NAME;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_PRODUCT_ON_PAGE;
import static com.epam.shchehlov.constant.Constant.ATTRIBUTE_SORT_FIELD;
import static com.epam.shchehlov.constant.Constant.MESSAGE_NO_PRODUCT;
import static com.epam.shchehlov.constant.Constant.PRODUCTS_PAGE;
import static com.epam.shchehlov.constant.Constant.PRODUCT_SERVICE;
import static com.epam.shchehlov.constant.Constant.SESSION_ATTRIBUTE_CATEGORIES;
import static com.epam.shchehlov.constant.Constant.SESSION_ATTRIBUTE_MANUFACTURERS;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ProductsServlet.class);

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("start doGet method");

        List<Category> categoryList = productService.getAllCategories();
        List<Manufacturer> manufacturerList = productService.getAllManufacturers();
        request.getSession().setAttribute(SESSION_ATTRIBUTE_CATEGORIES, categoryList);
        request.getSession().setAttribute(SESSION_ATTRIBUTE_MANUFACTURERS, manufacturerList);

        ProductFilterBean productFilterBean = ProductFilterBeanHandler.getProductFilterBean(request);
        logger.debug(productFilterBean);

        List<Product> productList = productService.getAllProducts(productFilterBean);
        logger.debug("productList.size() -> " + productList.size());
        int productAmount = productService.getProductNumber(productFilterBean);
        logger.debug("productAmount ==> " + productAmount);
        int numberOfPages = (int) Math.ceil(productAmount / (double) productFilterBean.getProductsOnPage());
        logger.debug("numberOfPages ==> " + numberOfPages);
        int[] pages = PaginationUtils.getPagesForPagination(productFilterBean.getPageNumber(), numberOfPages);
        logger.info(productFilterBean);

        if (productAmount == 0) {
            request.setAttribute(ATTRIBUTE_NO_PRODUCTS, MESSAGE_NO_PRODUCT);
        }
        request.setAttribute(ATTRIBUTE_MAX_PRICE, productFilterBean.getMaxPrice());
        request.setAttribute(ATTRIBUTE_MIN_PRICE, productFilterBean.getMinPrice());
        request.setAttribute(ATTRIBUTE_PRODUCT_NAME, productFilterBean.getName());
        request.setAttribute(ATTRIBUTE_SORT_FIELD, productFilterBean.getSortField());
        request.setAttribute(ATTRIBUTE_PAGE_NUMBER, productFilterBean.getPageNumber());
        request.setAttribute(ATTRIBUTE_PRODUCT_ON_PAGE, productFilterBean.getProductsOnPage());
        request.setAttribute(ATTRIBUTE_IS_ASCENDING, productFilterBean.isAscending());
        request.setAttribute(ATTRIBUTE_NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(ATTRIBUTE_PAGES, pages);
        request.setAttribute(ATTRIBUTE_CATEGORIES, productFilterBean.getCategories());
        request.setAttribute(ATTRIBUTE_MANUFACTURERS, productFilterBean.getManufacturers());
        request.setAttribute(ATTRIBUTE_PRODUCT_LIST, productList);

        request.getRequestDispatcher(PRODUCTS_PAGE).forward(request, response);
    }
}
