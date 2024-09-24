package vn.t3h.bookshop.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.bookshop.client.domain.Product;
import vn.t3h.bookshop.client.repository.ProductRepository;


import java.util.List;

@Controller// Đánh dấu nó là 1 controller 1-n servlet
public class Homepage {
    private final ProductRepository productRepository;

    public Homepage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @RequestMapping("/home2")// Định nghĩa đừng dẫn
    public ModelAndView showHome_2() {
        System.out.println("THis is home url");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("message", "Hello 2");
        return modelAndView;
    }



    @RequestMapping("redirect_1")// Định nghĩa đừng dẫn
    public String rediect_1(Model model, @RequestParam String message) {
        model.addAttribute("message", message);
        return "home";
    }

    @RequestMapping("redirect_2")// Định nghĩa đừng dẫn
    public String rediect_2(RedirectAttributes redirectAttributes, @RequestParam String message) {
        redirectAttributes.addAttribute("message", message);
        return "redirect:/redirect_1";
    }


    @RequestMapping("/")// Định nghĩa đừng dẫn
    public String showHome_1() {
        System.out.println("THis is home url");
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)// Định nghĩa đừng dẫn
    public String showHome_2(Model model) {
        List<Product> products = productRepository.getAllProducts();
        model.addAttribute("product1", products);
        return "home";
    }
    @GetMapping("/home/product/{id}")
    public String showDetailProduct(Model model, @PathVariable("id") Long id) {
        Product product = productRepository.getProductById(id);
        model.addAttribute("product", product);

        return"detail";
    }




    
}
