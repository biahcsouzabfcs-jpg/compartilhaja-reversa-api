package br.com.fiap.compartilhaja.controller;


import br.com.fiap.compartilhaja.dto.DescarteRequestDTO;
import br.com.fiap.compartilhaja.dto.DescarteResponseDTO;
import br.com.fiap.compartilhaja.model.Descarte;
import br.com.fiap.compartilhaja.service.DescarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class DescarteController {

    @Autowired
    private DescarteService descarteService;

    // Endpoint para cadastro de descarte

    @PostMapping("/descartes")
    @ResponseStatus(HttpStatus.CREATED)
    public DescarteRequestDTO salvar(@RequestBody DescarteRequestDTO descarteCadastroDTO) {
        return descarteService.cadastrarDescarte(descarteCadastroDTO);
    }

    // Endpoint para atualizar informações do descarte

    @PutMapping("/descartes")
    @ResponseStatus(HttpStatus.OK)
    public Descarte atualizar(@RequestBody Descarte descarte) {
        return descarteService.atualizarDescarte(descarte);
    }

    // Endpoint para excluir descarte

    @DeleteMapping("/descartes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        descarteService.excluirDescarte(id);
    }

    // Endpoint buscar descarte por id

    @GetMapping("/descartes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <DescarteResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(descarteService.buscarDescartePorId(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar todos os descartes

    @GetMapping("/descartes")
    @ResponseStatus(HttpStatus.OK)
    public List<DescarteResponseDTO> buscarDescartes(){
        return descarteService.buscarDescartes();
    }






}
