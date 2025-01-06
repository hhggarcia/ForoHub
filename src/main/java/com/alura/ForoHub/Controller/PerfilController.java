package com.alura.ForoHub.Controller;

import com.alura.ForoHub.Domain.Usuario.DatosListadoPerfil;
import com.alura.ForoHub.Domain.Usuario.DatosPerfil;
import com.alura.ForoHub.Domain.Usuario.Perfil;
import com.alura.ForoHub.Domain.Usuario.PerfilRepository;
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
@RequestMapping("/perfiles")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoPerfil>> listarPerfiles(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(perfilRepository.findByActivoTrue(paginacion)
                .map(DatosListadoPerfil::new));
    }

    @PostMapping
    public ResponseEntity<DatosListadoPerfil> registrarPerfil(@RequestBody @Valid DatosPerfil datos,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Perfil perfil = perfilRepository.save(new Perfil(datos));

        DatosListadoPerfil datosRespuesta = new DatosListadoPerfil(perfil.getIdPerfil(),
                perfil.getNombre());

        URI url = uriComponentsBuilder.path("perfiles/{id}").buildAndExpand(perfil.getIdPerfil()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }
}
