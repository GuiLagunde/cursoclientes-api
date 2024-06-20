package com.example.clientesapi.controller;

import com.example.clientesapi.model.ApiResponse;
import com.example.clientesapi.model.TrabalhosRealizados;
import com.example.clientesapi.service.TrabalhosRealizadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trabalhos")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TrabalhosRealizadosController {

    @Autowired
    private TrabalhosRealizadosService trabalhosRealizadosService;

    @PostMapping
    public ApiResponse<TrabalhosRealizados> save(@RequestBody TrabalhosRealizados trabalhosRealizados){

        if(trabalhosRealizados.getDescricao() == null || trabalhosRealizados.getDescricao().isEmpty()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "A descrição deve ser informada. Verifique!",null);
        } else if (trabalhosRealizados.getDataPrevisao() == null) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "A data de previsão deve ser informada. Verifique!",null);
        }

        return new ApiResponse<>(HttpStatus.CREATED.value(), "Trabalho salvo com sucesso!",this.trabalhosRealizadosService.save(trabalhosRealizados));
    }

    @GetMapping("/listlike")
    public ApiResponse<Page<TrabalhosRealizados>> findAll(@RequestParam(value = "datainicio", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataInicio,
                                                          @RequestParam(value = "datafim", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFim,
                                                          @RequestParam(value = "idcliente", required = false)Long idCliente,
                                                          @RequestParam(value = "pesquisa", required = false, defaultValue = "")String pesquisa,
                                                          @RequestParam(value = "size",required = false,defaultValue = "10")Integer size,
                                                          @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                                          @RequestParam(value = "sort",required = false,defaultValue = "id")String sort){

        Page<TrabalhosRealizados> result = this.trabalhosRealizadosService.findAll(idCliente,dataInicio,dataFim,pesquisa,page,size,sort);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", result);
    }

    @GetMapping
    public ApiResponse<Page<TrabalhosRealizados>> findAll(@RequestParam(value = "size",required = false,defaultValue = "10")Integer size,
                                                          @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                                          @RequestParam(value = "sort",required = false,defaultValue = "id")String sort){

        Page<TrabalhosRealizados> result = this.trabalhosRealizadosService.findAll(page,size,sort);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", result);
    }

    @GetMapping("/list")
    public ApiResponse<List<TrabalhosRealizados>> findAll(){

        List<TrabalhosRealizados> result = this.trabalhosRealizadosService.findAll();
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", result);
    }

    @GetMapping("/{id}")
    public ApiResponse<TrabalhosRealizados> findById(@PathVariable Long id){

        TrabalhosRealizados trabalhosRealizado = this.trabalhosRealizadosService.findById(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", trabalhosRealizado);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){

        this.trabalhosRealizadosService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Trabalho deletado com sucesso!", null);
    }
}






















