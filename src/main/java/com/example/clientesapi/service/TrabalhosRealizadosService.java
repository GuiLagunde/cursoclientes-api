package com.example.clientesapi.service;

import com.example.clientesapi.model.TrabalhosRealizados;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface TrabalhosRealizadosService {
    TrabalhosRealizados save(TrabalhosRealizados trabalhosRealizados);

    Page<TrabalhosRealizados> findAll(Long idCliente, LocalDate dataInicio, LocalDate dataFim, String pesquisa,
                                      Integer pageNumber, Integer pageSize, String sortField);

    Page<TrabalhosRealizados> findAll(Integer pageNumber, Integer pageSize, String sortField);

    List<TrabalhosRealizados> findAll();

    TrabalhosRealizados findById(Long id);

    void delete(Long id);
}
