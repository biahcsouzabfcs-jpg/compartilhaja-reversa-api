package br.com.fiap.compartilhaja.service;

import br.com.fiap.compartilhaja.dto.NotificacaoRequestDTO;
import br.com.fiap.compartilhaja.dto.NotificacaoResponseDTO;
import br.com.fiap.compartilhaja.model.Notificacao;
import br.com.fiap.compartilhaja.repository.NotificacaoRepository;
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

class NotificacaoServiceTest {

    private NotificacaoService service;
    private Map<Long, Notificacao> registros;

    @BeforeEach
    void preparar() {
        registros = new HashMap<>();
        NotificacaoRepository repository = mock(NotificacaoRepository.class);
        service = new NotificacaoService();
        ReflectionTestUtils.setField(service, "notificacaoRepository", repository);
        when(repository.findById(anyLong())).thenAnswer(invocation ->
                Optional.ofNullable(registros.get(invocation.getArgument(0))));
        when(repository.save(any(Notificacao.class))).thenAnswer(invocation -> {
            Notificacao registro = invocation.getArgument(0);
            if (registro.getNotificacaoId() == null) {
                registro.setNotificacaoId(1L);
            }
            registros.put(registro.getNotificacaoId(), registro);
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
        NotificacaoRequestDTO cadastro = new NotificacaoRequestDTO(
                10L,
                30L,
                40L,
                "EMAIL",
                "dono@exemplo.com",
                "Descarte concluído",
                "Certificado disponível",
                "ENVIADO",
                LocalDate.of(2026, 11, 3)
        );
        assertEquals(cadastro, service.cadastrarNotificacao(cadastro));
        NotificacaoResponseDTO esperado = new NotificacaoResponseDTO(
                1L,
                10L,
                30L,
                40L,
                "EMAIL",
                "dono@exemplo.com",
                "Descarte concluído",
                "Certificado disponível",
                "ENVIADO",
                LocalDate.of(2026, 11, 3)
        );
        assertEquals(esperado, service.buscarNotificacaoPorId(1L));

        Notificacao atualizado = new Notificacao();
        atualizado.setNotificacaoId(1L);
        atualizado.setTipo("ATUALIZADO");
        service.atualizarNotificacao(atualizado);
        assertEquals("ATUALIZADO", service.buscarNotificacaoPorId(1L).tipo());
        assertEquals(List.of(service.buscarNotificacaoPorId(1L)), service.buscarNotificacoes());

        service.excluirNotificacao(1L);
        assertTrue(service.buscarNotificacoes().isEmpty());
        assertThrows(RuntimeException.class, () -> service.buscarNotificacaoPorId(1L));
    }

    @Test
    void naoAtualizarRegistroInexistente() {
        Notificacao registro = new Notificacao();
        registro.setNotificacaoId(99L);
        assertThrows(RuntimeException.class, () -> service.atualizarNotificacao(registro));
        assertTrue(registros.isEmpty());
    }

    @Test
    void naoExcluirRegistroInexistente() {
        assertThrows(RuntimeException.class, () -> service.excluirNotificacao(99L));
    }
}

