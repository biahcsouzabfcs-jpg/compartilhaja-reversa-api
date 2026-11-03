package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Equipamento;
import jakarta.validation.constraints.*;

public record EquipamentoRequestDTO(
        @NotBlank(message = "Nome do equipamento é obrigatório")
        @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
        String nome,

        @NotBlank(message = "Categoria é obrigatória")
        @Size(min = 3, max = 255, message = "Categoria deve ter entre 3 e 255 caracteres")
        String categoria,

        @NotBlank(message = "Marca é obrigatória")
        @Size(min = 2, max = 255, message = "Marca deve ter entre 2 e 255 caracteres")
        String marca,

        @NotBlank(message = "Modelo é obrigatório")
        @Size(min = 2, max = 255, message = "Modelo deve ter entre 2 e 255 caracteres")
        String modelo,

        @NotNull(message = "Peso em kg é obrigatório")
        @Positive(message = "Peso deve ser um valor positivo")
        Double pesoKg,

        @NotBlank(message = "Estado de conservação é obrigatório")
        @Size(min = 3, max = 255, message = "Estado de conservação deve ter entre 3 e 255 caracteres")
        String estadoConservacao
) {
    public EquipamentoRequestDTO(Equipamento equipamento){
        this(
                equipamento.getNome(),
                equipamento.getCategoria(),
                equipamento.getMarca(),
                equipamento.getModelo(),
                equipamento.getPesoKg(),
                equipamento.getEstadoConvervacao()
        );
    }
}

