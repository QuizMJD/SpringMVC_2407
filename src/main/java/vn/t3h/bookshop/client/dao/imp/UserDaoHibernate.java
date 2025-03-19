package vn.t3h.bookshop.client.dao.imp;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.dao.UserDao;
import vn.t3h.bookshop.client.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation của UserDao sử dụng Hibernate thuần
 */
@Repository("userDaoHibernate")
public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Phương thức này không sử dụng trong ngữ cảnh HibernateTransactionManager
     * Vì session sẽ được truyền vào từ TransactionManager
     */
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Session session = getCurrentSession();
        try {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = getCurrentSession();
        try {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        } finally {
            session.close();
        }
    }

    /**
     * Phương thức này được gọi từ HibernateTransactionManager
     */
    public User saveWithSession(User user, Session session) {
        if (user.getId() == null) {
            // Tạo mới
            LocalDateTime now = LocalDateTime.now();
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            session.persist(user);
        } else {
            // Cập nhật
            user.setUpdatedAt(LocalDateTime.now());
            user.setPassword(session.get(User.class, user.getId()).getPassword());
            session.merge(user);
        }
        return user;
    }

    @Override
    public User save(User user) {
        Session session = getCurrentSession();
        try {
            if (user.getId() == null) {
                // Tạo mới
                LocalDateTime now = LocalDateTime.now();
                user.setCreatedAt(now);
                user.setUpdatedAt(now);
                session.persist(user);
            } else {
                // Cập nhật
                user.setUpdatedAt(LocalDateTime.now());
                session.merge(user);
            }
            return user;
        } finally {
            session.close();
        }
    }

    /**
     * Phương thức này được gọi từ HibernateTransactionManager
     */
    public boolean deleteWithSession(Long id, Session session) {
        User user = session.get(User.class, id);
        if (user != null) {
            // Xóa quan hệ user_roles và các thực thể liên quan
            session.createQuery("DELETE FROM UserRole WHERE user.id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();

            // Xóa identity card
            session.createQuery("DELETE FROM IdentityCard WHERE user.id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();

            // Xóa cart
            session.createQuery("DELETE FROM Cart WHERE user.id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();

            // Xóa user
            session.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Session session = getCurrentSession();
        try {
            User user = session.get(User.class, id);
            if (user != null) {
                // Xóa quan hệ user_roles và các thực thể liên quan
                Query<?> deleteRolesQuery = session.createQuery("DELETE FROM user_roles WHERE user_id = :userId");
                deleteRolesQuery.setParameter("userId", id);
                deleteRolesQuery.executeUpdate();

                // Xóa identity card
                Query<?> deleteIdentityCardQuery = session
                        .createQuery("DELETE FROM identity_cards WHERE user_id = :userId");
                deleteIdentityCardQuery.setParameter("userId", id);
                deleteIdentityCardQuery.executeUpdate();

                // Xóa cart
                Query<?> deleteCartQuery = session.createQuery("DELETE FROM carts WHERE user_id = :userId");
                deleteCartQuery.setParameter("userId", id);
                deleteCartQuery.executeUpdate();

                // Xóa user
                session.remove(user);
                return true;
            }
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAllPaginated(int pageNo, int pageSize) {
        Session session = getCurrentSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            TypedQuery<User> query = session.createQuery(cq);
            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public long count() {
        Session session = getCurrentSession();
        try {
            Query<Long> query = session.createQuery("SELECT COUNT(u) FROM User u", Long.class);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        Session session = getCurrentSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            return session.createQuery(cq).getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public User getById(Long id) {
        Session session = getCurrentSession();
        try {
            return session.get(User.class, id);
        } finally {
            session.close();
        }
    }
}