//package vn.t3h.bookshop.client.service.imp;
//
//import vn.t3h.bookshop.client.dao.BaseDao;
//import vn.t3h.bookshop.client.service.BaseService;
//
//import java.util.List;
//
//public abstract class AbstractService <T> implements BaseService<T> {
//
//    protected BaseDao<T> baseDao;
//
//    public AbstractService(BaseDao<T> baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    @Override
//    public List<T> findAll() {
//        return baseDao.findAll();
//    }
//
//    @Override
//    public T getById(Long id) {
//        return baseDao.getById(id);
//    }
//}