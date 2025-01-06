package com.alura.ForoHub.Domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosPerfil(@NotBlank
                          String nombre) {
}
