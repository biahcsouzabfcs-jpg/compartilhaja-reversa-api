package br.com.fiap.compartilhaja.controller;


import br.com.fiap.compartilhaja.dto.NotificacaoRequestDTO;
import br.com.fiap.compartilhaja.dto.NotificacaoResponseDTO;
import br.com.fiap.compartilhaja.model.Notificacao;
import br.com.fiap.compartilhaja.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    // Endpoint para cadastro de notificação

    @PostMapping("/notificacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificacaoRequestDTO salvar(@RequestBody NotificacaoRequestDTO notificacaoCadastroDTO) {
        return notificacaoService.cadastrarNotificacao(notificacaoCadastroDTO);
    }

    // Endpoint para atualizar informações da notificação

    @PutMapping("/notificacoes")
    @ResponseStatus(HttpStatus.OK)
    public Notificacao atualizar(@RequestBody Notificacao notificacao) {
        return notificacaoService.atualizarNotificacao(notificacao);
    }

    // Endpoint para excluir notificação

    @DeleteMapping("/notificacoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        notificacaoService.excluirNotificacao(id);
    }

    // Endpoint buscar notificação por id

    @GetMapping("/notificacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <NotificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(notificacaoService.buscarNotificacaoPorId(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar todas as notificações

    @GetMapping("/notificacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoResponseDTO> buscarNotificacoes(){
        return notificacaoService.buscarNotificacoes();
    }






}
