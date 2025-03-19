package vn.t3h.bookshop.client.service;

import vn.t3h.bookshop.client.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface cho User
 */
public interface UserService extends BaseService<User> {

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
     * Lưu hoặc cập nhật user (quản lý transaction)
     * 
     * @param user đối tượng user cần lưu/cập nhật
     * @return user đã được lưu/cập nhật
     */
    User saveUser(User user);

    /**
     * Xóa user (quản lý transaction)
     * 
     * @param id ID của user cần xóa
     * @return true nếu xóa thành công, false nếu không
     */
    boolean deleteUser(Long id);

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
    long countUsers();
}