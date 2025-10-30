package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Cliente;
import com.delivery_api.Projeto.Delivery.API.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um serviço do Spring
public class ClienteService {

    @Autowired // Injeta automaticamente o ClienteRepository
    private ClienteRepository clienteRepository;

    // Cadastrar um novo cliente
    public Cliente cadastrar(Cliente cliente) {
        // Validação simples: não permitir e-mail vazio
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O e-mail do cliente é obrigatório");
        }

        // Verificar se o e-mail já existe
        Optional<Cliente> existente = clienteRepository.findAll()
                .stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(cliente.getEmail()))
                .findFirst();
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com esse e-mail");
        }

        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Buscar cliente por e-mail
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Atualizar cliente
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> existente = clienteRepository.findById(id);
        if (!existente.isPresent()) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }

        Cliente cliente = existente.get();
        cliente.setNome(clienteAtualizado.getNome() != null ? clienteAtualizado.getNome() : cliente.getNome());
        cliente.setEmail(clienteAtualizado.getEmail() != null ? clienteAtualizado.getEmail() : cliente.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone() != null ? clienteAtualizado.getTelefone() : cliente.getTelefone());

        return clienteRepository.save(cliente);
    }

    // Deletar cliente
    public boolean deletar(Long id) {
        Optional<Cliente> existente = clienteRepository.findById(id);
        if (existente.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
