package com.alura.ForoHub.Domain.Usuario;

import java.util.List;

public record DatosUsuario(String nombre,
                           String email,
                           String clave,
                           List<Long> perfiles) {
}
