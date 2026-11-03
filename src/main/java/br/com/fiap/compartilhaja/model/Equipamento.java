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
@Table(name = "tbl_equipamentos")


public class Equipamento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_EQUIPAMENTOS"

    )

    @SequenceGenerator(
            name = "SEQ_EQUIPAMENTOS",
            sequenceName = "SEQ_EQUIPAMENTOS",
            allocationSize = 1
    )
    @Column(name = "equipamento_id")
    private Long equipamentoId;
    private String nome;
    private String categoria;
    private String marca;
    private String modelo;
    private Double pesoKg;
    private String estadoConvervacao;
    private String status;
    private String nomeProprietario;
    private String emailProprietario;
    private LocalDate dataCadastro;


}

