/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Exception;

import Lcserver.PortalApi.dto.input.ErrorPortalApiDtoInput;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.springframework.http.HttpStatus;

/**
 *
 * @author JORDAN QUEIROGA
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Erro implements Serializable {

    private Integer statusCode;
    private String statusMessage;
    private String httpMethod;
    private String erroMensagem;
    private String erroCause;
    private String detalhe;
    private String path;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getErroMensagem() {
        return erroMensagem;
    }

    public String getErroCause() {
        return erroCause;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getPath() {
        return path;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Erro erro;

        Builder() {
            this.erro = new Erro();
        }

        public Builder addStatus(HttpStatus status) {
            this.erro.statusCode = status.value();
            this.erro.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addErroMensagem(String erro) {
            this.erro.erroMensagem = erro;
            return this;
        }

        public Builder addErroCause(String erroCause) {
            this.erro.erroCause = erroCause;
            return this;
        }

        public Builder addDetalhe(String detalhe) {
            this.erro.detalhe = detalhe;
            return this;
        }

        public Builder addPath(String path) {
            this.erro.path = path;
            return this;
        }

        public Erro build() {
            return this.erro;
        }
    }

}
