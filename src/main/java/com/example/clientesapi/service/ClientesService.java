package com.example.clientesapi.service;

import com.example.clientesapi.model.Clientes;
import com.example.clientesapi.model.dto.ClientesDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ClientesService {
    Clientes save(Clientes clientes);
    Clientes findById(Long id);
    void delete(Long id);
    List<Clientes> findAll();
    Page<Clientes> findAll(Integer pageNumber, Integer pageSize, String sortField);
    Page<ClientesDTO> findAll(LocalDate datainicio, LocalDate datafim, String pesquisa, Integer pageNumber, Integer pageSize, String sortField);
}
