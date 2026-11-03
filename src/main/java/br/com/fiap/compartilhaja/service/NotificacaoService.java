package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.NotificacaoRequestDTO;
import br.com.fiap.compartilhaja.dto.NotificacaoResponseDTO;
import br.com.fiap.compartilhaja.model.Notificacao;
import br.com.fiap.compartilhaja.repository.NotificacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;


    // METODOS

    // Cadastro de notificações

    public NotificacaoRequestDTO cadastrarNotificacao(NotificacaoRequestDTO notificacaoDTO) {

        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        Notificacao notificacaoSalvo = notificacaoRepository.save(notificacao);
        return new NotificacaoRequestDTO(notificacaoSalvo);
    }

    // Atualizar informações da notificação

    public Notificacao atualizarNotificacao (Notificacao notificacao) {
        Optional<Notificacao> notificacaoExiste = notificacaoRepository.findById(notificacao.getNotificacaoId());
        if (notificacaoExiste.isPresent()) {
            return notificacaoRepository.save(notificacao);
        } else{
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Notificação não encontrado!");
        }
    }

    // Excluir notificação pelo id
    public void excluirNotificacao(Long id){
        Optional<Notificacao> notificacaoExiste = notificacaoRepository.findById(id);
        if (notificacaoExiste.isPresent()) {
            notificacaoRepository.deleteById(id);
        } else {
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Notificação não encontrado!");
        }
    }

    // Buscar notificação pelo id
    public NotificacaoResponseDTO buscarNotificacaoPorId(Long id){
        Optional<Notificacao> notificacaoExiste = notificacaoRepository.findById(id);
        if (notificacaoExiste.isPresent()) {
            return new NotificacaoResponseDTO(notificacaoExiste.get());
        }  else {
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Notificação não existe!");
        }
    }

    // Listar todas as notificações

    public List<NotificacaoResponseDTO> buscarNotificacoes(){
        return notificacaoRepository
                .findAll()
                .stream()
                .map(NotificacaoResponseDTO::new)
                .toList();
    }

}
