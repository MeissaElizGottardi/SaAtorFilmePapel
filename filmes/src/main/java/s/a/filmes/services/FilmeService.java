package s.a.filmes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s.a.filmes.dto.FilmeDto;
import s.a.filmes.model.Filme;
import s.a.filmes.repository.FilmeRepository;


@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;


    //ADICIONAR FILME
    public Filme adicionarFilme(FilmeDto filmeDto){
        try {

            Filme filmeSaved = new Filme();
            filmeSaved.setNome(filmeDto.getNome());
            filmeSaved.setDiretor(filmeDto.getDiretor());
            filmeSaved.setDescricao(filmeDto.getDescricao());
            filmeSaved.setGenero(filmeDto.getGenero());
            filmeSaved.setDataLancamento(filmeDto.getDataLancamento());
            filmeSaved.setOrcamento(filmeDto.getOrcamento());
            filmeSaved.setIdiomaOriginal(filmeDto.getIdiomaOriginal());
            filmeSaved.setDuracao(filmeDto.getDuracao());
            filmeSaved.setPaisOrigem(filmeDto.getPaisOrigem());
            filmeSaved.setClassificacaoIndicativa(filmeDto.getClassificacaoIndicativa());

             return filmeRepository.save(filmeSaved);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar filme: " + e.getMessage());
        }
        return null;
    }

    
    //EDITAR FILME
    public Filme editarFilme(Long id, FilmeDto filmeEditadoDto) {
    Filme filmeDto = filmeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado: " + id));

    filmeDto.setNome(filmeEditadoDto.getNome());
    filmeDto.setDiretor(filmeEditadoDto.getDiretor());
    filmeDto.setDescricao(filmeEditadoDto.getDescricao());
    filmeDto.setGenero(filmeEditadoDto.getGenero());
    filmeDto.setDataLancamento(filmeEditadoDto.getDataLancamento());
    filmeDto.setOrcamento(filmeEditadoDto.getOrcamento());
    filmeDto.setIdiomaOriginal(filmeEditadoDto.getIdiomaOriginal());
    filmeDto.setDuracao(filmeEditadoDto.getDuracao());
    filmeDto.setClassificacaoIndicativa(filmeEditadoDto.getClassificacaoIndicativa());


    return filmeRepository.save(filmeDto); 
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

    

