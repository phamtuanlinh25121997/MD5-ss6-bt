package ra.md5_ss6_bt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import ra.md5_ss6_bt.model.entity.CartItem;
import ra.md5_ss6_bt.model.entity.Order;

@Controller
public class CartController {
    @ModelAttribute("cart")
    public CartItem setupCart(){
        return new CartItem();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute("cart") CartItem cart){
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }

    @GetMapping("/payment")
    public String payment(@SessionAttribute("cart") CartItem cart, Model model){
        Order order = new Order();
        order.copyCart(cart.getProducts());
        order.setTotal(cart.countTotalPayment());
        model.addAttribute("order", order);
        cart.clearCart();
        return "orderDetail";
    }
}
