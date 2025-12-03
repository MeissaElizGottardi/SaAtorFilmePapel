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

import s.a.filmes.model.Ator;
import s.a.filmes.services.AtorService;


@RestController
@RequestMapping("/ator")
public class AtorController {
    
    @Autowired 
    private AtorService AtorService;

    //ADICIONAR ATOR
   @PostMapping("/adicionarAtor")
    public ResponseEntity<Ator> adicionarAtor(@RequestBody Ator ator) {
    Ator novoAtor = AtorService.adicionarAtor(ator);
    return ResponseEntity.created(URI.create("/ator/" + novoAtor.getId())).body(novoAtor);
}

    //EDITAR ATOR
   @PutMapping("/editarAtor/{id}")
    public ResponseEntity<Ator> editarAtor(@PathVariable Long id, @RequestBody Ator ator) {
    Ator atorEditado = AtorService.editarAtor(id, ator);
    return ResponseEntity.ok(atorEditado);
}

    //EXCLUIR ATOR
    @DeleteMapping("/excluirAtor/{id}")
    public ResponseEntity<Ator> excluirAtor(@PathVariable Long id) {
       AtorService.excluirAtor(id);
        return ResponseEntity.noContent().build();     
}

}

    
    


