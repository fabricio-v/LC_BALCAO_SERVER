/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.PortalApi.dto.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jordan LC Sistemas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorPortalApiDtoInput implements Serializable {

    private Integer codeStatus;
    private String statusMessage;
    private String httpMethod;
    private String message;
    private String error;
    private String detalhe;
    private String path;

    public ErrorPortalApiDtoInput() {
    }

    public ErrorPortalApiDtoInput(Integer codeStatus, String statusMessage, String httpMethod, String message, String error, String detalhe, String path) {
        this.codeStatus = codeStatus;
        this.statusMessage = statusMessage;
        this.httpMethod = httpMethod;
        this.message = message;
        this.error = error;
        this.detalhe = detalhe;
        this.path = path;
    }
    
    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.codeStatus);
        hash = 53 * hash + Objects.hashCode(this.statusMessage);
        hash = 53 * hash + Objects.hashCode(this.httpMethod);
        hash = 53 * hash + Objects.hashCode(this.message);
        hash = 53 * hash + Objects.hashCode(this.error);
        hash = 53 * hash + Objects.hashCode(this.detalhe);
        hash = 53 * hash + Objects.hashCode(this.path);
        return hash;
    }
    
}
