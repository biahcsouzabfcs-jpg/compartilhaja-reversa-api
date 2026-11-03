package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Inspecao;

import java.time.LocalDate;

public record InspecaoRequestDTO(
        Long equipamentoId,
        LocalDate dataInspecao,
        String responsavel,
        String resultado,
        String possuiBateria,
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
