package com.alura.ForoHub.Domain.Curso;


import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findByActivoTrue(Pageable paginacion);
    Optional<Curso> findByNombreIgnoreCase(@NotBlank String nombre);
}
