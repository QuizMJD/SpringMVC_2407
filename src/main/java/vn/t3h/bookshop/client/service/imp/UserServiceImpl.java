package vn.t3h.bookshop.client.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.t3h.bookshop.client.dao.UserDao;
import vn.t3h.bookshop.client.model.User;
import vn.t3h.bookshop.client.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation của UserService với quản lý transaction
 */
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
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
     * Phương thức lưu/cập nhật user với transaction
     * Transaction đảm bảo toàn vẹn dữ liệu khi lưu user và các quan hệ liên quan
     */
    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.save(user);
    }

    /**
     * Phương thức xóa user với transaction
     * Transaction đảm bảo toàn vẹn dữ liệu khi xóa user và các quan hệ liên quan
     */
    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> findAllPaginated(int pageNo, int pageSize) {
        return userDao.findAllPaginated(pageNo, pageSize);
    }

    @Override
    public long countUsers() {
        return userDao.count();
    }
}