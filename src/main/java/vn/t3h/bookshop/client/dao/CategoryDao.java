package vn.t3h.bookshop.client.dao;

import vn.t3h.bookshop.client.model.Category;

import java.util.List;

/**
 * DAO interface cho danh mục sản phẩm
 */
public interface CategoryDao extends BaseDao<Category> {

    /**
     * Lấy danh mục theo ID
     * 
     * @param id ID của danh mục
     * @return Danh mục tìm được hoặc null nếu không tồn tại
     */
    Category getCategoryById(Long id);
}
