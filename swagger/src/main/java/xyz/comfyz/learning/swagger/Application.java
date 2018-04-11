package xyz.comfyz.learning.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@RequestMapping
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String ok() {
        return "welcome";
    }

}
