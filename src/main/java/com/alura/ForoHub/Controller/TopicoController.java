package com.alura.ForoHub.Controller;

import com.alura.ForoHub.Domain.Curso.Curso;
import com.alura.ForoHub.Domain.Curso.CursoRepository;
import com.alura.ForoHub.Domain.Curso.DatosListadoCurso;
import com.alura.ForoHub.Domain.Topico.DatosListadoTopico;
import com.alura.ForoHub.Domain.Topico.DatosTopico;
import com.alura.ForoHub.Domain.Topico.Topico;
import com.alura.ForoHub.Domain.Topico.TopicoRepository;
import com.alura.ForoHub.Domain.Usuario.Usuario;
import com.alura.ForoHub.Domain.Usuario.UsuarioRepository;
import com.alura.ForoHub.infra.Errores.MensajeError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion)
                .map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleTopico(@PathVariable Long id){
        Optional<Topico> isTopico = topicoRepository.findById(id);
        if (!isTopico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeError("Topico no existe"));
        }
        Topico topico = isTopico.get();

        DatosListadoTopico datosRespuesta = new DatosListadoTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosListadoCurso(topico.getCurso()));

        return ResponseEntity.ok(datosRespuesta);
    }

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosTopico datos,
                                             UriComponentsBuilder uriComponentsBuilder){
        // validar el id del usuario
        Optional<Usuario> usuarioValidar = usuarioRepository.findById(datos.idAutor());
        if (!usuarioValidar.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeError("Usuario no existe"));
        }
        // validar nombre del curso
        Optional<Curso> existCurso = cursoRepository.findByNombreIgnoreCase(datos.nombreCurso());
        if (!existCurso.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeError("Curso no encontrado"));
        }
        // registrar topico
        Topico topico = topicoRepository.save(new Topico(datos,
                existCurso.get(),
                usuarioValidar.get()));

        DatosListadoTopico datosRespuesta = new DatosListadoTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosListadoCurso(topico.getCurso()));

        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id,
                                              @RequestBody @Valid DatosTopico datos,
                                              UriComponentsBuilder uriComponentsBuilder){
        Optional<Topico> isTopico = topicoRepository.findById(id);
        if (!isTopico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeError("Topico no existe"));
        }

        Topico topico = isTopico.get();
        topico.actualizarTopico(datos);

        DatosListadoTopico datosRespuesta = new DatosListadoTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosListadoCurso(topico.getCurso()));

        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
