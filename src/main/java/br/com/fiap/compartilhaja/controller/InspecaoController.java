package br.com.fiap.compartilhaja.controller;


import br.com.fiap.compartilhaja.dto.InspecaoRequestDTO;
import br.com.fiap.compartilhaja.dto.InspecaoResponseDTO;
import br.com.fiap.compartilhaja.model.Inspecao;
import br.com.fiap.compartilhaja.service.InspecaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class InspecaoController {

    @Autowired
    private InspecaoService inspecaoService;

    // Endpoint para cadastro de inspeção

    @PostMapping("/inspecoes")
    @ResponseStatus(HttpStatus.CREATED)
    public InspecaoRequestDTO salvar(@Valid @RequestBody InspecaoRequestDTO inspecaoCadastroDTO) {
        return inspecaoService.cadastrarInspecao(inspecaoCadastroDTO);
    }

    // Endpoint para atualizar informações da inspeção

    @PutMapping("/inspecoes")
    @ResponseStatus(HttpStatus.OK)
    public Inspecao atualizar(@Valid @RequestBody Inspecao inspecao) {
        return inspecaoService.atualizarInspecao(inspecao);
    }

    // Endpoint para excluir inspeção

    @DeleteMapping("/inspecoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        inspecaoService.excluirInspecao(id);
    }

    // Endpoint buscar inspeção por id

    @GetMapping("/inspecoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <InspecaoResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(inspecaoService.buscarInspecaoPorId(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar todas as inspeções

    @GetMapping("/inspecoes")
    @ResponseStatus(HttpStatus.OK)
    public List<InspecaoResponseDTO> buscarInspecoes(){
        return inspecaoService.buscarInspecoes();
    }






}
