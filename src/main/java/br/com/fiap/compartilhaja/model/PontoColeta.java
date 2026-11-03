package br.com.fiap.compartilhaja.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tbl_pontos_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PONTOS_COLETA"
    )
    @SequenceGenerator(
            name = "SEQ_PONTOS_COLETA",
            sequenceName = "SEQ_PONTOS_COLETA",
            allocationSize = 1
    )
    @Column(name = "ponto_coleta_id")
    private Long pontoColetaId;
    private String nome;
    private String tipo;
    private String residuoAceito;
    private String cidade;
    private String uf;
    private String emailContato;
    private Double capacidadeKg;
    private Double pesoAcumuladoKg;
    private Integer limiteAlertaPct;
    private String status;

}
