package com.alura.ForoHub.Domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopico(@NotNull
                          Long idAutor,
                          @NotBlank
                          String titulo,
                          @NotBlank
                          String mensaje,
                          @NotBlank
                          String nombreCurso) {
}
