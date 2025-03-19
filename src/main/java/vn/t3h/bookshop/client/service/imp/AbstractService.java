package vn.t3h.bookshop.client.service.imp;

import lombok.RequiredArgsConstructor;
import vn.t3h.bookshop.client.dao.BaseDao;
import vn.t3h.bookshop.client.service.BaseService;

import java.util.List;

/**
 * Abstract class cung cấp implementation cơ bản cho các service
 * 
 * @param <T> Entity type
 */
@RequiredArgsConstructor
public abstract class AbstractService<T> implements BaseService<T> {

    protected final BaseDao<T> baseDao;

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public T getById(Long id) {
        return baseDao.getById(id);
    }
}