package vn.t3h.bookshop.client.service;

import java.util.List;

public interface BaseService <T> {
    List<T> findAll();
    T getById(Long id);
}