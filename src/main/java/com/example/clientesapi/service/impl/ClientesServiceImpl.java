package com.example.clientesapi.service.impl;

import com.example.clientesapi.model.Clientes;
import com.example.clientesapi.model.dto.ClientesDTO;
import com.example.clientesapi.repository.ClientesRepository;
import com.example.clientesapi.service.ClientesService;
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
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public Clientes save(Clientes clientes) {
        return this.clientesRepository.save(clientes);
    }

    @Override
    public Clientes findById(Long id) {
        return this.clientesRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.clientesRepository.deleteById(id);
    }

    @Override
    public List<Clientes> findAll(){
        return this.clientesRepository.findAll();
    }

    @Override
    public Page<Clientes> findAll(Integer pageNumber, Integer pageSize, String sortField){
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.ASC, sortField));
        return this.clientesRepository.findAll(pageRequest);
    }

    @Override
    public Page<ClientesDTO> findAll(LocalDate datainicio, LocalDate datafim,String pesquisa,Integer pageNumber, Integer pageSize, String sortField){
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.ASC, sortField));
        return this.clientesRepository.findAll(datainicio,datafim,pesquisa,pageRequest);
    }
}
