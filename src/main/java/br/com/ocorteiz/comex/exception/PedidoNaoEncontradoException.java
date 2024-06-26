package br.com.ocorteiz.comex.exception;

import javax.persistence.NoResultException;

public class PedidoNaoEncontradoException extends NoResultException {
    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
