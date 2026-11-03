package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Descarte;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DescarteRequestDTO(
        @NotNull(message = "ID do equipamento é obrigatório")
        @Positive(message = "ID do equipamento deve ser um número positivo")
        Long equipamentoId,

        @NotNull(message = "ID da inspeção é obrigatório")
        @Positive(message = "ID da inspeção deve ser um número positivo")
        Long inspecaoId,

        @NotNull(message = "ID do ponto de coleta é obrigatório")
        @Positive(message = "ID do ponto de coleta deve ser um número positivo")
        Long pontoColetaId,

        @NotBlank(message = "Destinação é obrigatória")
        @Size(min = 3, max = 255, message = "Destinação deve ter entre 3 e 255 caracteres")
        String destinacao,

        @NotNull(message = "Peso em kg é obrigatório")
        @Positive(message = "Peso deve ser um valor positivo")
        Double pesoKg,

        @NotBlank(message = "Status é obrigatório")
        @Size(min = 2, max = 255, message = "Status deve ter entre 2 e 255 caracteres")
        String status,

        @Size(max = 255, message = "Número do certificado deve ter no máximo 255 caracteres")
        String numeroCertificado,

        @NotNull(message = "Data de registro é obrigatória")
        @PastOrPresent(message = "Data de registro não pode ser no futuro")
        LocalDate dataRegistro,

        @PastOrPresent(message = "Data de conclusão não pode ser no futuro")
        LocalDate dataConclusao
) {
    public DescarteRequestDTO(Descarte descarte){
        this(
                descarte.getEquipamentoId(),
                descarte.getInspecaoId(),
                descarte.getPontoColetaId(),
                descarte.getDestinacao(),
                descarte.getPesoKg(),
                descarte.getStatus(),
                descarte.getNumeroCertificado(),
                descarte.getDataRegistro(),
                descarte.getDataConclusao()
        );
    }
}
