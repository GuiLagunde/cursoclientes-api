package com.example.clientesapi.repository;

import com.example.clientesapi.model.TrabalhosRealizados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface TrabalhosRealizadosRepository extends JpaRepository<TrabalhosRealizados, Long> {

    @Query(value = "select * from trabalhosrealizados where " +
            " ((date(:datainicio) is null and date(:datafim) is null) or (date(dataprevisao) >= date(:datainicio) and date(dataprevisao) <= date(:datafim))) and " +
            " (:idcliente is null or idcliente = :idcliente) and " +
            " ( " +
            " (unaccent(descricao) ilike %:pesquisa%) or " +
            " (text(id) ilike %:pesquisa%) " +
            " ) ",nativeQuery = true)
    Page<TrabalhosRealizados> findAll(@Param("datainicio") LocalDate dataInicio,
                                      @Param("datafim") LocalDate datafim,
                                      @Param("idcliente")Long idCliente,
                                      @Param("pesquisa")String pesquisa,
                                      Pageable pageable);
}
