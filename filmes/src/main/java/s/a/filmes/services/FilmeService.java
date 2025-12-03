package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.model.Filme;
import s.a.filmes.repository.FilmeRepository;


@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;


    //ADICIONAR FILME
    public Filme adicionarFilme(Filme filme){
        try {
             return filmeRepository.save(filme);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar filme: " + e.getMessage());
        }
        return null;
    }

    
    //EDITAR FILME
    public Filme editarFilme(Long id, Filme filmeEditado) {
    Filme filme = filmeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado: " + id));

    filme.setNome(filmeEditado.getNome());
    filme.setDiretor(filmeEditado.getDiretor());
    filme.setDescricao(filmeEditado.getDescricao());
    filme.setGenero(filmeEditado.getGenero());
    filme.setDataLancamento(filmeEditado.getDataLancamento());
    filme.setOrcamento(filmeEditado.getOrcamento());
    filme.setIdiomaOriginal(filmeEditado.getIdiomaOriginal());
    filme.setDuracao(filmeEditado.getDuracao());
    filme.setClassificacaoIndicativa(filmeEditado.getClassificacaoIndicativa());


    return filmeRepository.save(filme); 
}

    //EXCLUIR FILME
    public void excluirFilme(Long id){
        try {
            filmeRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao excluir filme: " + e.getMessage());
        }
    }
    

}

    

