package com.example.clientesapi.repository;

import com.example.clientesapi.model.Clientes;
import com.example.clientesapi.model.dto.ClientesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    @Query(value = "select id, nome, telefone from clientes where " +
            " ((date(:datainicio) is null and date(:datafim) is null) or (date(datacadastro) >= date(:datainicio) and date(datacadastro) <= date(:datafim))) and " +
            " ( " +
                " (unaccent(nome) ilike %:pesquisa% ) or " +
                " (unaccent(telefone) ilike %:pesquisa%) or " +
                " (text(id) ilike %:pesquisa% ) " +
            " ) ",nativeQuery = true)
   Page<ClientesDTO> findAll(@Param("datainicio") LocalDate datainicio,
                             @Param("datafim") LocalDate datafim,
                             @Param("pesquisa") String pesquisa,
                             Pageable pageable);
}
