package com.example.clientesapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table(name = "trabalhosrealizados")
@Entity
public class TrabalhosRealizados {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dataprevisao")
    private LocalDate dataPrevisao;

    @Column(name = "datacadastro", nullable = false,updatable = false)
    private LocalDate data = LocalDate.now();

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valortotal", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente", referencedColumnName = "id")
    private Clientes cliente;
}
