package com.alura.ForoHub.Controller;

import com.alura.ForoHub.Domain.Usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> listarUsuarios(Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion)
                .map(DatosListadoUsuarios::new));
    }

    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUsuario datos,
                                           UriComponentsBuilder uriComponentsBuilder){
        Usuario nuevoUsuario = new Usuario(datos);

        List<Perfil> perfiles = perfilRepository.findAllById(datos.perfiles());
        for (Perfil perfil : perfiles) {
            nuevoUsuario.addPerfil(perfil);
        }

        Usuario creadoUsuario = usuarioRepository.save(nuevoUsuario);

        DatosListadoUsuarios datosRespuesta = new DatosListadoUsuarios(creadoUsuario.getIdUsuario(),
                creadoUsuario.getNombre(),
                creadoUsuario.getEmail(),
                creadoUsuario.getPerfiles());

        URI url = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(creadoUsuario.getIdUsuario()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }
}
