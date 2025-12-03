package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.model.Papel;
import s.a.filmes.repository.PapelRepository;


@Service
public class PapelService {

    @Autowired
    private PapelRepository papelRepository;

    //ADICIONAR PAPEL
    public Papel adicionarPapel(Papel papel){
        try {
            return papelRepository.save(papel);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar papel: " + e.getMessage());
        }
        return null;
    }

    //EDITAR PAPEL
     public Papel editarPapel(Long id, Papel papelEditado) {
    Papel papel = papelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Papel não encontrado: " + id));
      
        papel.setNome(papelEditado.getNome());
        papel.setPersonagemPrincipal(papelEditado.getPersonagemPrincipal());
        papel.setDescricao(papelEditado.getDescricao());
        papel.setIdade(papelEditado.getIdade());
        papel.setTempoCena(papelEditado.getTempoCena());
        papel.setImportancia(papelEditado.getImportancia());
 
        return papelRepository.save(papel);
    }


    //EXCLUIR PAPEL
    public void excluirPapel(Long id){
        try {
            papelRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao excluir papel: " + e.getMessage());
        }
    }
    
    


    

    
}
