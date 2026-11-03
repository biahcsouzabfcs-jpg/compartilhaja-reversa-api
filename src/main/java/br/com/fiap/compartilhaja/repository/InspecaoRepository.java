package br.com.fiap.compartilhaja.repository;

import br.com.fiap.compartilhaja.model.Inspecao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoRepository extends JpaRepository<Inspecao, Long> {
}
