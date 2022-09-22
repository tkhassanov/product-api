package com.fdmgroup.Product.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Component
@Scope("prototype")
@Entity
@Table(name="products")
public class Product {

    // ============================= Fields ===============================

    @Id
    @SequenceGenerator(name="seq_product", sequenceName="product_id_seq_jpa", initialValue=50_001, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_product")
    private int id;
    @NotBlank(message = "Product needs to have a non-blank name")
    private String name;
    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    // ============================= Getters&Setters ===============================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // ============================= Overrides ===============================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (!name.equals(product.name)) return false;
        return price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
