package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Descarte;

import java.time.LocalDate;

public record DescarteRequestDTO(
        Long equipamentoId,
        Long inspecaoId,
        Long pontoColetaId,
        String destinacao,
        Double pesoKg,
        String status,
        String numeroCertificado,
        LocalDate dataRegistro,
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
