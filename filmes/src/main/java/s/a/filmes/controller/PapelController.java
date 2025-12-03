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

import s.a.filmes.model.Papel;
import s.a.filmes.services.PapelService;


@RestController
@RequestMapping("/papel")
public class PapelController {

    @Autowired
    private PapelService PapelService;

    
    //ADICIONAR PAPEL  
    @PostMapping("/adicionarPapel")
    public ResponseEntity<Papel> adicionarPapel(@RequestBody Papel papel) {
        Papel novoPapel = PapelService.adicionarPapel(papel);
        return ResponseEntity.created(URI.create("/papel/" + novoPapel.getId())).body(novoPapel); 

    }

    //EDITAR PAPEL
    @PutMapping("/editarPapel/{id}")
    public ResponseEntity<Papel> editarPapel(@PathVariable Long id, @RequestBody Papel papel) {
        Papel papelEditado = PapelService.editarPapel(id, papel);
        return ResponseEntity.ok(papelEditado);
    }

    //EXCLUIR PAPEL
    @DeleteMapping("/excluirPapel/{id}")
    public ResponseEntity<Papel> excluirPapel(@PathVariable Long id) {
       PapelService.excluirPapel(id);
        return ResponseEntity.noContent().build();
    
    }
}


    
        



    

    

    
    

