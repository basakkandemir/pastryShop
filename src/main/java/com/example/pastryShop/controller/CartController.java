package com.example.pastryShop.controller;

import com.example.pastryShop.cart.CartProcess;
import com.example.pastryShop.model.Product;
import com.example.pastryShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        CartProcess.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("cartCount", CartProcess.cart.size());
        model.addAttribute("total", CartProcess.cart.stream().mapToDouble(Product::getPrice));
        model.addAttribute("cart", CartProcess.cart);
        return "cart";
    }
}
