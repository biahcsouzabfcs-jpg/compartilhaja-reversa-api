package br.com.fiap.compartilhaja.repository;

import br.com.fiap.compartilhaja.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
