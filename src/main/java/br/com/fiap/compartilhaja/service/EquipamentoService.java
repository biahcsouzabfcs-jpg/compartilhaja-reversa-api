package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.EquipamentoRequestDTO;
import br.com.fiap.compartilhaja.dto.EquipamentoResponseDTO;
import br.com.fiap.compartilhaja.model.Equipamento;
import br.com.fiap.compartilhaja.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    // METODOS

    // Cadastro de equipamentos

    public EquipamentoRequestDTO cadastrarEquipamento(EquipamentoRequestDTO equipamentoDTO) {

        // Mapeamento explícito: BeanUtils não funciona com records (não possuem getters),
        // e o campo do DTO (estadoConservacao) tem nome diferente da entidade (estadoConvervacao).
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(equipamentoDTO.nome());
        equipamento.setCategoria(equipamentoDTO.categoria());
        equipamento.setMarca(equipamentoDTO.marca());
        equipamento.setModelo(equipamentoDTO.modelo());
        equipamento.setPesoKg(equipamentoDTO.pesoKg());
        equipamento.setEstadoConvervacao(equipamentoDTO.estadoConservacao());
        equipamento.setStatus(equipamentoDTO.status());
        equipamento.setNomeProprietario(equipamentoDTO.nomeProprietario());
        equipamento.setEmailProprietario(equipamentoDTO.emailProprietario());
        equipamento.setDataCadastro(LocalDate.now());

        Equipamento equipamentoSalvo = equipamentoRepository.save(equipamento);
        return new EquipamentoRequestDTO(equipamentoSalvo);
    }

    // Atualizar informações do  equipamento

    public Equipamento atualizarEquipamento (Equipamento equipamento) {
        Optional<Equipamento> equipamentoExiste = equipamentoRepository.findById(equipamento.getEquipamentoId());
        if (equipamentoExiste.isPresent()) {
            return equipamentoRepository.save(equipamento);
        } else{
            throw new RuntimeException("Equipamento não encontrado!");
        }
    }

    // Excluir equipamento pelo id
    public void excluirEquipamento(Long id){
        Optional<Equipamento> equipamentoExiste = equipamentoRepository.findById(id);
        if (equipamentoExiste.isPresent()) {
            equipamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Equipamento não encontrado!");
        }
    }

    // Buscar Equipamentos pelo id
    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id){
        Optional<Equipamento> equipamentoExiste = equipamentoRepository.findById(id);
        if (equipamentoExiste.isPresent()) {
            return new EquipamentoResponseDTO(equipamentoExiste.get());
        }  else {
            throw new RuntimeException("Equipamento não existe!");
        }
    }

    // Listar todos os equipamentos

    public List<EquipamentoResponseDTO> buscarEquipamentos(){
        return equipamentoRepository
                .findAll()
                .stream()
                .map(EquipamentoResponseDTO::new)
                .toList();
    }

}
