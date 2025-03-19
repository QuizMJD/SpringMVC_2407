package vn.t3h.bookshop.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.t3h.bookshop.client.model.User;
import vn.t3h.bookshop.client.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserHibernateController {

    private static final String UPLOAD_DIR = "uploads/avatars/";
    private final UserService userService;

    @Autowired
    public UserHibernateController(@Qualifier("userServiceHibernate") UserService userService) {
        this.userService = userService;
        // Tạo thư mục uploads nếu chưa tồn tại
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục uploads: " + e.getMessage());
        }
    }

    private static class PaginatedResponse {
        public final List<User> data;
        public final int currentPage;
        public final int totalItems;
        public final int totalPages;

        public PaginatedResponse(List<User> data, int currentPage, int totalItems, int totalPages) {
            this.data = data;
            this.currentPage = currentPage;
            this.totalItems = totalItems;
            this.totalPages = totalPages;
        }
    }

    // ============ VIEW ENDPOINTS ============

    @GetMapping
    public String listUsersView(Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.findAllPaginated(page - 1, size);
        long count = userService.countUsers();
        int totalPages = (int) Math.ceil((double) count / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", count);

        return "user/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user/form";
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user/view";
    }

    // ============ REST API ENDPOINTS ============

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<PaginatedResponse> listUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.findAllPaginated(page - 1, size);
        long count = userService.countUsers();
        int totalPages = (int) Math.ceil((double) count / size);

        PaginatedResponse response = new PaginatedResponse(users, page, (int) count, totalPages);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<User> createUser(
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @ModelAttribute User user) {

        // Xử lý upload avatar nếu có
        if (avatar != null && !avatar.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + avatar.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.write(filePath, avatar.getBytes());
                user.setAvatarUrl("/uploads/avatars/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi upload avatar: " + e.getMessage());
            }
        }

        // Set thời gian tạo và cập nhật
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setActive(true);

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @ModelAttribute User user) {

        User existingUser = userService.getById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Xử lý upload avatar mới nếu có
        if (avatar != null && !avatar.isEmpty()) {
            try {
                // Xóa avatar cũ nếu có
                if (existingUser.getAvatarUrl() != null) {
                    Path oldAvatar = Paths.get(existingUser.getAvatarUrl().replace("/uploads/avatars/", UPLOAD_DIR));
                    Files.deleteIfExists(oldAvatar);
                }

                // Upload avatar mới
                String fileName = UUID.randomUUID().toString() + "_" + avatar.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.write(filePath, avatar.getBytes());
                existingUser.setAvatarUrl("/uploads/avatars/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi upload avatar: " + e.getMessage());
            }
        }

        // Cập nhật thông tin
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setAddress(user.getAddress());
        existingUser.setActive(user.isActive());
        existingUser.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userService.saveUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Xóa avatar nếu có
        if (user.getAvatarUrl() != null) {
            try {
                Path avatarPath = Paths.get(user.getAvatarUrl().replace("/uploads/avatars/", UPLOAD_DIR));
                Files.deleteIfExists(avatarPath);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi xóa avatar: " + e.getMessage());
            }
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/{id}/toggle-active")
    @ResponseBody
    public ResponseEntity<User> toggleUserActive(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setActive(!user.isActive());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/api/search")
    @ResponseBody
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {

        if (username != null && !username.isEmpty()) {
            return ResponseEntity.ok(List.of(userService.findByUsername(username).orElse(null)));
        }

        if (email != null && !email.isEmpty()) {
            return ResponseEntity.ok(List.of(userService.findByEmail(email).orElse(null)));
        }

        return ResponseEntity.badRequest().build();
    }
}