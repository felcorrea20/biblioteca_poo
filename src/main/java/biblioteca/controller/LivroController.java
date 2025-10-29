package biblioteca.controller;

import biblioteca.model.Livro;
import biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@CrossOrigin( origins = "*" )
public class LivroController {
    @Autowired
    private LivroService livroService;

    //create
    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro){
        return ResponseEntity.ok(livroService.adicionarLivro(livro));
    }

    //Read
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivro(){
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
        return livroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id,@RequestBody Livro livro){
        return ResponseEntity.ok(livroService.atualizarLivro(id, livro));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id){
        livroService.excliurLivro(id);
        return ResponseEntity.noContent().build();
    }

}
