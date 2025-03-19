package vn.t3h.bookshop.client.dao;

import vn.t3h.bookshop.client.model.Product;

/**
 * DAO interface cho sản phẩm
 */
public interface ProductDao extends BaseDao<Product> {

    /**
     * Lấy sản phẩm theo ID
     * 
     * @param id ID của sản phẩm
     * @return Sản phẩm tìm được hoặc null nếu không tồn tại
     */
    Product getProductById(Long id);
}
