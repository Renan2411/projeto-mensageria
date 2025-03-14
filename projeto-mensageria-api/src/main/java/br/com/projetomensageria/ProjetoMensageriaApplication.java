package br.com.projetomensageria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoMensageriaApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ProjetoMensageriaApplication.class);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}