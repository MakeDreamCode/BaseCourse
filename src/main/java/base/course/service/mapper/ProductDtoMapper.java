package base.course.service.mapper;

import base.course.model.Product;
import base.course.model.dto.ProductRequestDto;
import base.course.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductResponseDto parse(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }

    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setBrand(requestDto.getBrand());
        product.setPrice(requestDto.getPrice());
        return product;
    }
}
