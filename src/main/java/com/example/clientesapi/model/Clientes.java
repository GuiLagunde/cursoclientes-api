package com.example.clientesapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Table(name = "clientes")
@Entity
@Data
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "datacadastro", updatable = false)
    private LocalDate dataCadastro = LocalDate.now();
}
