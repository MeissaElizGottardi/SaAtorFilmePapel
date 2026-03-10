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

import s.a.filmes.dto.AtorDto;
import s.a.filmes.model.Ator;
import s.a.filmes.services.AtorService;



@RestController
@RequestMapping("/ator")
public class AtorController {
    
    @Autowired 
    private AtorService AtorService;

    //ADICIONAR ATOR
   @PostMapping("/adicionarAtor")
    public ResponseEntity<Ator> adicionarAtor(@RequestBody AtorDto atorDto) {
    Ator novoAtor = AtorService.adicionarAtor(atorDto);
    return ResponseEntity.created(URI.create("/ator/" + novoAtor.getId())).body(novoAtor);
}

    //EDITAR ATOR
   @PutMapping("/editarAtor/{id}")
    public ResponseEntity<Ator> editarAtor(@PathVariable Long id, @RequestBody AtorDto atorDto) {
    Ator atorEditado = AtorService.editarAtor(id, atorDto);
    return ResponseEntity.ok(atorEditado);
}

    //EXCLUIR ATOR
    @DeleteMapping("/excluirAtor/{id}")
    public ResponseEntity<Ator> excluirAtor(@PathVariable Long id) {
       AtorService.excluirAtor(id);
        return ResponseEntity.noContent().build();     
}

    @GetMapping("buscarAtor/{id}")
    public ResponseEntity<Ator> buscarAtorporId(@PathVariable Long id) {
        Ator ator = AtorService.getAtorById(id);
       return ResponseEntity.ok(ator);
    
    }
    

}

    
    


