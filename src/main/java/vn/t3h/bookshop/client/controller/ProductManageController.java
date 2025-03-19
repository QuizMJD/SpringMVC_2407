package vn.t3h.bookshop.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.CategoryService;
import vn.t3h.bookshop.client.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller xử lý các request liên quan đến quản lý sản phẩm
 */
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ProductManageController {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * Hiển thị trang quản lý sản phẩm
     */
    @GetMapping("/product")
    public String showProductManager(Model model) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "admin/product";
    }
}
