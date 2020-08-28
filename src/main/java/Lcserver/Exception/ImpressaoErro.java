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
//public class ImpressaoErro extends RuntimeException {
public class ImpressaoErro extends RuntimeException {

    public ImpressaoErro(String mensagem) {
        super(mensagem);
    }

    public ImpressaoErro(String message, Throwable cause) {
        super(message, cause);
    }
    

}
