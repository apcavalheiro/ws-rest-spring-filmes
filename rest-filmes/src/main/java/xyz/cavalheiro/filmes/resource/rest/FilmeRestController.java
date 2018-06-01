package xyz.cavalheiro.filmes.resource.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.cavalheiro.filmes.domain.Filme;
import xyz.cavalheiro.filmes.service.FilmeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = "/filmes",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class FilmeRestController {

    @Autowired
    private FilmeService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Filme> listar(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {

        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Filme editar(@PathVariable("id") Long id, @RequestBody Filme filme) {

        service.update(id, filme);
        return filme;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Filme getFilme(@PathVariable("id") Long id) {

        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Filme filme) {

        service.save(filme);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filme.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
