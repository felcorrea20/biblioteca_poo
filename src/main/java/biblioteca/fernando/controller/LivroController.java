package biblioteca.fernando.controller;

import biblioteca.fernando.model.Livro;
import biblioteca.fernando.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
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
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id, @RequestBody Livro livro){
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
