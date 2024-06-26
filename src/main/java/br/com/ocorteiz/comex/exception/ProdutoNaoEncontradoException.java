package br.com.ocorteiz.comex.exception;

import javax.persistence.NoResultException;

public class ProdutoNaoEncontradoException extends NoResultException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
