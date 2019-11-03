package com.example.clientsmanagement;

import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final ClientRepository repository;

    public Initializer(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Joosep", "Juhan", "Madis",
                "Anet").forEach(name ->
                repository.save(new Client(name))
        );

        repository.findAll().forEach(System.out::println);
    }
}