/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.PortalApi;

import Lcserver.Cliente.Cliente;
import Lcserver.Cliente.ClienteRepository;
import Lcserver.Empresa.EmpresaRepository;
import Lcserver.EstadoCidade.EstadosRepository;
import Lcserver.Exception.NotFoundException;
import Lcserver.PortalApi.dto.input.EmpresaBalcaoApiDtoInput;
import Lcserver.TelaPrincipal;
import Util.FuncoesCpfCnpj;
import Util.FuncoesFormataTexto;
import com.google.gson.Gson;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jordan LC Sistemas
 */
@RestController
@RequestMapping("/LcBalcaoApi")
public class PortalApiController {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PortalApiService portalApiService;
    @Autowired
    private EstadosRepository estadosRepository;

    @ResponseBody
    @GetMapping(value = "/consutaCnpjReceitaWs/{cpfCnpjConsulta}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> consultaEmpresaReceitaWs(@PathVariable(required = true) String cpfCnpjConsulta, @RequestHeader(required = true) String imei) {
        TelaPrincipal.TelaPrincipal.setLogAndValidaImei("/consutaCnpjReceita", imei);
        Optional<Cliente> clienteByCpfCnpj = clienteRepository.getClienteByCpfCnpj(cpfCnpjConsulta);
        if (clienteRepository.getClienteByCpfCnpj(cpfCnpjConsulta).isPresent()) {
            return ResponseEntity.ok(new EmpresaBalcaoApiDtoInput(clienteByCpfCnpj.get()));
        } else if (FuncoesCpfCnpj.ValidaCNPJ(cpfCnpjConsulta)) {
            ResponseEntity<String> response = portalApiService.requestPortalApiConsutaCnpjReceitaWs(empresaRepository.findAll().get(0).getCnpj(), cpfCnpjConsulta);
            if (response.getStatusCode().is2xxSuccessful()) {
                EmpresaBalcaoApiDtoInput empresa = new Gson().fromJson(response.getBody(), EmpresaBalcaoApiDtoInput.class);
                empresa.setEstado(estadosRepository.getNomeEstadoByUf(empresa.getEstado()));
                return ResponseEntity.ok(empresa);
            }
            return portalApiService.requestPortalApiConsutaCnpjReceitaWs(empresaRepository.findAll().get(0).getCnpj(), cpfCnpjConsulta);
        } else {
            throw new NotFoundException("CPF " + FuncoesFormataTexto.formataCpf_Cnpj(cpfCnpjConsulta) + " não encontrado na base de dados!");
        }
    }

}
