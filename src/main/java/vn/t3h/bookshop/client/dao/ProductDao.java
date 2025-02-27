package vn.t3h.bookshop.client.dao;

import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product getProductById(Long id);
}
