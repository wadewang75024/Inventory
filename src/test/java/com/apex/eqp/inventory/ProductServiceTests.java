package com.apex.eqp.inventory;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.services.ProductService;
import com.apex.eqp.inventory.services.RecalledProductService;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@SpringBootTest
public class ProductServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    RecalledProductService recalledProductService;

    /**
     * Helper method to create test products
     */
    private Product createTestProduct(String productName, Double price, Integer quantity) {
        return Product.builder()
                .name(productName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    /**
     * Helper method to create test recalled products
     */
    private RecalledProduct createTestRecalledProduct(String recalledProductName) {
        return RecalledProduct.builder()
                .name(recalledProductName)
                .build();
    }

    @DisplayName("1")
    @Test
    public void shouldSaveProduct() {
        Product product = createTestProduct("product1", 1.2, 2);

        Product savedProduct = productService.save(product);

        Product loadedProduct = productService.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(loadedProduct);
        Assertions.assertNotNull(loadedProduct.getId());
    }

    @DisplayName("2")
    @Test
    public void shouldUpdateProduct() {
        Product product = createTestProduct("product2", 1.3, 5);

        Product savedProduct = productService.save(product);

        Product loadedProduct = productService.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(loadedProduct);

        loadedProduct.setName("NewProduct");

        productService.save(loadedProduct);

        Assertions.assertNotNull(productService.findById(loadedProduct.getId()).orElse(null));
    }
    
    @DisplayName("3")
    @Test
    public void shouldGetAllProducts() {
        Product product3 = createTestProduct("product3", 1.3, 5);
        Product product4 = createTestProduct("product4", 1.3, 5);

        Product savedProduct3 = productService.save(product3);
        Product savedProduct4 = productService.save(product4);
        
        Collection<Product> allProducts = productService.getAllProduct();

        Assertions.assertNotNull(allProducts);
        Assertions.assertEquals(4, allProducts.size());
    }
    
    @DisplayName("4")
    @Test
    public void shouldGetAllProductsWithoutRecalledProducts() {
        Product product5 = createTestProduct("product5", 1.3, 5);
        Product product6 = createTestProduct("product6", 1.3, 5);

        Product savedProduct5 = productService.save(product5);
        Product savedProduct6 = productService.save(product6);
        
        RecalledProduct recalledProduct = new RecalledProduct();
        recalledProduct.setName("product1");    
        recalledProductService.save(recalledProduct);
        
        Collection<Product> allProducts = productService.getAllProduct();

        Assertions.assertNotNull(allProducts);
        Assertions.assertEquals(5, allProducts.size());
    }
}
