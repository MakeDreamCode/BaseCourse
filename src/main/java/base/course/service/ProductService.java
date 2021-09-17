package base.course.service;

import base.course.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    void delete(Long id);

    Product get(Long id);

    List<Product> findAll();

    List<Product> findAllByBrand(String brand);
}
