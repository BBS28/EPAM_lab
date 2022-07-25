package com.epam.shchehlov.repository.sql;

import com.epam.shchehlov.bean.ProductFilterBean;
import com.epam.shchehlov.bean.SQLBean;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for building SQL queries for product repository.
 *
 * @author B.Shchehlov.
 */
public class SQLBuilder {

    private static final Logger logger = Logger.getLogger(SQLBuilder.class);

    private static final String SQL_PART_SELECT_FIELDS = "SELECT pr.id id, pr.name name, pr.description description, pr.price price, c.name category_name, m.name manufacturer_name ";
    private static final String SQL_PART_ORDER_BY = " ORDER BY ";
    private static final String SQL_PART_DESC = " DESC ";
    private static final String SQL_PART_LIMIT = " LIMIT ";
    private static final String SQL_PART_SELECT_COUNT = "SELECT COUNT(*) products_number ";
    private static final String SQL_PART_FROM = "FROM ptshop.products pr LEFT JOIN ptshop.category c ON pr.category_id = c.id LEFT JOIN ptshop.manufacturer m ON pr.manufacturer_id = m.id ";
    private static final String SQL_PART_WHERE = "WHERE ";
    private static final String SQL_PART_PRICE = "pr.price ";
    private static final String SQL_PART_MORE = "> ";
    private static final String SQL_PART_LESS = "< ";
    private static final String SQL_PART_AND = " AND ";
    private static final String SQL_PART_OR = " OR ";
    private static final String SQL_PART_CATEGORY_NAME = " c.name = ";
    private static final String SQL_PART_MANUFACTURER_NAME = " m.name = ";
    private static final String SQL_PART_NAME_LIKE = "pr.name LIKE '%";
    private static final String SQL_PART_PERCENT = "%' ";
    private static final String SQL_PART_COMMA = " ,";
    private static final String SQL_PART_OPEN_BRACKET = "(";
    private static final String SQL_PART_CLOSE_BRACKET = ")";
    private static final String SQL_PART_QUESTION_MARK = "?";
    private static final String SQL_MARKER = "sql -> ";
    private static final String SQL_BEAN_MARKER = "sql -> ";

    private final List<Object> values;
    private final ProductFilterBean productFilterBean;
    private boolean isFirstParameter;

    public SQLBuilder(ProductFilterBean productFilterBean) {
        this.productFilterBean = productFilterBean;
        values = new ArrayList<>();
        isFirstParameter = true;
    }

    public SQLBean buildGetAllSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_PART_SELECT_FIELDS);
        addFromPart(sql);
        addTablesAndConditionPart(productFilterBean, sql);
        sql.append(SQL_PART_ORDER_BY)
                .append(productFilterBean.getSortField());

        if (!productFilterBean.isAscending()) {
            sql.append(SQL_PART_DESC);
        }
        logger.debug(SQL_MARKER + sql);

        int displacement = (productFilterBean.getPageNumber() - 1) * productFilterBean.getProductsOnPage();

        sql.append(SQL_PART_LIMIT)
                .append(SQL_PART_QUESTION_MARK)
                .append(SQL_PART_COMMA)
                .append(SQL_PART_QUESTION_MARK);

        values.add(displacement);
        values.add(productFilterBean.getProductsOnPage());
        logger.debug(SQL_MARKER + sql);

        return getSqlBean(sql);
    }

    public SQLBean buildGetProductSizeSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_PART_SELECT_COUNT);
        addFromPart(sql);
        addTablesAndConditionPart(productFilterBean, sql);
        logger.debug(SQL_MARKER + sql);

        return getSqlBean(sql);
    }

    private SQLBean getSqlBean(StringBuilder sql) {
        SQLBean sqlBean = new SQLBean();
        sqlBean.setSqlQuery(sql.toString());
        Object[] valuesArray = new Object[values.size()];
        sqlBean.setValues(values.toArray(valuesArray));
        logger.debug(SQL_BEAN_MARKER + sqlBean);
        return sqlBean;
    }

    public void addFromPart(StringBuilder sql) {
        sql.append(SQL_PART_FROM);
    }

    private void addTablesAndConditionPart(ProductFilterBean productFilterBean, StringBuilder sql) {
        if (productFilterBean.getMinPrice() != null) {
            sql.append(SQL_PART_WHERE)
                    .append(SQL_PART_PRICE)
                    .append(SQL_PART_MORE)
                    .append(SQL_PART_QUESTION_MARK);
            values.add(productFilterBean.getMinPrice());
            isFirstParameter = false;
        }

        if (productFilterBean.getMaxPrice() != null) {
            setWhereOrAndPart(sql);
            sql.append(SQL_PART_PRICE)
                    .append(SQL_PART_LESS)
                    .append(SQL_PART_QUESTION_MARK);
            values.add(productFilterBean.getMaxPrice());
        }

        if (productFilterBean.getName() != null) {
            setWhereOrAndPart(sql);
            sql.append(SQL_PART_NAME_LIKE)
                    .append(productFilterBean.getName())
                    .append(SQL_PART_PERCENT);
        }

        addCollectionValues(productFilterBean.getCategories(), sql, SQL_PART_CATEGORY_NAME);
        addCollectionValues(productFilterBean.getManufacturers(), sql, SQL_PART_MANUFACTURER_NAME);
    }

    private void addCollectionValues(List<String> list, StringBuilder sql, String fieldName) {
        if (list != null && !list.isEmpty()) {
            int index = 0;

            for (String fieldValue : list) {
                if (index == 0) {
                    setWhereOrAndPart(sql);
                    if (list.size() > 1) {
                        sql.append(SQL_PART_OPEN_BRACKET);
                    }
                } else {
                    sql.append(SQL_PART_OR);
                }
                sql.append(fieldName).append(SQL_PART_QUESTION_MARK);
                values.add(fieldValue);
                index++;
            }

            if (list.size() > 1) {
                sql.append(SQL_PART_CLOSE_BRACKET);
            }
        }
    }

    private void setWhereOrAndPart(StringBuilder sql) {
        if (isFirstParameter) {
            sql.append(SQL_PART_WHERE);
            isFirstParameter = false;
        } else {
            sql.append(SQL_PART_AND);
        }
    }
}
