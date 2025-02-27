package vn.t3h.bookshop.client.service.imp;

import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.dao.CategoryDao;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.service.CategoryService;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
