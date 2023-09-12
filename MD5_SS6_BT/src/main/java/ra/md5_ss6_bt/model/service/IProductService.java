package ra.md5_ss6_bt.model.service;



import ra.md5_ss6_bt.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deleteById(Long id);
}