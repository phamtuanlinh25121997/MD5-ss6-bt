package ra.md5_ss6_bt.model.entity;

import java.util.HashMap;
import java.util.Map;

public class CartItem {
    private Map<Product, Integer> products = new HashMap<>();

    private boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            assert itemEntry != null;
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(), newQuantity);
        }
    }

    public void removeProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            assert itemEntry != null;
            int newQuantity = itemEntry.getValue() - 1;
            if (itemEntry.getValue() == 0) {
                newQuantity = 0;
            }
            products.replace(itemEntry.getKey(), newQuantity);
        }
    }

    public void deleteProduct(Product product) {
        products.remove(product);

    }

    public Integer countProductQuantity() {
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity() {
        return products.size();
    }

    public Float countTotalPayment() {
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += (float) (entry.getKey().getPrice() * (float) entry.getValue());
        }
        return payment;
    }

    public void clearCart() {
        products.clear();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}