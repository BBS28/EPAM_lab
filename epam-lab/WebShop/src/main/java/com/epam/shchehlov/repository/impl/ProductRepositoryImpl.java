package com.epam.shchehlov.repository.impl;

import com.epam.shchehlov.bean.ProductFilterBean;
import com.epam.shchehlov.bean.SQLBean;
import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.repository.ProductRepository;
import com.epam.shchehlov.repository.sql.SQLBuilder;
import com.epam.shchehlov.repository.template.JdbcTemplate;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String GET_ONE_PRODUCT = "SELECT pr.id id, " +
            "pr.name name, " +
            "pr.description description, " +
            "pr.price price, " +
            "c.name category_name, " +
            "m.name manufacturer_name " +
            "FROM ptshop.products pr " +
            "LEFT JOIN ptshop.category c " +
            "ON pr.category_id = c.id " +
            "LEFT JOIN ptshop.manufacturer m " +
            "ON pr.manufacturer_id = m.id " +
            "WHERE pr.id = ?;";
    private static final String GET_ALL_MANUFACTURERS = "SELECT DISTINCT id, name FROM ptshop.manufacturer";
    private static final String GET_ALL_CATEGORIES = "SELECT DISTINCT id, name FROM ptshop.category";

    private final JdbcTemplate<Product> productJdbcTemplate;
    private final JdbcTemplate<Category> categoryJdbcTemplate;
    private final JdbcTemplate<Manufacturer> manufacturerJdbcTemplate;
    private final JdbcTemplate<Integer> productNumberJdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate<Product> productJdbcTemplate,
                                 JdbcTemplate<Category> categoryJdbcTemplate,
                                 JdbcTemplate<Manufacturer> manufacturerJdbcTemplate,
                                 JdbcTemplate<Integer> productNumberJdbcTemplate) {
        this.productJdbcTemplate = productJdbcTemplate;
        this.categoryJdbcTemplate = categoryJdbcTemplate;
        this.manufacturerJdbcTemplate = manufacturerJdbcTemplate;
        this.productNumberJdbcTemplate = productNumberJdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts(ProductFilterBean productFilterBean) {
        SQLBuilder sqlBuilder = new SQLBuilder(productFilterBean);
        SQLBean sqlBean = sqlBuilder.buildGetAllSql();
        return productJdbcTemplate.executeGetAll(sqlBean.getSqlQuery(), sqlBean.getValues());
    }

    @Override
    public Product getProduct(long id) {
        Object[] values = {id};
        return productJdbcTemplate.executeGetOne(GET_ONE_PRODUCT, values);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        Object[] values = {};
        return manufacturerJdbcTemplate.executeGetAll(GET_ALL_MANUFACTURERS, values);
    }

    @Override
    public List<Category> getAllCategories() {
        Object[] values = {};
        return categoryJdbcTemplate.executeGetAll(GET_ALL_CATEGORIES, values);
    }

    @Override
    public int getProductNumber(ProductFilterBean productFilterBean) {
        SQLBuilder sqlBuilder = new SQLBuilder(productFilterBean);
        SQLBean sqlBean = sqlBuilder.buildGetProductSizeSql();
        return productNumberJdbcTemplate.executeGetAll(sqlBean.getSqlQuery(), sqlBean.getValues()).get(0);
    }
}
