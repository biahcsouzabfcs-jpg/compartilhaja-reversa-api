package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.PontoColeta;
import jakarta.validation.constraints.*;

public record PontoColetaRequestDTO(
        @NotBlank(message = "Nome do ponto de coleta é obrigatório")
        @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
        String nome,

        @NotBlank(message = "Tipo de ponto de coleta é obrigatório")
        @Size(min = 3, max = 255, message = "Tipo deve ter entre 3 e 255 caracteres")
        String tipo,

        @NotBlank(message = "Tipo de resíduo aceito é obrigatório")
        @Size(min = 3, max = 255, message = "Resíduo aceito deve ter entre 3 e 255 caracteres")
        String residuoAceito,

        @NotBlank(message = "Cidade é obrigatória")
        @Size(min = 3, max = 255, message = "Cidade deve ter entre 3 e 255 caracteres")
        String cidade,

        @NotBlank(message = "UF (Estado) é obrigatório")
        @Size(min = 2, max = 255, message = "UF deve ter entre 2 e 255 caracteres")
        String uf,

        @NotBlank(message = "Email de contato é obrigatório")
        @Email(message = "Email deve ser válido")
        @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
        String emailContato,

        @NotNull(message = "Capacidade em kg é obrigatória")
        @Positive(message = "Capacidade deve ser um valor positivo")
        Double capacidadeKg,

        @NotNull(message = "Peso acumulado é obrigatório")
        @PositiveOrZero(message = "Peso acumulado deve ser zero ou positivo")
        Double pesoAcumuladoKg,

        @NotNull(message = "Limite de alerta é obrigatório")
        @Min(value = 1, message = "Limite deve ser no mínimo 1%")
        @Max(value = 100, message = "Limite deve ser no máximo 100%")
        Integer limiteAlertaPct,

        @NotBlank(message = "Status é obrigatório")
        @Size(min = 3, max = 255, message = "Status deve ter entre 3 e 255 caracteres")
        String status
) {
    public PontoColetaRequestDTO(PontoColeta pontoColeta){
        this(
                pontoColeta.getNome(),
                pontoColeta.getTipo(),
                pontoColeta.getResiduoAceito(),
                pontoColeta.getCidade(),
                pontoColeta.getUf(),
                pontoColeta.getEmailContato(),
                pontoColeta.getCapacidadeKg(),
                pontoColeta.getPesoAcumuladoKg(),
                pontoColeta.getLimiteAlertaPct(),
                pontoColeta.getStatus()
        );
    }
}
