/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Exception;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class PermissaoInsuficienteException extends RuntimeException {

    private static final String MSG_PADRAO_INATIVO  = "Usuário Inativo! Ative-o no Servidor!";

    public PermissaoInsuficienteException(String message) {
        super(message);
    }
    
    public PermissaoInsuficienteException() {
        super(MSG_PADRAO_INATIVO);
    }
}
