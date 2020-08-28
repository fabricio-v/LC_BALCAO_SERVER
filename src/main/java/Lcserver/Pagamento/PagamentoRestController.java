/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Pagamento;

import Lcserver.Pagamento.dto.Output.PagamentoDtoOutput;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
@RequestMapping(value = "/pagamentos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PagamentoRestController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PagamentoDtoOutput> getListaPagamentos() {
        List<PagamentoDtoOutput> list = pagamentoRepository.getListaPagamentoReceber().stream().map(PagamentoDtoOutput::new).collect(Collectors.toList());
        return list;
    }

}
