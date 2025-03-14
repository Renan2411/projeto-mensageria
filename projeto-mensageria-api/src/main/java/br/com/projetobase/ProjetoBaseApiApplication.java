package br.com.projetobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoBaseApiApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ProjetoBaseApiApplication.class);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}