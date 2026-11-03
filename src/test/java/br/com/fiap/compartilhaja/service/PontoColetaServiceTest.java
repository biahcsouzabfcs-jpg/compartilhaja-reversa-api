package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.PontoColetaRequestDTO;
import br.com.fiap.compartilhaja.dto.PontoColetaResponseDTO;
import br.com.fiap.compartilhaja.model.PontoColeta;
import br.com.fiap.compartilhaja.repository.PontoColetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PontoColetaServiceTest {

    private PontoColetaService service;
    private Map<Long, PontoColeta> registros;

    @BeforeEach
    void preparar() {
        registros = new HashMap<>();
        PontoColetaRepository repository = mock(PontoColetaRepository.class);
        service = new PontoColetaService();
        ReflectionTestUtils.setField(service, "pontoColetaRepository", repository);
        when(repository.findById(anyLong())).thenAnswer(invocation ->
                Optional.ofNullable(registros.get(invocation.getArgument(0))));
        when(repository.save(any(PontoColeta.class))).thenAnswer(invocation -> {
            PontoColeta registro = invocation.getArgument(0);
            if (registro.getPontoColetaId() == null) {
                registro.setPontoColetaId(1L);
            }
            registros.put(registro.getPontoColetaId(), registro);
            return registro;
        });
        when(repository.findAll()).thenAnswer(invocation -> List.copyOf(registros.values()));
        doAnswer(invocation -> {
            registros.remove(invocation.<Long>getArgument(0));
            return null;
        }).when(repository).deleteById(anyLong());
    }

    @Test
    void cadastrarConsultarAtualizarListarEExcluir() {
        PontoColetaRequestDTO cadastro = new PontoColetaRequestDTO(
                "Centro",
                "COOPERATIVA",
                "ELETRONICO",
                "São Paulo",
                "SP",
                "contato@exemplo.com",
                500.0,
                150.0,
                80,
                "ATIVO"
        );
        assertEquals(cadastro, service.cadastrarPontoColeta(cadastro));
        PontoColetaResponseDTO esperado = new PontoColetaResponseDTO(
                1L,
                "Centro",
                "COOPERATIVA",
                "ELETRONICO",
                "São Paulo",
                "SP",
                "contato@exemplo.com",
                500.0,
                150.0,
                80,
                "ATIVO"
        );
        assertEquals(esperado, service.buscarPontoColetaPorId(1L));

        PontoColeta atualizado = new PontoColeta();
        atualizado.setPontoColetaId(1L);
        atualizado.setNome("ATUALIZADO");
        service.atualizarPontoColeta(atualizado);
        assertEquals("ATUALIZADO", service.buscarPontoColetaPorId(1L).nome());
        assertEquals(List.of(service.buscarPontoColetaPorId(1L)), service.buscarPontosColeta());

        service.excluirPontoColeta(1L);
        assertTrue(service.buscarPontosColeta().isEmpty());
        assertThrows(RuntimeException.class, () -> service.buscarPontoColetaPorId(1L));
    }

    @Test
    void naoAtualizarRegistroInexistente() {
        PontoColeta registro = new PontoColeta();
        registro.setPontoColetaId(99L);
        assertThrows(RuntimeException.class, () -> service.atualizarPontoColeta(registro));
        assertTrue(registros.isEmpty());
    }

    @Test
    void naoExcluirRegistroInexistente() {
        assertThrows(RuntimeException.class, () -> service.excluirPontoColeta(99L));
    }
}

