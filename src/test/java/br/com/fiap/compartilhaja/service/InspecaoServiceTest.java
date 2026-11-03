package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.InspecaoRequestDTO;
import br.com.fiap.compartilhaja.dto.InspecaoResponseDTO;
import br.com.fiap.compartilhaja.model.Inspecao;
import br.com.fiap.compartilhaja.repository.InspecaoRepository;
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

class InspecaoServiceTest {

    private InspecaoService service;
    private Map<Long, Inspecao> registros;

    @BeforeEach
    void preparar() {
        registros = new HashMap<>();
        InspecaoRepository repository = mock(InspecaoRepository.class);
        service = new InspecaoService();
        ReflectionTestUtils.setField(service, "inspecaoRepository", repository);
        when(repository.findById(anyLong())).thenAnswer(invocation ->
                Optional.ofNullable(registros.get(invocation.getArgument(0))));
        when(repository.save(any(Inspecao.class))).thenAnswer(invocation -> {
            Inspecao registro = invocation.getArgument(0);
            if (registro.getInspecaoId() == null) {
                registro.setInspecaoId(1L);
            }
            registros.put(registro.getInspecaoId(), registro);
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
        InspecaoRequestDTO cadastro = new InspecaoRequestDTO(
                10L,
                LocalDate.of(2026, 11, 3),
                "Ana",
                "APROVADO",
                "S",
                "Tela danificada"
        );
        assertEquals(cadastro, service.cadastrarInspecao(cadastro));
        InspecaoResponseDTO esperado = new InspecaoResponseDTO(
                1L,
                10L,
                LocalDate.of(2026, 11, 3),
                "Ana",
                "APROVADO",
                "S",
                "Tela danificada"
        );
        assertEquals(esperado, service.buscarInspecaoPorId(1L));

        Inspecao atualizado = new Inspecao();
        atualizado.setInspecaoId(1L);
        atualizado.setResponsavel("ATUALIZADO");
        service.atualizarInspecao(atualizado);
        assertEquals("ATUALIZADO", service.buscarInspecaoPorId(1L).responsavel());
        assertEquals(List.of(service.buscarInspecaoPorId(1L)), service.buscarInspecoes());

        service.excluirInspecao(1L);
        assertTrue(service.buscarInspecoes().isEmpty());
        assertThrows(RuntimeException.class, () -> service.buscarInspecaoPorId(1L));
    }

    @Test
    void naoAtualizarRegistroInexistente() {
        Inspecao registro = new Inspecao();
        registro.setInspecaoId(99L);
        assertThrows(RuntimeException.class, () -> service.atualizarInspecao(registro));
        assertTrue(registros.isEmpty());
    }

    @Test
    void naoExcluirRegistroInexistente() {
        assertThrows(RuntimeException.class, () -> service.excluirInspecao(99L));
    }
}

