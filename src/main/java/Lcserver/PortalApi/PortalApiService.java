/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.PortalApi;

import Lcserver.Exception.NotFoundException;
import Lcserver.Exception.OperacaoInvalidaException;
import Lcserver.PortalApi.dto.input.ErrorPortalApiDtoInput;
import Lcserver.TelaPrincipal;
import Util.Funcoes;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jordan LC Sistemas
 */
@Service
public class PortalApiService {

    private final String URL_BASE = "http://portal-api.lcsistemas.com.br:4803/LcBalcaoApi";
    private final String PATH_CONSULTA_RECEITAWS = "/consutaCnpjReceitaWs";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> requestPortalApiConsutaCnpjReceitaWs(String cnpjEmpresa, String cnpjConsulta) {
        cnpjEmpresa = Funcoes.removeMascaras(cnpjEmpresa);
        cnpjConsulta = Funcoes.removeMascaras(cnpjConsulta);
        String url = URL_BASE + PATH_CONSULTA_RECEITAWS + "/" + cnpjEmpresa + "/" + cnpjConsulta;
        String msgErro = "";
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(cnpjEmpresa), String.class);
        } catch (Exception e) {
            TelaPrincipal.TelaPrincipal.setErro("requestPortalApiConsutaCnpjReceitaWs", e);
            throw new OperacaoInvalidaException("Ops... Não conseguimos buscar os dados deste CNPJ no site da receita, por favor informe este cliente manualmente!");
        }
        if (!response.getStatusCode().is2xxSuccessful()) {
            try {
                msgErro = new Gson().fromJson(response.getBody(), ErrorPortalApiDtoInput.class).getMessage();
            } catch (Exception e) {
                TelaPrincipal.TelaPrincipal.setErro("requestPortalApiConsutaCnpjReceitaWs", e);
                throw new OperacaoInvalidaException("Ops... Não conseguimos buscar os dados deste CNPJ no site da receita, por favor informe este cliente manualmente!");
            }
            throw new OperacaoInvalidaException(msgErro);

        } else {
            return response;//Sucesso
        }
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.errorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
                return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR || httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR);
            }

            @Override
            public void handleError(ClientHttpResponse httpResponse) throws IOException {
                if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
                    // handle SERVER_ERROR
                } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                    // handle CLIENT_ERROR
                    if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new NotFoundException("Recurso não encontrado");
                    }
                }
            }
        }).build();
    }

    private HttpEntity<String> createHttpEntity(String cnpj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Empresa", gerarHash(cnpj));
        return new HttpEntity<String>("parameters", headers);
    }

    private String gerarHash(String cnpj) {
        int hash = 7;
        hash = 143 * hash + Objects.hashCode(cnpj + "vc é dono de sua realidade!");
        return String.valueOf(hash);
    }

}
