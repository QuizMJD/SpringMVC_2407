package vn.t3h.bookshop.client.service.imp;

import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.dao.CategoryDao;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.service.CategoryService;

/**
 * Implementation của CategoryService
 */
@Service
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {

    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
    }
}
