package s.a.filmes.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import s.a.filmes.dto.FilmeDto;
import s.a.filmes.model.Filme;
import s.a.filmes.services.FilmeService;

@RestController
@RequestMapping("/filme")
@Tag(name = "Filme", description = "Gerenciamento de filmes")

public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    ///ADICIONAR FILME
   @PostMapping("/adicionarFilme")
    public ResponseEntity<Filme> adicionarFilme(@RequestBody FilmeDto filmeDto) {
        Filme novoFilme = filmeService.adicionarFilme(filmeDto);
        return ResponseEntity.created(URI.create("/filme/" + novoFilme.getId())).body(novoFilme);
    }

    //EDITAR FILME
    @PutMapping("/editarFilme/{id}")
    public ResponseEntity<Filme> editarFilme(@PathVariable Long id, @RequestBody FilmeDto filmeDto) {
        Filme filmeEditado = filmeService.editarFilme(id, filmeDto);
        return ResponseEntity.ok(filmeEditado);
    }

    //BUSCAR FILME PELO ID
    @GetMapping("/buscarFilme/{id}")
    public ResponseEntity<Filme> buscarFilmeporId(@PathVariable Long id) {
        Filme filme = filmeService.getFilmeById(id);
        return ResponseEntity.ok(filme);

    }

    //EXCLUIR  FILME
    @DeleteMapping("/excluirFilme/{id}")
    public ResponseEntity<String> excluirFilme(@PathVariable Long id) {
        filmeService.excluirFilme(id);
        return ResponseEntity.ok("Filme " + id + " excluído com sucesso");
    }

}
