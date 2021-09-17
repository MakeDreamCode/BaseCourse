package base.course.service;

import base.course.model.Product;
import base.course.model.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    List<Product> findAll();

    List<Product> findAllByBrand(String brand);

    void delete(Long id);
}
