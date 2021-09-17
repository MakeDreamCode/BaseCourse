package base.course.controller;

import base.course.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class DemoController {
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring!");
        return "index";
    }

    @ResponseBody
    @GetMapping("/time")
    public String getTime(){
        return LocalDateTime.now().toString();
    }
}
