package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Inspecao;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record InspecaoRequestDTO(
        @NotNull(message = "ID do equipamento é obrigatório")
        @Positive(message = "ID do equipamento deve ser um número positivo")
        Long equipamentoId,

        @NotNull(message = "Data da inspeção é obrigatória")
        @PastOrPresent(message = "Data da inspeção não pode ser no futuro")
        LocalDate dataInspecao,

        @NotBlank(message = "Responsável pela inspeção é obrigatório")
        @Size(min = 3, max = 255, message = "Nome do responsável deve ter entre 3 e 255 caracteres")
        String responsavel,

        @NotBlank(message = "Resultado da inspeção é obrigatório")
        @Size(min = 3, max = 255, message = "Resultado deve ter entre 3 e 255 caracteres")
        String resultado,

        @NotBlank(message = "Informação sobre bateria é obrigatória")
        @Size(min = 2, max = 255, message = "Informação sobre bateria deve ter entre 2 e 255 caracteres")
        String possuiBateria,

        @Size(max = 255, message = "Descrição de avarias deve ter no máximo 255 caracteres")
        String descricaoAvarias
) {
    public InspecaoRequestDTO(Inspecao inspecao){
        this(
                inspecao.getEquipamentoId(),
                inspecao.getDataInspecao(),
                inspecao.getResponsavel(),
                inspecao.getResultado(),
                inspecao.getPossuiBateria(),
                inspecao.getDescricaoAvarias()
        );
    }
}
