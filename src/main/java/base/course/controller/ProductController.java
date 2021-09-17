package base.course.controller;

import base.course.model.Product;
import base.course.model.dto.ProductRequestDto;
import base.course.model.dto.ProductResponseDto;
import base.course.service.ProductService;
import base.course.service.mapper.ProductDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController //  = @Controller + @ResponseBody
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto create(@RequestBody ProductRequestDto product) {
        return productDtoMapper.parse(productService.save(productDtoMapper.toModel(product)));
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.toModel(requestDto);
        product.setId(id);
        Product updatedProduct = productService.update(product);
        return productDtoMapper.parse(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/")
    public List<ProductResponseDto> getAllProducts() {
        return productService.findAll()
                .stream()
                .map(productDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.parse(productService.get(id));
    }

    @GetMapping("/find")
    public List<ProductResponseDto> getAllByBrand(@RequestParam String brand) {
        return productService.findAllByBrand(brand)
                .stream()
                .map(productDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        Product iPhone = new Product();
        iPhone.setName("iPhone 7");
        iPhone.setBrand("Apple");
        iPhone.setPrice(BigDecimal.valueOf(499));

        Product samsungS20 = new Product();
        samsungS20.setName("Samsung S20");
        samsungS20.setBrand("Samsung");
        samsungS20.setPrice(BigDecimal.valueOf(695));

        Product samsungS10 = new Product();
        samsungS10.setName("Samsung S10");
        samsungS10.setBrand("Samsung");
        samsungS10.setPrice(BigDecimal.valueOf(695));

        productService.save(iPhone);
        productService.save(samsungS10);
        productService.save(samsungS20);

        return "Data was injected!";
    }
}
