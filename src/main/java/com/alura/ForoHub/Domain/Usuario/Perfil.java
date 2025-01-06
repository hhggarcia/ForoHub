package com.alura.ForoHub.Domain.Usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPerfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;
    private String nombre;
    private Boolean activo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "idPerfil"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario")
    )
    @JsonBackReference
    private List<Usuario> usuarios;

    public Perfil(@Valid DatosPerfil datos) {
        this.nombre = datos.nombre();
        this.activo = true;
    }
}
