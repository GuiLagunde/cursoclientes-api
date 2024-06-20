package com.example.clientesapi.service.impl;

import com.example.clientesapi.model.TrabalhosRealizados;
import com.example.clientesapi.repository.TrabalhosRealizadosRepository;
import com.example.clientesapi.service.TrabalhosRealizadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TrabalhosRealizadosServiceImpl implements TrabalhosRealizadosService {

    @Autowired
    private TrabalhosRealizadosRepository trabalhosRealizadosRepository;

    @Override
    public TrabalhosRealizados save(TrabalhosRealizados trabalhosRealizados){
        return this.trabalhosRealizadosRepository.save(trabalhosRealizados);
    }

    @Override
    public Page<TrabalhosRealizados> findAll(Long idCliente, LocalDate dataInicio, LocalDate dataFim, String pesquisa,
                                                                    Integer pageNumber, Integer pageSize, String sortField){
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.DESC, sortField));
        return this.trabalhosRealizadosRepository.findAll(dataInicio,dataFim,idCliente,pesquisa,pageRequest);
    }

    @Override
    public Page<TrabalhosRealizados> findAll(Integer pageNumber, Integer pageSize, String sortField){
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.DESC, sortField));
        return this.trabalhosRealizadosRepository.findAll(pageRequest);
    }

    @Override
    public List<TrabalhosRealizados> findAll(){
        return this.trabalhosRealizadosRepository.findAll();
    }

    @Override
    public TrabalhosRealizados findById(Long id){
        return this.trabalhosRealizadosRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){
        this.trabalhosRealizadosRepository.deleteById(id);
    }
}
