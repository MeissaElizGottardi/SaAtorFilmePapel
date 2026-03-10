package s.a.filmes.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s.a.filmes.dto.FilmeDto;
import s.a.filmes.model.Filme;
import s.a.filmes.services.FilmeService;


@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService FilmeService;

    
     ///ADICIONAR FILME
   @PostMapping("/adicionarFilme")
    public ResponseEntity<Filme> adicionarFilme(@RequestBody FilmeDto filmeDto) {
    Filme novoFilme = FilmeService.adicionarFilme(filmeDto);
    return ResponseEntity.created(URI.create("/filme/" + novoFilme.getId())).body(novoFilme);
}

    //EDITAR FILME
   @PutMapping("/editarFilme/{id}")
    public ResponseEntity<Filme> editarAtor(@PathVariable Long id, @RequestBody FilmeDto filmeDto) {
    Filme filmeEditado = FilmeService.editarFilme(id, filmeDto);
    return ResponseEntity.ok(filmeEditado);
}

    //EXCLUIR ATOR
    @DeleteMapping("/excluirFilme/{id}")
    public ResponseEntity<Filme> excluirAtor(@PathVariable Long id) {
       FilmeService.excluirFilme(id);
        return ResponseEntity.noContent().build();     
}

}