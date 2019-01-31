package com.example.springbootlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.springbootlearning.dao.AccountRepository;
import com.example.springbootlearning.entity.Account;

@SpringBootApplication
public class Sbl07MongodbApplication {

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sbl07MongodbApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            this.accountRepository.deleteAll();

            this.accountRepository.save(new Account("zhangsan", 1000));
            this.accountRepository.save(new Account("wangwu", 2000));

            System.out.println("\n" + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            System.out.println("findAll():\t" + this.accountRepository.findAll());
            System.out.println("getByName(\"wangwu\"):\t" + this.accountRepository.getByName("wangwu"));
            System.out.println("findByMoney(1000):\t" + this.accountRepository.findByMoney(1000));

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\n");
        };
    }
}

