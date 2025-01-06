package com.alura.ForoHub.Domain.Usuario;

import com.alura.ForoHub.Domain.Respuesta.Respuesta;
import com.alura.ForoHub.Domain.Topico.Topico;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String email;
    private String password;
    private Boolean activo;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Perfil> perfiles;

    public Usuario(@Valid DatosUsuario datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.password = datos.clave();
        this.activo = true;
        this.perfiles = new ArrayList<>();
    }

    public void addPerfil(Perfil perfil){
        this.perfiles.add(perfil);
        perfil.getUsuarios().add(this);
    }
}
