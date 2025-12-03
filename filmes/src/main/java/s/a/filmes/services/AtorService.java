package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.model.Ator;
import s.a.filmes.repository.AtorRepository;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    // adicionar ator
    public Ator adicionarAtor(Ator ator){
        try {
         return atorRepository.save(ator);
        } catch (Exception e) {
         System.out.println("Erro ao adicionar ator: " + e.getMessage());
            return null; // ou pode lançar exceção
        }
}
 
    //editar ator
    public Ator editarAtor(Long id, Ator atorEditado) {
    Ator ator = atorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado: " + id));

    ator.setNome(atorEditado.getNome());
    ator.setDataNascimento(atorEditado.getDataNascimento());
    ator.setCpf(atorEditado.getCpf());
    ator.setEmail(atorEditado.getEmail());
    ator.setSalario(atorEditado.getSalario());
    ator.setGenero(atorEditado.getGenero());
    ator.setNacionalidade(atorEditado.getNacionalidade());
    ator.setTelefone(atorEditado.getTelefone());

    return atorRepository.save(ator); 
}

    //excluir ator
    public void excluirAtor(Long id){
        try {
            atorRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao excluir ator: " + e.getMessage());
        }
    }
    

}

