package br.com.techallenge4.msclientes.repository;

import br.com.techallenge4.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}