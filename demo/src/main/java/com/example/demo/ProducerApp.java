package com.example.demo;

public class ProducerApp {

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();
        producer.sendMessage("mssss");
    }
}
