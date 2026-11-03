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
@Table(name = "tbl_notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_NOTIFICACOES"
    )
    @SequenceGenerator(
            name = "SEQ_NOTIFICACOES",
            sequenceName = "SEQ_NOTIFICACOES",
            allocationSize = 1
    )
    @Column(name = "notificacao_id")
    private Long notificacaoId;
    private Long equipamentoId;
    private Long pontoColetaId;
    private Long descarteId;
    private String tipo;
    private String destinatarioEmail;
    private String assunto;
    private String mensagem;
    private String statusEnvio;
    private LocalDate dataCriacao;

}
