package br.com.fiap.compartilhaja.exception;

// Exceção lançada quando um recurso (descarte, equipamento, inspeção, notificação ou ponto de coleta) não é encontrado no banco de dados.
// Estende RuntimeException para não exigir try/catch nos services e controllers.

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

}
