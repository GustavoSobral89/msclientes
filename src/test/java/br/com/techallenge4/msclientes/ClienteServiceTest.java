package br.com.techallenge4.msclientes;

import br.com.techallenge4.msclientes.model.Cliente;
import br.com.techallenge4.msclientes.repository.ClienteRepository;
import br.com.techallenge4.msclientes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1L, "João", "joao@email.com", "Rua 123");
    }

    @Test
    public void testCreate() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente createdCliente = clienteService.create(cliente);

        assertNotNull(createdCliente);
        assertEquals("João", createdCliente.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testGetById_ClienteExistente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = clienteService.getById(1L);

        assertNotNull(foundCliente);
        assertEquals(1L, foundCliente.getId());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetById_ClienteNaoExistente() {
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        Cliente foundCliente = clienteService.getById(2L);

        assertNull(foundCliente);
        verify(clienteRepository, times(1)).findById(2L);
    }

    @Test
    public void testGetAll() {
        List<Cliente> clientes = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> allClientes = clienteService.getAll();

        assertNotNull(allClientes);
        assertFalse(allClientes.isEmpty());
        assertEquals(1, allClientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testUpdate() {
        Cliente updatedCliente = new Cliente(1L, "João Atualizado", "joaoatualizado@email.com", "Rua 456");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(updatedCliente);

        Cliente result = clienteService.update(1L, updatedCliente);

        assertNotNull(result);
        assertEquals("João Atualizado", result.getNome());
        verify(clienteRepository, times(1)).save(updatedCliente);
    }

    @Test
    public void testDelete() {
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.delete(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
