package br.com.techallenge4.msclientes.controller;

import br.com.techallenge4.msclientes.exception.ClienteNotFoundException;
import br.com.techallenge4.msclientes.model.Cliente;
import br.com.techallenge4.msclientes.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteCriado = clienteRepository.save(cliente);
        return ResponseEntity.status(201).body(clienteCriado); // Retorna 201 Created com o cliente criado
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
