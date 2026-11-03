package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Equipamento;


// DTO para cadastro do produto

public record EquipamentoRequestDTO(
        String nome,
        String categoria,
        String marca,
        String modelo,
        Double pesoKg,
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

