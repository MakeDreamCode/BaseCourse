package base.course.dao;

import base.course.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(Product product);

    Optional<Product> get(Long id);

    List<Product> findAll();

    List<Product> findAllByBrand(String brand);

    void delete(Long id);
}
