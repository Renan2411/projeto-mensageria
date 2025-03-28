package br.com.projetomensageria.application.mensageria;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println(String.format("Received <%s>", message));
        latch.countDown();
    }

//   a

    public CountDownLatch getLatch() {
        return latch;
    }

}
