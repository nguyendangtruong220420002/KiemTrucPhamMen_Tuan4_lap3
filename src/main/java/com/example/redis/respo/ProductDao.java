package com.example.redis.respo;


import com.example.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    public static final String HASH_KEY = "Product";
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Product save(Product product){
        String idAsString = String.valueOf(product.getId());
        template.opsForHash().put(HASH_KEY,idAsString,product);
        return product;
    }

    public List<Product> finAll(){
        return  template.opsForHash().values(HASH_KEY);
    }
    public Product findProductByiId(int id){
        String idAsString = String.valueOf(id);
        return (Product) template.opsForHash().get(HASH_KEY,idAsString);
    }
    public String deleteProduct(int id){
        String idAsString = String.valueOf(id);
        template.opsForHash().delete(HASH_KEY, idAsString);
        return "Product đã xóa !!";
    }
    public Product updateProduct(Product updatedProduct) {
        String idAsString = String.valueOf(updatedProduct.getId());

        // Check if the product with the given ID exists before updating
        if (template.opsForHash().hasKey(HASH_KEY, idAsString)) {
            template.opsForHash().put(HASH_KEY, idAsString, updatedProduct);
            return updatedProduct;
        } else {
            // Handle the case where the product with the given ID doesn't exist
            // You can throw an exception, return null, or handle it as per your requirement.
            throw new RuntimeException("Product with ID " + updatedProduct.getId() + " not found for update");
        }
    }
}
