package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.DescarteRequestDTO;
import br.com.fiap.compartilhaja.dto.DescarteResponseDTO;
import br.com.fiap.compartilhaja.model.Descarte;
import br.com.fiap.compartilhaja.repository.DescarteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescarteService {

    @Autowired
    private DescarteRepository descarteRepository;


    // METODOS

    // Cadastro de descartes

    public DescarteRequestDTO cadastrarDescarte(DescarteRequestDTO descarteDTO) {

        Descarte descarte = new Descarte();
        BeanUtils.copyProperties(descarteDTO, descarte);
        Descarte descarteSalvo = descarteRepository.save(descarte);
        return new DescarteRequestDTO(descarteSalvo);
    }

    // Atualizar informações do descarte

    public Descarte atualizarDescarte (Descarte descarte) {
        Optional<Descarte> descarteExiste = descarteRepository.findById(descarte.getDescarteId());
        if (descarteExiste.isPresent()) {
            return descarteRepository.save(descarte);
        } else{
            throw new RuntimeException("Descarte não encontrado!");
        }
    }

    // Excluir descarte pelo id
    public void excluirDescarte(Long id){
        Optional<Descarte> descarteExiste = descarteRepository.findById(id);
        if (descarteExiste.isPresent()) {
            descarteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Descarte não encontrado!");
        }
    }

    // Buscar descarte pelo id
    public DescarteResponseDTO buscarDescartePorId(Long id){
        Optional<Descarte> descarteExiste = descarteRepository.findById(id);
        if (descarteExiste.isPresent()) {
            return new DescarteResponseDTO(descarteExiste.get());
        }  else {
            throw new RuntimeException("Descarte não existe!");
        }
    }

    // Listar todos os descartes

    public List<DescarteResponseDTO> buscarDescartes(){
        return descarteRepository
                .findAll()
                .stream()
                .map(DescarteResponseDTO::new)
                .toList();
    }

}
