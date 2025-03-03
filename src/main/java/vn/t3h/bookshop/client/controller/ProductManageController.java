package vn.t3h.bookshop.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.CategoryService;
import vn.t3h.bookshop.client.service.ProductService;

import java.util.List;
@Controller
public class ProductManageController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductManageController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @RequestMapping(value = "/manager/product", method = RequestMethod.GET)// Định nghĩa đừng dẫn
    public String showProductManager(Model model) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product1", products);
        model.addAttribute("categorys", categories);
        return "admin/product";
    }
//    @GetMapping("/home/product/{id}")
//    public String showDetailProduct(Model model, @PathVariable("id") Long id) {
//        Product product = productService.getProductById(id);
//        model.addAttribute("product", product);
//
//        return "product/detail";
//    }

}
