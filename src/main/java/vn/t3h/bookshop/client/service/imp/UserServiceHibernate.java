package vn.t3h.bookshop.client.service.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.dao.UserDao;
import vn.t3h.bookshop.client.dao.imp.UserDaoHibernate;
import vn.t3h.bookshop.client.dao.util.HibernateTransactionManager;
import vn.t3h.bookshop.client.model.User;
import vn.t3h.bookshop.client.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation của UserService sử dụng Transaction thuần của Hibernate
 */
@Service("userServiceHibernate")
public class UserServiceHibernate implements UserService {

    private final SessionFactory sessionFactory;
    private final UserDaoHibernate userDao;
    private final HibernateTransactionManager transactionManager;

    @Autowired
    public UserServiceHibernate(SessionFactory sessionFactory,
            @Qualifier("userDaoHibernate") UserDao userDao,
            HibernateTransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.userDao = (UserDaoHibernate) userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Phương thức lưu user sử dụng transaction thuần của Hibernate
     * Hiện thực cách 1: Sử dụng transactionManager đã tạo
     */
    @Override
    public User saveUser(User user) {
        return transactionManager.executeInTransaction(session -> {
            // Sử dụng phương thức saveWithSession từ UserDaoHibernate
            return userDao.saveWithSession(user, session);
        });
    }

    /**
     * Phương thức xóa user sử dụng transaction thuần của Hibernate
     * Hiện thực cách 2: Tự quản lý transaction trực tiếp
     */
    @Override
    public boolean deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean result = false;

        try {
            // Bắt đầu transaction
            transaction = session.beginTransaction();

            // Sử dụng phương thức deleteWithSession từ UserDaoHibernate
            result = userDao.deleteWithSession(id, session);

            // Commit transaction
            transaction.commit();
            return result;
        } catch (Exception e) {
            // Rollback transaction nếu có lỗi
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            // Đóng session
            session.close();
        }
    }

    @Override
    public List<User> findAllPaginated(int pageNo, int pageSize) {
        return userDao.findAllPaginated(pageNo, pageSize);
    }

    @Override
    public long countUsers() {
        return userDao.count();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}