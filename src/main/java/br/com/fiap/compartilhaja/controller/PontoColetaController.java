package br.com.fiap.compartilhaja.controller;


import br.com.fiap.compartilhaja.dto.PontoColetaRequestDTO;
import br.com.fiap.compartilhaja.dto.PontoColetaResponseDTO;
import br.com.fiap.compartilhaja.model.PontoColeta;
import br.com.fiap.compartilhaja.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PontoColetaController {

    @Autowired
    private PontoColetaService pontoColetaService;

    // Endpoint para cadastro de ponto de coleta

    @PostMapping("/pontos-coleta")
    @ResponseStatus(HttpStatus.CREATED)
    public PontoColetaRequestDTO salvar(@Valid @RequestBody PontoColetaRequestDTO pontoColetaCadastroDTO) {
        return pontoColetaService.cadastrarPontoColeta(pontoColetaCadastroDTO);
    }

    // Endpoint para atualizar informações do ponto de coleta

    @PutMapping("/pontos-coleta")
    @ResponseStatus(HttpStatus.OK)
    public PontoColeta atualizar(@Valid @RequestBody PontoColeta pontoColeta) {
        return pontoColetaService.atualizarPontoColeta(pontoColeta);
    }

    // Endpoint para excluir ponto de coleta

    @DeleteMapping("/pontos-coleta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pontoColetaService.excluirPontoColeta(id);
    }

    // Endpoint buscar ponto de coleta por id

    @GetMapping("/pontos-coleta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <PontoColetaResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(pontoColetaService.buscarPontoColetaPorId(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar todos os pontos de coleta

    @GetMapping("/pontos-coleta")
    @ResponseStatus(HttpStatus.OK)
    public List<PontoColetaResponseDTO> buscarPontosColeta(){
        return pontoColetaService.buscarPontosColeta();
    }






}
