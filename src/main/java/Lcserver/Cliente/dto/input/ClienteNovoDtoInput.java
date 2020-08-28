/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente.dto.input;

import Lcserver.Cliente.Cliente;
import Lcserver.Cliente.ClienteRepository;
import Lcserver.EstadoCidade.Cidades;
import Lcserver.EstadoCidade.Estados;
import Lcserver.Exception.OperacaoInvalidaException;
import SessaoAberta.IndicadorIe;
import Lcserver.Permissao.Permissao;
import Lcserver.Usuario.Usuario;
import Lcserver.Usuario.UsuarioDao;
import Util.FuncoesCpfCnpj;
import Util.Funcoes;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 *
 * @author Jordan LC Sistemas
 */
public class ClienteNovoDtoInput {

    private Integer id;
    @Size(min = 11, max = 14, message = "Tamanho do campo CPF/CNPJ inválido!")
    @NotEmpty
    private String cpfCnpj;
    @Size(min = 1, max = 1, message = "Tamanho do campo tipo inválido")
    @NotEmpty(message = "Tipo não pode ser vazio")
    private String tipo; //recebe J ou F
    @Min(value = 1, message = "Informe um usuario com ID maior que um!")
    @NotNull(message = "ID do usuário não pode ser vazio!")
    private Integer idUsuario;
    private String ie;
    private Integer ieIdicador;
    private String im;
    private String rg;
    @Nullable
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date emissao;
    private String orgao;
    @Size(min = 1, message = "Nome do cliente não pode ser vazio!")
    private String nome;
    private String apelidoAdi;
    private String razaoSocial;
    private String endereco;
    private String numero;
    private String referencia;
    private String cep;
    private String bairro;
    @Size(min = 0, message = "Quantidade de caracteres do email inválido")
    private String email;
    @Size(min = 0, max = 14, message = "Quantidade de caracteres do telefone inválido!")
    private String telefone;
    @Size(min = 0, max = 14, message = "Quantidade de caracteres do telefone 2 inválido!")
    private String telefone2;
    @Size(min = 0, max = 14, message = "Quantidade de caracteres do celular inválido!")
    private String celular;
    private Integer idPais;
    @NotNull(message = "Estado inválido, informe UF com dois caracteres!")
    private Integer idEstado;
    @NotNull(message = "Cidade inválida!")
    private Integer idCidade;
    private String ativo;

    public void isValid(UsuarioDao usuarioDao, ClienteRepository clienteDao) {
        String msg = "";
        if ("F".equals(tipo) && !FuncoesCpfCnpj.ValidaCPF(cpfCnpj)) {
            msg += "CPF inválido!\n";
        }
        if ("J".equals(tipo) && !FuncoesCpfCnpj.ValidaCNPJ(cpfCnpj)) {
            msg += "CNPJ inválido!\n";
        }
        if (!"J".equals(tipo) && !"F".equals(tipo)) {
            msg += "Tipo de cliente com valor inválido!\n";
        }
        if (StringUtils.isEmpty(tipo)) {
            msg += "Tipo de cliente inválido!";
        }
        if (StringUtils.isEmpty(nome)) {
            msg += "Nome inválido!";
        }
        if (!StringUtils.isEmpty(this.telefone) && !StringUtils.isEmpty(this.telefone2) && !StringUtils.isEmpty(this.celular)) {
            msg += "Telefone não informado!\n";
        }
        Optional<Cliente> cliente = clienteDao.getClienteByCpfCnpj(Funcoes.removeMascaras(cpfCnpj));
       
        if ((id == null || id == 0) && cliente.isPresent()) {
            msg += "Cliente já cadastrado no sistema!\n";
        }
        Usuario usuario = usuarioDao.getUsuarioAtivo(idUsuario);
        if (usuario == null) {
            msg += "Usuário não encontrado na base de dados!\n";
            System.out.println(msg);
        } else if (!usuario.getPermissoes().contains(Permissao.CADASTRO_CLIENTE.name())) {
            msg += "Usuário não tem permissão para essa operação!\n";
        }

        if ("F".equals(tipo)) {
            if (ieIdicador != IndicadorIe.CONTRIBUINTE_NAO.getCodigo()) {
                msg += "Indicador da Inscrição estadual inválido para este tipo de cliente!\n";
            }
        }

        if ("J".equals(tipo)) {
            if (StringUtils.isEmpty(razaoSocial)) {
                msg += "Razão social inválido!";
            }
//            if (StringUtils.isEmpty(ie)) {
//                msg += "Inscrição Estadual inválida!\n";
//            }
            if (ieIdicador == IndicadorIe.CONTRIBUINTE_ISENTO.getCodigo() && ieIdicador == IndicadorIe.CONTRIBUINTE_ICMS.getCodigo() && ieIdicador == IndicadorIe.CONTRIBUINTE_NAO.getCodigo()) {
                msg += "Indicador da Inscrição estadual inválido!\n";
            }
        }
        if (!msg.equals("")) {
           throw new OperacaoInvalidaException(msg);
        }
    }

    public Cliente buildCliente(ClienteRepository clienteDao) {
        Cliente c = new Cliente();
        if (id != null && id > 2) {
            c = clienteDao.getOne(id);
        }
        c.setIe(ie);
        c.setIeIndicador(ieIdicador.toString());
        c.setIm(im == null ? "" : im);
        c.setRg(rg == null ? "" : rg);
        c.setEmissao(emissao == null ? null : emissao);
        c.setOrgao(orgao == null ? "" : orgao);
        c.setNome(nome);
        c.setRazaoSocial(razaoSocial == null ? "" : razaoSocial);
        c.setApelidoAdi(apelidoAdi == null ? "" : apelidoAdi);
        c.setEndereco(endereco);
        c.setNumero(numero);
        c.setReferencia(referencia == null ? "" : referencia);
        c.setCep(cep);
        c.setBairro(bairro);
        c.setEmailAdi(email);
        c.setTelefone(telefone == null ? "" : telefone.replace(" ", ""));
        c.setTelComercial(telefone2 == null ? "" : telefone2.replace(" ", ""));
        c.setFax(celular == null ? "" : celular.replace(" ", ""));
        c.setIdPais(idPais == null ? 34 : idPais);
        c.setEstado(new Estados(idEstado == null ? 14 : idEstado));
        c.setCidade(new Cidades(idCidade == null ? 217 : idCidade));
        c.setReferencia(referencia);
        c.setAtivo(ativo);
        if (id == null || id == 0) {
            c.setCpfCnpj(cpfCnpj);
            c.setTipo(tipo);
            c.setLimiteCredito(0.0);
            c.setObs("");
            c.setEndereco2("");
            c.setNumero2("");
            c.setCep2("");
            c.setBairro2("");
            c.setReferencia2("");
            c.setIdEstado2(14);
            c.setIdCidade2(217);
            c.setIdEmpresa(1);
            c.setPaiAdi("");
            c.setMaeAdi("");
            c.setNascimentoAdi(null);
            c.setSexoAdi("");
            c.setEstcivilAdi("");
            c.setIdCidadesAdi(0);
            c.setIdEstadosAdi(0);
            c.setEmpresa("");
            c.setFoneEmp("");
            c.setEnderecoEmp("");
            c.setNumeroEmp("");
            c.setCepEmp("");
            c.setBairroEmp("");
            c.setCargoEmp("");
            c.setRendaEmp(0.0);
            c.setAdmissaoEmp(null);
            c.setIdCidadesEmp(0);
            c.setIdEstadosEmp(0);
            c.setConjuje("");
            c.setCpfConj("");
            c.setRgConj("");
            c.setNascimentoConj(null);
            c.setEmpresaConj("");
            c.setFoneConj("");
            c.setEnderecoConj("");
            c.setNumeroConj("");
            c.setCepConj("");
            c.setBairroConj("");
            c.setCargoConj("");
            c.setRendaConj(0.0);
            c.setAdmissaoConj(null);
            c.setIdCidadesConj(0);
            c.setIdEstadosConj(0);
            c.setReferencias("");
            c.setComercial1("");
            c.setComercial2("");
            c.setComercial3("");
            c.setBancaria1("");
            c.setBancaria2("");
            c.setDataCadastro(clienteDao.getDataServer());
            c.setFoto("");
            c.setPoupanca(0.0);
            c.setPodeAprazo("S");
            c.setPodeCartacobranca("S");
            c.setNumeroContrato(null);
            c.setNumeroCartao("");
            c.setTabelaPreco("NORMAL");
            c.setIdVendedor(0);
            c.setFiliacaoFonemae("(  )    -    ");
            c.setFiliacaoFonepai("(  )    -    ");
            c.setFiliacaoEndereco("");
            c.setFiliacaoReferencia("");
            c.setFiliacaoNumero("");
            c.setFiliacaoBairro("");
            c.setFiliacaoCep("");
            c.setFiliacaoIdcidade(0);
            c.setFiliacaoIdestado(0);
            c.setAvalistaNome("");
            c.setAvalistaCpf("   .   .   -  ");
            c.setAvalistaRg("");
            c.setAvalistaNascimento(null);
            c.setAvalistaFone("(  )    -    ");
            c.setAvalistaEndereco("");
            c.setAvalistaNumero("");
            c.setAvalistaCep("");
            c.setAvalistaBairro("");
            c.setAvalistaEmpresa("");
            c.setAvalistaCargo("");
            c.setAvalistaRenda(0.0);
            c.setAvalistaAdmissao(null);
            c.setAvalistaIdcidade(0);
            c.setAvalistaIdestado(0);
        }

        return c;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public Integer getIeIdicador() {
        return ieIdicador;
    }

    public void setIeIdicador(Integer ieIdicador) {
        this.ieIdicador = ieIdicador;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidoAdi() {
        return apelidoAdi;
    }

    public void setApelidoAdi(String apelidoAdi) {
        this.apelidoAdi = apelidoAdi;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

}
