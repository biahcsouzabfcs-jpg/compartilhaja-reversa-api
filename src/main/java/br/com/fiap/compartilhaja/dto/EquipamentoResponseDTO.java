package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Equipamento;

import java.time.LocalDate;

// DTO para resposta

public record EquipamentoResponseDTO(
        Long equipamentoId,
        String nome,
        String categoria,
        String marca,
        String modelo,
        Double pesoKg,
        String estadoConservacao,
        String status,
        String nomeProprietario,
        LocalDate dataCadastro
) {
    public EquipamentoResponseDTO(Equipamento equipamento){
        this(
                equipamento.getEquipamentoId(),
                equipamento.getNome(),
                equipamento.getCategoria(),
                equipamento.getMarca(),
                equipamento.getModelo(),
                equipamento.getPesoKg(),
                equipamento.getEstadoConvervacao(),
                equipamento.getStatus(),
                equipamento.getNomeProprietario(),
                equipamento.getDataCadastro()
        );
    }
}