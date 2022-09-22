package com.fdmgroup.Product.services;

import com.fdmgroup.Product.exceptions.ProductServiceException;
import com.fdmgroup.Product.models.Product;
import com.fdmgroup.Product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // ============================= Fields ===============================

    private final ProductRepository productRepo;

    // ========================== Constructor =============================

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    // =========================== Methods ================================

    public List<Product> findProducts() {
        return productRepo.findAll();
    }

    public Product findProduct(int id) {
        Optional<Product> optProduct = productRepo.findById(id);

        if (optProduct.isEmpty()) {
            throw new ProductServiceException("Product not found based on id: " + id);
        }
        return optProduct.get();
    }

    public Product findProductByName(String name) {
        Optional<Product> optProduct = productRepo.findByName(name);

        if (optProduct.isEmpty()) {
            throw new ProductServiceException("Product not found based on name: " + name);
        }
        return optProduct.get();
    }

    public Product createProduct(Product product) {
        if (productRepo.existsById(product.getId())) {
            throw new ProductServiceException("Product " + product.getName() + " already exists");
        }
        return productRepo.save(product);
    }

    public void updateProduct(Product product) {
        if (productRepo.existsById(product.getId())) {
            productRepo.save(product);
        }
    }

    public void deleteProduct(int id) {
        productRepo.findById(id);
        productRepo.deleteById(id);
    }
}
