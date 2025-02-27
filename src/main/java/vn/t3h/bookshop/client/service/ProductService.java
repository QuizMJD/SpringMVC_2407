package vn.t3h.bookshop.client.service;

import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.model.Product;

import java.util.List;

public interface ProductService {
        List<Product> findAll();
        Product getProductById(Long id);

}
