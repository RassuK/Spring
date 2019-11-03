package com.example.clientsmanagement;

import com.example.clientsmanagement.model.Client;
import com.example.clientsmanagement.model.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    Collection<Client> clients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    ResponseEntity<?> getClient(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/client")
    ResponseEntity<Client> createClient(@Valid @RequestBody Client client) throws URISyntaxException {
        log.info("Request to create client: {}", client);
        Client result = clientRepository.save(client);
        return ResponseEntity.created(new URI("/api/client/" + result.getId()))
                .body(result);
    }

    @PutMapping("/client")
    ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) {
        log.info("Request to update client: {}", client);
        Client result = clientRepository.save(client);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.info("Request to delete client: {}", id);
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}