package vn.t3h.bookshop.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.CategoryService;
import vn.t3h.bookshop.client.service.ProductService;

import java.util.List;

/**
 * Controller xử lý các request liên quan đến trang chủ và hiển thị sản phẩm
 */
@Controller
@RequiredArgsConstructor
public class Homepage {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * Hiển thị trang chủ mặc định
     */
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/home";
    }

    /**
     * Hiển thị trang chủ với danh sách sản phẩm và danh mục
     */
    @GetMapping("/home")
    public String showHomeWithProducts(Model model) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "product/home";
    }

    /**
     * Hiển thị chi tiết một sản phẩm
     * 
     * @param id ID của sản phẩm
     */
    @GetMapping("/home/product/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "product/detail";
    }
}
