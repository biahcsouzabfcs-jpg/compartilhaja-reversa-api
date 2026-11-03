package br.com.fiap.compartilhaja.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tbl_inspecoes")
public class Inspecao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_INSPECOES"
    )
    @SequenceGenerator(
            name = "SEQ_INSPECOES",
            sequenceName = "SEQ_INSPECOES",
            allocationSize = 1
    )
    @Column(name = "inspecao_id")
    private Long inspecaoId;
    private Long equipamentoId;
    private LocalDate dataInspecao;
    private String responsavel;
    private String resultado;
    private String possuiBateria;
    private String descricaoAvarias;

}
