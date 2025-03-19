package vn.t3h.bookshop.client.service;

import java.util.List;

/**
 * Interface cơ sở cho các service, định nghĩa các phương thức CRUD cơ bản
 * 
 * @param <T> Entity type
 */
public interface BaseService<T> {

    /**
     * Lấy tất cả các entity
     * 
     * @return Danh sách các entity
     */
    List<T> findAll();

    /**
     * Lấy entity theo ID
     * 
     * @param id ID của entity cần tìm
     * @return Entity tìm được hoặc null nếu không tồn tại
     */
    T getById(Long id);
}