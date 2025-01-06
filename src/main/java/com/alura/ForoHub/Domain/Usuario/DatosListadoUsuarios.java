package com.alura.ForoHub.Domain.Usuario;

import java.util.List;

public record DatosListadoUsuarios(Long id,
                                   String nombre,
                                   String email,
                                   List<Perfil> perfiles) {
    public DatosListadoUsuarios(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail(), usuario.getPerfiles());
    }
}
