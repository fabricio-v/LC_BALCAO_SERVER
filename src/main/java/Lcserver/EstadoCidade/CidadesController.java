/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstadoCidade;

import Lcserver.Exception.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jordan LC Sistemas
 */
@RestController
@RequestMapping(value = "/cidades", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class CidadesController {

    @Autowired
    private CidadesRepository cidadesRepository;

    @ResponseBody
    @RequestMapping(value = "/{idUf}", method = RequestMethod.GET)
    public List<Cidades> getCidades(@PathVariable String idUf) {
        return cidadesRepository.getCidadesByIdEstado(idUf).orElseThrow(() -> new NotFoundException("Não foi encontrado cidades para este estado!"));
    }

}
