package base.course.dao;

import base.course.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(Product product);

    Product update(Product product);

    void delete(Long id);

    Optional<Product> get(Long id);

    List<Product> findAll();

    List<Product> findAllByBrand(String brand);
}
