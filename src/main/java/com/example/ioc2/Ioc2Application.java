package com.example.ioc2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@SpringBootApplication
public class Ioc2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ioc2Application.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        //UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        Encoder encoder = context.getBean("urlEncode",Encoder.class);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        String result = encoder.encode(url);
        System.out.println(result);

    }

}

@Configuration              // 여러가지 Bean 생성
class AppConfig{

    @Bean("base64Encode")   // Bean 으로 만들어서 이름부여해서 사용(중복방지위해서)
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }

    // 이클립스의 jsp:usebean 과 어떤 관계고 어떻게 연결이 되어있는것인지 알아보지!!

}