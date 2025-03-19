package vn.t3h.bookshop.client.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Utility class để quản lý transaction của Hibernate
 */
@Component
public class HibernateTransactionManager {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateTransactionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Thực thi một function trong transaction và trả về kết quả
     * 
     * @param function Function nhận vào Session và trả về kết quả kiểu T
     * @return Kết quả kiểu T
     * @param <T> Kiểu dữ liệu trả về
     */
    public <T> T executeInTransaction(Function<Session, T> function) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Bắt đầu transaction
            transaction = session.beginTransaction();

            // Thực thi function và lấy kết quả
            T result = function.apply(session);

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

    /**
     * Thực thi một runnable trong transaction không cần trả về kết quả
     * 
     * @param runnable SessionRunnable thực thi trên session
     */
    public void executeInTransaction(SessionRunnable runnable) {
        executeInTransaction(session -> {
            runnable.run(session);
            return null;
        });
    }

    /**
     * Functional interface cho các operation không trả về kết quả
     */
    @FunctionalInterface
    public interface SessionRunnable {
        void run(Session session);
    }

    /**
     * Lấy session hiện tại
     *
     * @return Session hiện tại
     */
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}