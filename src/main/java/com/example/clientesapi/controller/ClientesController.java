package com.example.clientesapi.controller;

import com.example.clientesapi.model.ApiResponse;
import com.example.clientesapi.model.Clientes;
import com.example.clientesapi.model.dto.ClientesDTO;
import com.example.clientesapi.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping
    public ApiResponse<Clientes> save(@RequestBody Clientes clientes) {

        if(clientes.getNome() == null || clientes.getNome().isEmpty()) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"O nome deve ser informado. Verifique!",null);
        } else if (clientes.getTelefone() == null || clientes.getTelefone().isEmpty()) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"O telefone deve ser informado. Verifique!",null);
        }

        return new ApiResponse<>(HttpStatus.CREATED.value(),"Cliente salvo com sucesso!",this.clientesService.save(clientes));
    }

    @GetMapping("/listlike")
    public ApiResponse<Page<ClientesDTO>> findAll(@RequestParam(value = "datainicio",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
                                                  @RequestParam(value = "datafim",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim,
                                                  @RequestParam(value = "pesquisa",required = false,defaultValue = "")String pesquisa,
                                                  @RequestParam(value = "size",required = false,defaultValue = "10")Integer size,
                                                  @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                                  @RequestParam(value = "sort",required = false,defaultValue = "id")String sort){

        Page<ClientesDTO> result = this.clientesService.findAll(dataInicio,dataFim,pesquisa,page,size,sort);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!",result);
    }

    @GetMapping
    public ApiResponse<Page<Clientes>> findAll(@RequestParam(value = "size",required = false,defaultValue = "10")Integer size,
                                               @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                               @RequestParam(value = "sort",required = false,defaultValue = "id")String sort){

        Page<Clientes> result = this.clientesService.findAll(page,size,sort);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!",result);
    }

    @GetMapping("/list")
    public ApiResponse<List<Clientes>> findAll(){

        List<Clientes> result = this.clientesService.findAll();
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!",result);
    }

    @GetMapping("/{id}")
    public ApiResponse<Clientes> findById(@PathVariable("id") Long id){

        Clientes cliente = this.clientesService.findById(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", cliente);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Clientes> delete(@PathVariable("id") Long id){

        this.clientesService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Cliente deletado com sucesso!", null);
    }
}
