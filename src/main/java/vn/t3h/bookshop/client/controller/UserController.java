package vn.t3h.bookshop.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.bookshop.client.model.User;
import vn.t3h.bookshop.client.service.UserService;

import java.util.List;

/**
 * Controller xử lý các request liên quan đến quản lý user
 */
@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final int PAGE_SIZE = 10;

    /**
     * Hiển thị danh sách users
     */
    @GetMapping
    public String listUsers(Model model,
            @RequestParam(defaultValue = "1") int page) {
        List<User> users = userService.findAllPaginated(page, PAGE_SIZE);
        long totalUsers = userService.countUsers();
        long totalPages = (totalUsers + PAGE_SIZE - 1) / PAGE_SIZE;

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/users/list";
    }

    /**
     * Hiển thị form tạo user mới
     */
    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/users/form";
    }

    /**
     * Hiển thị form chỉnh sửa user
     */
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "admin/users/form";
    }

    /**
     * Lưu user (cả tạo mới và cập nhật)
     */
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,
            RedirectAttributes redirectAttributes) {
        try {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage",
                    user.getId() == null ? "Tạo user mới thành công!" : "Cập nhật user thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    /**
     * Xem chi tiết user
     */
    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "admin/users/view";
    }

    /**
     * Xóa user
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("successMessage", "Xóa user thành công!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy user!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Lỗi khi xóa user: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }
}