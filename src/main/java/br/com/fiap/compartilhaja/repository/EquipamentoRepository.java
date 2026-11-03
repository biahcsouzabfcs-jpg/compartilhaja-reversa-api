package br.com.fiap.compartilhaja.repository;

import br.com.fiap.compartilhaja.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
