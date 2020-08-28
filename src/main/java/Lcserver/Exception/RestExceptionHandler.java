/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Exception;

import Lcserver.ApiwebApplication;
import Lcserver.TelaPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author JORDAN QUEIROGA
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ImpressaoErro.class})
    public ResponseEntity<Object> erroImpressao(ImpressaoErro ex, WebRequest request) {
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Houve um erro ao tentar realizar a impressão no servidor!")
                .addErroMensagem(ex.getMessage())
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addStatus(HttpStatus.VARIANT_ALSO_NEGOTIATES)
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.VARIANT_ALSO_NEGOTIATES, request);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolada(org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, Erro.builder()
                .addDetalhe("Constraint violada: " + ex.getConstraintName())
                .addErroMensagem(ex.getMessage())
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addStatus(HttpStatus.CONFLICT)
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({org.hibernate.PropertyValueException.class})
    public ResponseEntity<Object> propriedadeNula(org.hibernate.PropertyValueException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("O atributo '" + ex.getPropertyName() + "' não pode ser nulo.")
                .addErroMensagem(ex.getMessage())
                .addStatus(HttpStatus.BAD_REQUEST)
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Recurso não encontrado na base de dados.")
                .addErroMensagem(ex.getMessage())
                .addStatus(HttpStatus.NOT_FOUND)
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PermissaoInsuficienteException.class})
    public ResponseEntity<Object> usuarioInativo(PermissaoInsuficienteException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Não possui permissão suficiente para esta operação!")
                .addErroMensagem(ex.getMessage())
                .addStatus(HttpStatus.FORBIDDEN)
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({CredencialInvalidaException.class})
    public ResponseEntity<Object> credencialInvalida(CredencialInvalidaException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Credencial de usuário inválida!")
                .addErroMensagem(ex.getMessage())
                .addStatus(HttpStatus.UNAUTHORIZED)
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {
        TelaPrincipal.TelaPrincipal.setErro("Um exceção foi lançada.", ex);
        ApiwebApplication.auditoriaControle.erro(RestExceptionHandler.class, getHttpMethod(request) + " " + getPath(request), ex);
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Um exceção foi lançada.")
                .addErroMensagem(ex.getMessage())
                .addErroCause(ex.getCause().toString())
                .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({OperacaoInvalidaException.class})
    public ResponseEntity<Object> operacaoInvalida(OperacaoInvalidaException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, Erro.builder()
                .addDetalhe("Esta operação não pode ser realizada!")
                .addErroMensagem(ex.getMessage())
                .addStatus(HttpStatus.NOT_ACCEPTABLE)
                .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    private String getPath(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getMethod();
    }
}
