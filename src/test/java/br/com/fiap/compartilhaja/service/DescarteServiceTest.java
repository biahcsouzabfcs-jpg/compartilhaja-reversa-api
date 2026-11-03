package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.DescarteRequestDTO;
import br.com.fiap.compartilhaja.dto.DescarteResponseDTO;
import br.com.fiap.compartilhaja.model.Descarte;
import br.com.fiap.compartilhaja.repository.DescarteRepository;
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

class DescarteServiceTest {

    private DescarteService service;
    private Map<Long, Descarte> registros;

    @BeforeEach
    void preparar() {
        registros = new HashMap<>();
        DescarteRepository repository = mock(DescarteRepository.class);
        service = new DescarteService();
        ReflectionTestUtils.setField(service, "descarteRepository", repository);
        when(repository.findById(anyLong())).thenAnswer(invocation ->
                Optional.ofNullable(registros.get(invocation.getArgument(0))));
        when(repository.save(any(Descarte.class))).thenAnswer(invocation -> {
            Descarte registro = invocation.getArgument(0);
            if (registro.getDescarteId() == null) {
                registro.setDescarteId(1L);
            }
            registros.put(registro.getDescarteId(), registro);
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
        DescarteRequestDTO cadastro = new DescarteRequestDTO(
                10L,
                20L,
                30L,
                "RECICLAGEM",
                2.5,
                "CONCLUIDO",
                "CERT-123",
                LocalDate.of(2026, 11, 2),
                LocalDate.of(2026, 11, 3)
        );
        assertEquals(cadastro, service.cadastrarDescarte(cadastro));
        DescarteResponseDTO esperado = new DescarteResponseDTO(
                1L,
                10L,
                20L,
                30L,
                "RECICLAGEM",
                2.5,
                "CONCLUIDO",
                "CERT-123",
                LocalDate.of(2026, 11, 2),
                LocalDate.of(2026, 11, 3)
        );
        assertEquals(esperado, service.buscarDescartePorId(1L));

        Descarte atualizado = new Descarte();
        atualizado.setDescarteId(1L);
        atualizado.setDestinacao("ATUALIZADO");
        service.atualizarDescarte(atualizado);
        assertEquals("ATUALIZADO", service.buscarDescartePorId(1L).destinacao());
        assertEquals(List.of(service.buscarDescartePorId(1L)), service.buscarDescartes());

        service.excluirDescarte(1L);
        assertTrue(service.buscarDescartes().isEmpty());
        assertThrows(RuntimeException.class, () -> service.buscarDescartePorId(1L));
    }

    @Test
    void naoAtualizarRegistroInexistente() {
        Descarte registro = new Descarte();
        registro.setDescarteId(99L);
        assertThrows(RuntimeException.class, () -> service.atualizarDescarte(registro));
        assertTrue(registros.isEmpty());
    }

    @Test
    void naoExcluirRegistroInexistente() {
        assertThrows(RuntimeException.class, () -> service.excluirDescarte(99L));
    }
}

