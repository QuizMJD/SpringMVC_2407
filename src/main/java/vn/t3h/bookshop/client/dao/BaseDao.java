package vn.t3h.bookshop.client.dao;

import java.util.List;

public interface  BaseDao<T> {
    List<T> findAll();
    T getById(Long id);
}
