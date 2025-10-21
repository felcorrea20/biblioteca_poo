package biblioteca.fernando.services;

import biblioteca.fernando.model.Livro;
import biblioteca.fernando.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    //Create
    public Livro adicionarLivro(Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id){
        return livroRepository.findById(id);
    }

    //update
    public Livro atualizarLivro(Long id, Livro livroAtualizado){
        return livroRepository.findById(id)
                .map(livro ->{
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setAutor(livroAtualizado.getAutor());
                    return livroRepository.save(livro);
                })
                .orElseThrow(()-> new RuntimeException("Livro não encontrado"));
    }
    //Delete
    public void excliurLivro(Long id){
        livroRepository.deleteById(id);
    }
}
