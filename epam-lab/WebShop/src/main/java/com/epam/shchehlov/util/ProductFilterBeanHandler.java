package com.epam.shchehlov.util;

import com.epam.shchehlov.bean.ProductFilterBean;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.epam.shchehlov.constant.Constant.EMPTY;
import static com.epam.shchehlov.constant.Constant.EMPTY_STRING;
import static com.epam.shchehlov.constant.Constant.FALSE;
import static com.epam.shchehlov.constant.Constant.INPUT;
import static com.epam.shchehlov.constant.Constant.PARAMETER_CATEGORY;
import static com.epam.shchehlov.constant.Constant.PARAMETER_MANUFACTURER;
import static com.epam.shchehlov.constant.Constant.PARAMETER_MAX_PRICE;
import static com.epam.shchehlov.constant.Constant.PARAMETER_MIN_PRICE;
import static com.epam.shchehlov.constant.Constant.PARAMETER_PAGE_NUMBER;
import static com.epam.shchehlov.constant.Constant.PARAMETER_PRODUCT_NAME;
import static com.epam.shchehlov.constant.Constant.PARAMETER_PRODUCT_ON_PAGE;
import static com.epam.shchehlov.constant.Constant.PARAMETER_SORT_DIRECTION;
import static com.epam.shchehlov.constant.Constant.PARAMETER_SORT_FIELD;
import static com.epam.shchehlov.constant.Constant.PRODUCT_NAME;
import static com.epam.shchehlov.constant.Constant.PRODUCT_ON_PAGE_DEFAULT;

/**
 * Class for handling ProductFilterBean.
 *
 * @author B.Shchehlov.
 */
public class ProductFilterBeanHandler {

    private static final Logger logger = Logger.getLogger(ProductFilterBeanHandler.class);

    private ProductFilterBeanHandler() {
    }

    /**
     * Returns bean with product filter parameters.
     *
     * @param request HttpServletRequest.
     * @return instance of ProductFilterBean.
     */
    public static ProductFilterBean getProductFilterBean(HttpServletRequest request) {
        Integer minPrice = getPriceValue(request, PARAMETER_MIN_PRICE);
        Integer maxPrice = getPriceValue(request, PARAMETER_MAX_PRICE);
        String productFilterName = request.getParameter(PARAMETER_PRODUCT_NAME);
        List<String> manufacturers = getCheckboxValues(request, PARAMETER_MANUFACTURER);
        List<String> categories = getCheckboxValues(request, PARAMETER_CATEGORY);
        int productsOnPage = getProductsOnPage(request, PARAMETER_PRODUCT_ON_PAGE);
        String sortField = getSortValue(request, PARAMETER_SORT_FIELD);
        int pageNumber = getPageNumber(request, PARAMETER_PAGE_NUMBER);
        boolean isAscending = getSortDirection(request, PARAMETER_SORT_DIRECTION);

        ProductFilterBean productFilterBean = new ProductFilterBean();
        productFilterBean.setMinPrice(minPrice);
        productFilterBean.setMaxPrice(maxPrice);
        productFilterBean.setName(productFilterName);
        productFilterBean.setManufacturers(manufacturers);
        productFilterBean.setCategories(categories);
        productFilterBean.setProductsOnPage(productsOnPage);
        productFilterBean.setSortField(sortField);
        productFilterBean.setAscending(isAscending);
        productFilterBean.setPageNumber(pageNumber);

        return productFilterBean;
    }

    /**
     * Returns price value.
     *
     * @param request          HttpServletRequest
     * @param parameterName entered value.
     * @return price value.
     */
    private static Integer getPriceValue(HttpServletRequest request, String parameterName) {
        String priceParameterValue = request.getParameter(parameterName);

        if (priceParameterValue == null) {
            logger.debug("price parameter value is null");
            return null;
        }

        Integer price = null;
        try {
            price = Integer.parseInt(priceParameterValue);
        } catch (NumberFormatException e) {
            logger.error("Input not valid");
            request.setAttribute(parameterName + INPUT, EMPTY);
        }
        return price;
    }

    /**
     * Returns the number of products on the page.
     *
     * @param request       HttpServletRequest.
     * @param parameterName name of parameter.
     * @return number of products on the page.
     */
    private static int getProductsOnPage(HttpServletRequest request, String parameterName) {
        String pagesValueParameterValue = request.getParameter(parameterName);
        if (pagesValueParameterValue == null) {
            logger.debug("number of products on pages is null");
            return PRODUCT_ON_PAGE_DEFAULT;
        }

        int productsOnPage;
        try {
            productsOnPage = Integer.parseInt(pagesValueParameterValue);
        } catch (NumberFormatException e) {
            logger.error("number of products on pages not valid");
            productsOnPage = PRODUCT_ON_PAGE_DEFAULT;
        }
        return productsOnPage;
    }

    /**
     * Returns list of checkbox values names that were checked.
     *
     * @param request       HttpServletRequest.
     * @param parameterName name of parameter.
     * @return list of values.
     */
    private static List<String> getCheckboxValues(HttpServletRequest request, String parameterName) {
        String[] values = request.getParameterValues(parameterName);
        logger.debug(values);
        List<String> valueList = new ArrayList<>();

        if (values != null) {
            for (String name : values) {
                if (name != null) {
                    valueList.add(name);
                }
            }
        }
        return valueList;
    }

    /**
     * Returns the name of the field to sort.
     *
     * @param request       HttpServletRequest.
     * @param parameterName name of parameter.
     * @return the name of the field to sort.
     */
    private static String getSortValue(HttpServletRequest request, String parameterName) {
        if (request.getParameter(parameterName) == null || request.getParameter(parameterName).equals(EMPTY_STRING)) {
            return PRODUCT_NAME;
        }
        return request.getParameter(parameterName);
    }

    /**
     * Returns number of current page.
     *
     * @param request       HttpServletRequest.
     * @param parameterName name of parameter.
     * @return number of current page.
     */
    private static int getPageNumber(HttpServletRequest request, String parameterName) {
        int pageNumber = 1;
        if (request.getParameter(parameterName) == null) {
            return pageNumber;
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            logger.error("Can't get pageNumber");
        }
        return pageNumber;
    }

    /**
     * Returns true if sort direction is ascending.
     *
     * @param request       HttpServletRequest.
     * @param parameterName name of parameter.
     * @return true if sort direction is ascending.
     */
    private static boolean getSortDirection(HttpServletRequest request, String parameterName) {
        String directionValue = request.getParameter(parameterName);
        logger.debug("directionValue ==> " + directionValue);
        return directionValue == null || !directionValue.equalsIgnoreCase(FALSE);
    }
}
