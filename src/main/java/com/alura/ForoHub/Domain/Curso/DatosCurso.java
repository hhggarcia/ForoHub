package com.alura.ForoHub.Domain.Curso;

import jakarta.validation.constraints.NotBlank;

public record DatosCurso(@NotBlank
                         String nombre,
                         @NotBlank
                         String categoria) {
}
