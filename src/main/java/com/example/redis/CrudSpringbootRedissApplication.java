package com.example.redis;

import com.example.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.example.redis.respo.ProductDao;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class CrudSpringbootRedissApplication {
    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return dao.finAll();
    }
    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id){
        return dao.findProductByiId(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id){
        return dao.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updataProduct){
        updataProduct.setId(id);
        return dao.updateProduct(updataProduct);
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringbootRedissApplication.class, args);
    }

}
