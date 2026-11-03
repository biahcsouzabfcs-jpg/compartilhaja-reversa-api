package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.InspecaoRequestDTO;
import br.com.fiap.compartilhaja.dto.InspecaoResponseDTO;
import br.com.fiap.compartilhaja.model.Inspecao;
import br.com.fiap.compartilhaja.repository.InspecaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspecaoService {

    @Autowired
    private InspecaoRepository inspecaoRepository;


    // METODOS

    // Cadastro de inspeções

    public InspecaoRequestDTO cadastrarInspecao(InspecaoRequestDTO inspecaoDTO) {

        Inspecao inspecao = new Inspecao();
        BeanUtils.copyProperties(inspecaoDTO, inspecao);
        Inspecao inspecaoSalvo = inspecaoRepository.save(inspecao);
        return new InspecaoRequestDTO(inspecaoSalvo);
    }

    // Atualizar informações da inspeção

    public Inspecao atualizarInspecao (Inspecao inspecao) {
        Optional<Inspecao> inspecaoExiste = inspecaoRepository.findById(inspecao.getInspecaoId());
        if (inspecaoExiste.isPresent()) {
            return inspecaoRepository.save(inspecao);
        } else{
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Inspeção não encontrado!");
        }
    }

    // Excluir inspeção pelo id
    public void excluirInspecao(Long id){
        Optional<Inspecao> inspecaoExiste = inspecaoRepository.findById(id);
        if (inspecaoExiste.isPresent()) {
            inspecaoRepository.deleteById(id);
        } else {
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Inspeção não encontrado!");
        }
    }

    // Buscar inspeção pelo id
    public InspecaoResponseDTO buscarInspecaoPorId(Long id){
        Optional<Inspecao> inspecaoExiste = inspecaoRepository.findById(id);
        if (inspecaoExiste.isPresent()) {
            return new InspecaoResponseDTO(inspecaoExiste.get());
        }  else {
            throw new br.com.fiap.compartilhaja.exception.ResourceNotFoundException("Inspeção não existe!");
        }
    }

    // Listar todas as inspeções

    public List<InspecaoResponseDTO> buscarInspecoes(){
        return inspecaoRepository
                .findAll()
                .stream()
                .map(InspecaoResponseDTO::new)
                .toList();
    }

}
