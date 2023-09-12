package ra.md5_ss6_bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md5_ss6_bt.model.entity.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}