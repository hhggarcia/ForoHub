package com.alura.ForoHub.Domain.Topico;

import com.alura.ForoHub.Domain.Curso.Curso;
import com.alura.ForoHub.Domain.Respuesta.Respuesta;
import com.alura.ForoHub.Domain.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Topico(@Valid DatosTopico datos, Curso curso, Usuario usuario) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.status = "Creado";
        this.fechaCreacion = LocalDateTime.now();
        this.curso = curso;
        this.autor = usuario;
        this.activo = true;
    }

    public void actualizarTopico(@Valid DatosTopico datos){
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
    }

    public void desactivarTopico() {
        this.activo = false;
    }
}
