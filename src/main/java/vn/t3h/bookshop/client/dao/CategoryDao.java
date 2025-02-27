package vn.t3h.bookshop.client.dao;

import vn.t3h.bookshop.client.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category>findAll();
}
