package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.PontoColetaRequestDTO;
import br.com.fiap.compartilhaja.dto.PontoColetaResponseDTO;
import br.com.fiap.compartilhaja.model.PontoColeta;
import br.com.fiap.compartilhaja.repository.PontoColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;


    // METODOS

    // Cadastro de pontos de coleta

    public PontoColetaRequestDTO cadastrarPontoColeta(PontoColetaRequestDTO pontoColetaDTO) {

        PontoColeta pontoColeta = new PontoColeta();
        BeanUtils.copyProperties(pontoColetaDTO, pontoColeta);
        PontoColeta pontoColetaSalvo = pontoColetaRepository.save(pontoColeta);
        return new PontoColetaRequestDTO(pontoColetaSalvo);
    }

    // Atualizar informações do ponto de coleta

    public PontoColeta atualizarPontoColeta (PontoColeta pontoColeta) {
        Optional<PontoColeta> pontoColetaExiste = pontoColetaRepository.findById(pontoColeta.getPontoColetaId());
        if (pontoColetaExiste.isPresent()) {
            return pontoColetaRepository.save(pontoColeta);
        } else{
            throw new RuntimeException("Ponto de coleta não encontrado!");
        }
    }

    // Excluir ponto de coleta pelo id
    public void excluirPontoColeta(Long id){
        Optional<PontoColeta> pontoColetaExiste = pontoColetaRepository.findById(id);
        if (pontoColetaExiste.isPresent()) {
            pontoColetaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ponto de coleta não encontrado!");
        }
    }

    // Buscar ponto de coleta pelo id
    public PontoColetaResponseDTO buscarPontoColetaPorId(Long id){
        Optional<PontoColeta> pontoColetaExiste = pontoColetaRepository.findById(id);
        if (pontoColetaExiste.isPresent()) {
            return new PontoColetaResponseDTO(pontoColetaExiste.get());
        }  else {
            throw new RuntimeException("Ponto de coleta não existe!");
        }
    }

    // Listar todos os pontos de coleta

    public List<PontoColetaResponseDTO> buscarPontosColeta(){
        return pontoColetaRepository
                .findAll()
                .stream()
                .map(PontoColetaResponseDTO::new)
                .toList();
    }

}
