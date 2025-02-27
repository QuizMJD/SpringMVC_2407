package vn.t3h.bookshop.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.t3h.bookshop.client.model.Category;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.CategoryService;
import vn.t3h.bookshop.client.service.ProductService;


import java.util.List;

@Controller// Đánh dấu nó là 1 controller 1-n servlet
public class Homepage {
    private final ProductService productService;
    private final CategoryService categoryService;
    public Homepage(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;

    }
//    @RequestMapping("/home2")// Định nghĩa đừng dẫn
//    public ModelAndView showHome_2() {
//        System.out.println("THis is home url");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("product/home");
//        modelAndView.addObject("message", "Hello 2");
//        return modelAndView;
//    }
//
//
//
//    @RequestMapping("redirect_1")// Định nghĩa đừng dẫn
//    public String rediect_1(Model model, @RequestParam String message) {
//        model.addAttribute("message", message);
//        return "product/home";
//    }
//
//    @RequestMapping("redirect_2")// Định nghĩa đừng dẫn
//    public String rediect_2(RedirectAttributes redirectAttributes, @RequestParam String message) {
//        redirectAttributes.addAttribute("message", message);
//        return "redirect:/redirect_1";
//    }


    @RequestMapping("/")// Định nghĩa đừng dẫn
    public String showHome_1() {
        System.out.println("THis is home url");
        return "product/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)// Định nghĩa đừng dẫn
    public String showHome_2(Model model) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product1", products);
        model.addAttribute("category1", categories);

        return "product/home";
    }
    @GetMapping("/home/product/{id}")
    public String showDetailProduct(Model model, @PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "product/detail";
    }




    
}
