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
@Table(name = "tbl_descartes")
public class Descarte {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DESCARTES"
    )
    @SequenceGenerator(
            name = "SEQ_DESCARTES",
            sequenceName = "SEQ_DESCARTES",
            allocationSize = 1
    )
    @Column(name = "descarte_id")
    private Long descarteId;
    private Long equipamentoId;
    private Long inspecaoId;
    private Long pontoColetaId;
    private String destinacao;
    private Double pesoKg;
    private String status;
    private String numeroCertificado;
    private LocalDate dataRegistro;
    private LocalDate dataConclusao;

}
