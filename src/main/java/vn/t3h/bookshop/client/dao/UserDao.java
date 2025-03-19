package vn.t3h.bookshop.client.dao;

import vn.t3h.bookshop.client.model.User;
import java.util.List;
import java.util.Optional;

/**
 * DAO Interface cho User
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Tìm user theo username
     * 
     * @param username tên đăng nhập
     * @return Optional của user
     */
    Optional<User> findByUsername(String username);

    /**
     * Tìm user theo email
     * 
     * @param email email của user
     * @return Optional của user
     */
    Optional<User> findByEmail(String email);

    /**
     * Lưu hoặc cập nhật user
     * 
     * @param user đối tượng user cần lưu/cập nhật
     * @return user đã được lưu/cập nhật
     */
    User save(User user);

    /**
     * Xóa user
     * 
     * @param id ID của user cần xóa
     * @return true nếu xóa thành công, false nếu không
     */
    boolean delete(Long id);

    /**
     * Lấy danh sách user theo trang
     * 
     * @param pageNo   số trang
     * @param pageSize kích thước trang
     * @return danh sách user
     */
    List<User> findAllPaginated(int pageNo, int pageSize);

    /**
     * Đếm tổng số user
     * 
     * @return tổng số user
     */
    long count();
}