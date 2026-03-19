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
import s.a.filmes.dto.PapelDto;
import s.a.filmes.model.Papel;
import s.a.filmes.services.PapelService;

@RestController
@RequestMapping("/papel")
@Tag(name = "Papel", description = "Gerenciamento de papeis")
public class PapelController {

    @Autowired
    private PapelService papelService;

    //ADICIONAR PAPEL  
    @PostMapping("/adicionarPapel")
    public ResponseEntity<Papel> adicionarPapel(@RequestBody PapelDto papelDto) {
        Papel novoPapel = papelService.adicionarPapel(papelDto);
        return ResponseEntity.created(URI.create("/papel/" + novoPapel.getId())).body(novoPapel);

    }

    //EDITAR PAPEL
    @PutMapping("/editarPapel/{id}")
    public ResponseEntity<Papel> editarPapel(@PathVariable Long id, @RequestBody PapelDto papelDto) {
        Papel papelEditado = papelService.editarPapel(id, papelDto);
        return ResponseEntity.ok(papelEditado);
    }

    //BUSCAR PAPEL PELO ID
    @GetMapping("/buscarPapel/{id}")
    public ResponseEntity<Papel> buscarPapelporId(@PathVariable Long id) {
        Papel papel = papelService.getPapelById(id);
        return ResponseEntity.ok(papel);

    }

    //EXCLUIR PAPEL
    @DeleteMapping("/excluirPapel/{id}")
    public ResponseEntity<Papel> excluirPapel(@PathVariable Long id) {
        papelService.excluirPapel(id);
        return ResponseEntity.noContent().build();

    }
}
