package br.com.fiap.compartilhaja.controller;


import br.com.fiap.compartilhaja.dto.EquipamentoRequestDTO;
import br.com.fiap.compartilhaja.dto.EquipamentoResponseDTO;
import br.com.fiap.compartilhaja.model.Equipamento;
import br.com.fiap.compartilhaja.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    // Endpoint para cadastro de equipamento

    @PostMapping("/equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoRequestDTO salvar(@Valid @RequestBody EquipamentoRequestDTO equipamentoCadastroDTO) {
        return equipamentoService.cadastrarEquipamento(equipamentoCadastroDTO);
    }

    // Endpoint para atualizar informações do equipamento

    @PutMapping("/equipamentos")
    @ResponseStatus(HttpStatus.OK)
    public Equipamento atualizar(@Valid @RequestBody Equipamento equipamento) {
        return equipamentoService.atualizarEquipamento(equipamento);
    }

    // Endpoint para excluir equipamento

    @DeleteMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        equipamentoService.excluirEquipamento(id);
    }

    // Endpoint buscar Equipamento por id

    @GetMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <EquipamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(equipamentoService.buscarEquipamentoPorId(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar todos os equipamentos

    @GetMapping("/equipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoResponseDTO> buscarEquipamentos(){
        return equipamentoService.buscarEquipamentos();
    }






}
