package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Notificacao;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NotificacaoRequestDTO(
        @Positive(message = "ID do equipamento deve ser um número positivo")
        Long equipamentoId,

        @Positive(message = "ID do ponto de coleta deve ser um número positivo")
        Long pontoColetaId,

        @Positive(message = "ID do descarte deve ser um número positivo")
        Long descarteId,

        @NotBlank(message = "Tipo de notificação é obrigatório")
        @Size(min = 3, max = 255, message = "Tipo deve ter entre 3 e 255 caracteres")
        String tipo,

        @NotBlank(message = "Email do destinatário é obrigatório")
        @Email(message = "Email deve ser válido")
        @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
        String destinatarioEmail,

        @NotBlank(message = "Assunto é obrigatório")
        @Size(min = 5, max = 255, message = "Assunto deve ter entre 5 e 255 caracteres")
        String assunto,

        @NotBlank(message = "Mensagem é obrigatória")
        @Size(min = 10, max = 255, message = "Mensagem deve ter entre 10 e 255 caracteres")
        String mensagem,

        @NotBlank(message = "Status de envio é obrigatório")
        @Size(min = 3, max = 255, message = "Status de envio deve ter entre 3 e 255 caracteres")
        String statusEnvio,

        @NotNull(message = "Data de criação é obrigatória")
        @PastOrPresent(message = "Data de criação não pode ser no futuro")
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
