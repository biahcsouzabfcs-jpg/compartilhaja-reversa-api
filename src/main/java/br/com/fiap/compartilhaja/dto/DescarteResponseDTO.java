package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.Descarte;

import java.time.LocalDate;

public record DescarteResponseDTO(
        Long descarteId,
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
    public DescarteResponseDTO(Descarte descarte){
        this(
                descarte.getDescarteId(),
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
