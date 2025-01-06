package com.alura.ForoHub.Domain.Topico;

import com.alura.ForoHub.Domain.Curso.Curso;
import com.alura.ForoHub.Domain.Curso.DatosListadoCurso;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 LocalDateTime fechaCreacion,
                                 DatosListadoCurso curso) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), new DatosListadoCurso(topico.getCurso()));
    }
}
