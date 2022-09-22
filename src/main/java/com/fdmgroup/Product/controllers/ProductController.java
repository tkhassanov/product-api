package com.fdmgroup.Product.controllers;

import com.fdmgroup.Product.models.Product;
import com.fdmgroup.Product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    // ============================= Fields ===============================

    private final ProductService productService;

    // =========================== Constructor ============================

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ============================= Methods ==============================

//    @GetMapping
//    public ResponseEntity<List<Product>> getProducts() {
//        List<Product> products = productService.findProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable int id) {
//        Product product = productService.findProduct(id);
//        return ResponseEntity.ok(product);
//    }

    @GetMapping
    public List<Product> getProducts() {
        return  productService.findProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.findProduct(id);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.findProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        product = productService.createProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> removeProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
