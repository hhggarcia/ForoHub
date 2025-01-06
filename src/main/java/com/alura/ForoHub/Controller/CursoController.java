package com.alura.ForoHub.Controller;

import com.alura.ForoHub.Domain.Curso.Curso;
import com.alura.ForoHub.Domain.Curso.CursoRepository;
import com.alura.ForoHub.Domain.Curso.DatosCurso;
import com.alura.ForoHub.Domain.Curso.DatosListadoCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
//@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosListadoCurso> registrarCurso(@RequestBody @Valid DatosCurso datos,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Curso curso =  cursoRepository.save(new Curso(datos));

        DatosListadoCurso datosRespuesta = new DatosListadoCurso(curso.getId(),
                curso.getNombre(),
                curso.getCategoria());

        URI url = uriComponentsBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listadoCursos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion)
                .map(DatosListadoCurso::new));
    }
}
