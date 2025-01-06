package com.alura.ForoHub.Domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopico(@NotBlank
                          String titulo,
                          @NotBlank
                          String mensaje,
                          @NotNull
                          Long idAutor,
                          @NotBlank
                          String nombreCurso) {
}
