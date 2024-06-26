package br.com.ocorteiz.comex.exception;

import javax.persistence.NoResultException;

public class ClienteNaoEncontradoException extends NoResultException {
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
