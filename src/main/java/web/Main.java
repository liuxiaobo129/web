package web;//import org.example.aop.AppConfig;
//import org.example.aop.MyService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Main {



    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        System.out.println("------------------success---------------");
    }


}


