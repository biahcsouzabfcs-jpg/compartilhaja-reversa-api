package br.com.fiap.compartilhaja.dto;

import br.com.fiap.compartilhaja.model.PontoColeta;

public record PontoColetaRequestDTO(
        String nome,
        String tipo,
        String residuoAceito,
        String cidade,
        String uf,
        String emailContato,
        Double capacidadeKg,
        Double pesoAcumuladoKg,
        Integer limiteAlertaPct,
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
