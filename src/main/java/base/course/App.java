package base.course;

import base.course.config.AppConfig;
import base.course.model.Product;
import base.course.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean(ProductService.class);

        List<Product> products = productService.findAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }

//        context.close();
    }
}
