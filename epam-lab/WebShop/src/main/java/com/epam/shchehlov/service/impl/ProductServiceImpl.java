package com.epam.shchehlov.service.impl;

import com.epam.shchehlov.bean.ProductFilterBean;
import com.epam.shchehlov.database.transaction.TransactionManager;
import com.epam.shchehlov.database.transaction.impl.JdbcTransactionManager;
import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.exception.ObjectNotFoundException;
import com.epam.shchehlov.repository.ProductRepository;
import com.epam.shchehlov.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TransactionManager transactionManager;

    public ProductServiceImpl(ProductRepository productRepository, JdbcTransactionManager jdbcTransactionManager) {
        this.productRepository = productRepository;
        this.transactionManager = jdbcTransactionManager;
    }

    @Override
    public List<Product> getAllProducts(ProductFilterBean productFilterBean) {
        return transactionManager.doInquiryTransaction(() -> productRepository.getAllProducts(productFilterBean));
    }

    @Override
    public Product getProduct(long id) {
        Product product = transactionManager.doInquiryTransaction(() -> productRepository.getProduct(id));
        if (product == null) {
            throw new ObjectNotFoundException("product not found by id " + id);
        }
        return product;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return transactionManager.doInquiryTransaction(productRepository::getAllManufacturers);
    }

    @Override
    public List<Category> getAllCategories() {
        return transactionManager.doInquiryTransaction(productRepository::getAllCategories);
    }

    @Override
    public int getProductNumber(ProductFilterBean productFilterBean) {
        return transactionManager.doInquiryTransaction(() -> productRepository.getProductNumber(productFilterBean));
    }
}
