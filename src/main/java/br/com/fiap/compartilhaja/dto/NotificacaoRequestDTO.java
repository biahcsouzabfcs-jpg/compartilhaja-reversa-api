package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Notificacao;

import java.time.LocalDate;

public record NotificacaoRequestDTO(
        Long equipamentoId,
        Long pontoColetaId,
        Long descarteId,
        String tipo,
        String destinatarioEmail,
        String assunto,
        String mensagem,
        String statusEnvio,
        LocalDate dataCriacao
) {
    public NotificacaoRequestDTO(Notificacao notificacao){
        this(
                notificacao.getEquipamentoId(),
                notificacao.getPontoColetaId(),
                notificacao.getDescarteId(),
                notificacao.getTipo(),
                notificacao.getDestinatarioEmail(),
                notificacao.getAssunto(),
                notificacao.getMensagem(),
                notificacao.getStatusEnvio(),
                notificacao.getDataCriacao()
        );
    }
}
