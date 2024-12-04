package br.com.techallenge4.msclientes;

import br.com.techallenge4.msclientes.controller.ClienteController;
import br.com.techallenge4.msclientes.model.Cliente;
import br.com.techallenge4.msclientes.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(1L, "João Silva", "joao.silva@email.com", "Rua A, 123");
    }

    @Test
    public void testCreateCliente() throws Exception {
        // Mockando o comportamento do repositório
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Realizando a requisição POST
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.email").value("joao.silva@email.com"))
                .andExpect(jsonPath("$.endereco").value("Rua A, 123"));
    }

    @Test
    public void testGetClienteById() throws Exception {
        // Mockando o comportamento do repositório
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Realizando a requisição GET
        mockMvc.perform(get("/clientes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.email").value("joao.silva@email.com"))
                .andExpect(jsonPath("$.endereco").value("Rua A, 123"));
    }

    @Test
    public void testGetClienteByIdNotFound() throws Exception {
        // Mockando o comportamento do repositório para não encontrar o cliente
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        // Realizando a requisição GET
        mockMvc.perform(get("/clientes/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllClientes() throws Exception {
        // Mockando o comportamento do repositório
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        // Realizando a requisição GET
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].email").value("joao.silva@email.com"))
                .andExpect(jsonPath("$[0].endereco").value("Rua A, 123"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        // Mockando o comportamento do repositório
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Atualizando o cliente
        cliente.setNome("João Silva Atualizado");

        // Realizando a requisição PUT
        mockMvc.perform(put("/clientes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        // Mockando o comportamento do repositório
        doNothing().when(clienteRepository).deleteById(1L);

        // Realizando a requisição DELETE
        mockMvc.perform(delete("/clientes/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verificando se o método delete foi chamado
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}