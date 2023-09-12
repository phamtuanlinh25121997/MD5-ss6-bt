package ra.md5_ss6_bt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.md5_ss6_bt.model.entity.CartItem;
import ra.md5_ss6_bt.model.entity.Product;
import ra.md5_ss6_bt.model.service.IProductService;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public CartItem setupCart() {
        return new CartItem();
    }

    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    public ModelAndView showDetail(@PathVariable Long id){
        return new ModelAndView("detail","product",productService.findById(id).get());
    }
    public String deleteInCart(@PathVariable Long id,@ModelAttribute CartItem cart){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "404";
        }
        cart.deleteProduct(productOptional.get());
        return "redirect:/shopping-cart";
    }
    public String addToCart(@PathVariable Long id, @ModelAttribute CartItem cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "404";
        }
        if (action.equals("increase")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        } else if (action.equals("decrease")) {
            cart.removeProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }

        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

}