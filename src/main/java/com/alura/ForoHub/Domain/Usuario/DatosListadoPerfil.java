package com.alura.ForoHub.Domain.Usuario;

public record DatosListadoPerfil(Long idPerfil,
                                 String nombre) {

    public DatosListadoPerfil(Perfil datos) {
        this(datos.getIdPerfil(), datos.getNombre());
    }
}
