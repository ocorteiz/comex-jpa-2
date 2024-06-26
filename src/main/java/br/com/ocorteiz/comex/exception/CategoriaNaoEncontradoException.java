package br.com.ocorteiz.comex.exception;

import javax.persistence.NoResultException;

public class CategoriaNaoEncontradoException extends NoResultException {
    public CategoriaNaoEncontradoException(String message) {
        super(message);
    }
}
