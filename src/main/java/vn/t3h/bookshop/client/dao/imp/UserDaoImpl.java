package vn.t3h.bookshop.client.dao.imp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.dao.UserDao;
import vn.t3h.bookshop.client.model.User;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implemention của UserDao sử dụng JdbcTemplate
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, User.class);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[] { username },
                    new BeanPropertyRowMapper<>(User.class));
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[] { email },
                    new BeanPropertyRowMapper<>(User.class));
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            // Tạo mới
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO users (username, password, email, created_at, updated_at) VALUES (:username, :password, :email, NOW(), NOW())";
            SqlParameterSource params = new BeanPropertySqlParameterSource(user);
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "id" });
            user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            // Cập nhật
            String sql = "UPDATE users SET username = :username, password = :password, email = :email, updated_at = NOW() WHERE id = :id";
            SqlParameterSource params = new BeanPropertySqlParameterSource(user);
            namedParameterJdbcTemplate.update(sql, params);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        // Xóa quan hệ user_roles trước
        String deleteRolesSql = "DELETE FROM user_roles WHERE user_id = :id";
        namedParameterJdbcTemplate.update(deleteRolesSql, params);

        // Xóa identity_card nếu có
        String deleteIdentityCardSql = "DELETE FROM identity_cards WHERE user_id = :id";
        namedParameterJdbcTemplate.update(deleteIdentityCardSql, params);

        // Xóa carts nếu có
        String deleteCartsSql = "DELETE FROM carts WHERE user_id = :id";
        namedParameterJdbcTemplate.update(deleteCartsSql, params);

        // Xóa user
        String deleteUserSql = "DELETE FROM users WHERE id = :id";
        int affectedRows = namedParameterJdbcTemplate.update(deleteUserSql, params);

        return affectedRows > 0;
    }

    @Override
    public List<User> findAllPaginated(int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        String sql = "SELECT * FROM users ORDER BY id LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[] { pageSize, offset }, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Long.class);
    }
}