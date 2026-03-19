package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.dto.PapelDto;
import s.a.filmes.exceptionPapel.PapelNaoEncontradoException;
import s.a.filmes.model.Papel;
import s.a.filmes.repository.PapelRepository;


@Service
public class PapelService {

    @Autowired
    private PapelRepository papelRepository;

    //ADICIONAR PAPEL
    public Papel adicionarPapel(PapelDto papelDto){
        try {

            Papel papelSaved = new Papel();

            papelSaved.setNome(papelDto.getNome());
            papelSaved.setPersonagemPrincipal(papelDto.getPersonagemPrincipal());
            papelSaved.setDescricao(papelDto.getDescricao());
            papelSaved.setIdade(papelDto.getIdade());
            papelSaved.setTempoCena(papelDto.getTempoCena());
            papelSaved.setImportancia(papelDto.getImportancia());



            return papelRepository.save(papelSaved);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar papel: " + e.getMessage());
        }
        return null;
    }

    //EDITAR PAPEL
     public Papel editarPapel(Long id, PapelDto papelEditadoDto) {
    Papel papelDto = papelRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Papel não encontrado: " + id));
      
        papelDto.setNome(papelEditadoDto.getNome());
        papelDto.setPersonagemPrincipal(papelEditadoDto.getPersonagemPrincipal());
        papelDto.setDescricao(papelEditadoDto.getDescricao());
        papelDto.setIdade(papelEditadoDto.getIdade());
        papelDto.setTempoCena(papelEditadoDto.getTempoCena());
        papelDto.setImportancia(papelEditadoDto.getImportancia());
 
        return papelRepository.save(papelDto);
    }

    // BUSCAR PAPEL  POR ID
     public Papel getPapelById(Long id) {
        return papelRepository.findById(id)
                .orElseThrow(() -> new PapelNaoEncontradoException("Papel não encontrado: " + id));
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
