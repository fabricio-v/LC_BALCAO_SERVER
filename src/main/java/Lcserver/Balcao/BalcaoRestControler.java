/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import Lcserver.Balcao.dto.Input.BalcaoNewDtoInput;
import Lcserver.Balcao.dto.output.BalcaoDtoOutput;
import Lcserver.Balcao.dto.output.BalcaoListDtoOutput;
import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Cliente.ClienteService;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Exception.OperacaoInvalidaException;
import Lcserver.TelaPrincipal;
import Lcserver.Usuario.Usuario;
import Lcserver.Usuario.UsuarioControle;
import Util.Funcoes;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
@RequestMapping(value = "",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BalcaoRestControler {

    private static final Logger logger = LoggerFactory.getLogger(BalcaoRestControler.class);

    @Autowired
    private BalcaoControle balcaoControle;
    @Autowired
    private BalcaoDao balcaoDao;
    @Autowired
    private BalcaoMobileControle mobileControle;
    @Autowired
    private UsuarioControle usuarioControle;
    @Autowired
    private ClienteService clienteControle;

    @GetMapping("/empresas/{idEmpresa}/balcoes/clientes/{nome}")//?cliente=sdpmfkmsp%sdpkfsd
    @ResponseStatus(HttpStatus.OK)
    public List<BalcaoListDtoOutput> pesquisaSimplesBalcaoCodCliente(@PathVariable String nome, @RequestHeader HttpHeaders headers) {
        Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        TelaPrincipal.TelaPrincipal.setLog("/balcoes/clientes/" + nome, usuario);
        usuario = usuarioControle.getUsuarioAtivoValida(usuario);
        Integer id_usuario = usuario.getPermissoes().contains("BALCAO_PESQUISAR_ATENDIMENTOS_TODOS_USUARIOS") ? 0 : usuario.getId();
        List<Balcao> list = balcaoControle.getBalcaoListByIdByClienteNomeByCpfCnpjByRazaoAndPF(nome, id_usuario);
        return list.stream().map(BalcaoListDtoOutput::new).collect(Collectors.toList());
    }

    @GetMapping("/empresas/{idEmpresa}/balcoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BalcaoDtoOutput getBalcaoById(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
        Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        TelaPrincipal.TelaPrincipal.setLog("balcoes/ " + id, usuario);
        usuario = usuarioControle.getUsuarioAtivoValida(usuario);
        BalcaoDtoOutput balcaoDtoOutput = new BalcaoDtoOutput(balcaoControle.getBalcao(id), clienteControle);
        return balcaoDtoOutput;
    }

    /**
     * @deprecated @param balcao
     * @param headers
     * @return
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvarBalcao(@RequestBody Balcao balcao, @RequestHeader HttpHeaders headers) {
        TelaPrincipal.TelaPrincipal.setLog("/salvarBalcao");
        Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        BalcaoMobile mobile = mobileControle.validaAndroid(headers.get("imei").get(0));
        TelaPrincipal.TelaPrincipal.atualizaTabela();
        usuario = usuarioControle.getUsuarioAtivoValida(usuario);
        mobile.validate();
        if (balcao.getStatus().equals("OC") && !usuario.getPermissoes().contains("BALCAO_SALVAR_ATENDIMENTO")) {
            throw new PermissaoInsuficienteException(usuario.getLogin() + ", você não tem permissão para esta operação!");
        }
        balcao = balcaoControle.inserir(balcao);
        return balcao.getId();
    }

    //@PostMapping( "/empresas/{idEmpresa}/balcoes/salvar" )
    @PostMapping( "/empresas/{idEmpresa}/balcoes" )
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional( isolation = Isolation.READ_COMMITTED,rollbackFor = {OperacaoInvalidaException.class, NullPointerException.class, IllegalArgumentException.class})
    public Integer salvarBalcao(@RequestBody BalcaoNewDtoInput balcaoNewDtoInput, @RequestHeader HttpHeaders headers) {
        TelaPrincipal.TelaPrincipal.setLog("/salvarBalcao");
        Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        BalcaoMobile mobile = mobileControle.validaAndroid(headers.get("imei").get(0));
        usuario = usuarioControle.getUsuarioAtivoValida(usuario);
        TelaPrincipal.TelaPrincipal.atualizaTabela();
        if (mobile.validate() && balcaoNewDtoInput.validate(usuario)) {
            Balcao balcao = balcaoControle.inserir(balcaoNewDtoInput.build(usuario, balcaoNewDtoInput, balcaoDao));
            return balcao.getId();
        }
        return null;
    }

//    @Deprecated
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Void delete(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
//        TelaPrincipal.TelaPrincipal.setLog("/deletar OC: " + id);
//        BalcaoMobile mobile = mobileControle.validaAndroid(headers.get("imei").get(0));
//        TelaPrincipal.TelaPrincipal.atualizaTabela();
//        //Usuario usuario = Funcoes.decoede_Base_64_usuario(headers.get("user").get(0));
//        if (mobile.getStatus().equals("INATIVO")) {
//            throw new PermissaoInsuficienteException();
//        }
////        if (balcao.getStatus().equals("OC") && !usuario.getPermissoes().contains("BALCAO_EXCLUIR")) {
////            throw new PermissaoInsuficienteException(usuario.getLogin() + ", você não tem permissão para esta operação!");
////        }
//        balcaoControle.delete(id);
//        return null;
//    }
    //@PostMapping("/deletar/{id}")
    @DeleteMapping("/balcoes/deletar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
        TelaPrincipal.TelaPrincipal.setLog("/deletar OC: " + id);
        BalcaoMobile mobile = mobileControle.validaAndroid(headers.get("imei").get(0));
        Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        usuario = usuarioControle.getUsuarioAtivoValida(usuario);
        TelaPrincipal.TelaPrincipal.atualizaTabela();
        //Usuario usuario = Funcoes.decode_Base_64_usuario(headers.get("user").get(0));
        if (mobile.getStatus().equals("INATIVO")) {
            throw new PermissaoInsuficienteException();
        }
        balcaoControle.delete(usuario, id);
        String value = "{\"value\":\"Orçamento excluido com sucesso\"}";
        return value;
    }

}
