package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.dto.AtorDto;
import s.a.filmes.exceptionAtor.AtorNaoEncontradoException;
import s.a.filmes.model.Ator;
import s.a.filmes.repository.AtorRepository;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    // adicionar ator
    public Ator adicionarAtor(AtorDto atorDto){
        try {
        
        Ator atorSaved = new Ator();
        atorSaved.setNome(atorDto.getNome());
        atorSaved.setDataNascimento(atorDto.getDataNascimento());
        atorSaved.setCpf(atorDto.getCpf());
        atorSaved.setEmail(atorDto.getEmail());
        atorSaved.setSalario(atorDto.getSalario());
        atorSaved.setGenero(atorDto.getGenero());
        atorSaved.setNacionalidade(atorDto.getNacionalidade());
        atorSaved.setTelefone(atorDto.getTelefone());

         return atorRepository.save(atorSaved);
        } catch (Exception e) {
         System.out.println("Erro ao adicionar ator: " + e.getMessage());
            return null; // ou pode lançar exceção
        }
}
 
    //editar ator
    public Ator editarAtor(Long id, AtorDto atorEditadoDto) {
    Ator AtorDto = atorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado: " + id));

    AtorDto.setNome(atorEditadoDto.getNome());
    AtorDto.setDataNascimento(atorEditadoDto.getDataNascimento());
    AtorDto.setCpf(atorEditadoDto.getCpf());
    AtorDto.setEmail(atorEditadoDto.getEmail());
    AtorDto.setSalario(atorEditadoDto.getSalario());
    AtorDto.setGenero(atorEditadoDto.getGenero());
    AtorDto.setNacionalidade(atorEditadoDto.getNacionalidade());
    AtorDto.setTelefone(atorEditadoDto.getTelefone());

    return atorRepository.save(AtorDto); 
}

    //excluir ator
    public void excluirAtor(Long id){
        try {
            atorRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao excluir ator: " + e.getMessage());
        }
    }
    
    public Ator getAtorById(Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new AtorNaoEncontradoException("Ator não encontrado: " + id));
    }

}

