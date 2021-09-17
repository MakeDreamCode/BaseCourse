package base.course.service.impl;

import base.course.dao.ProductDao;
import base.course.model.Product;
import base.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findAllByBrand(String brand) {
        return productDao.findAllByBrand(brand);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }
}
