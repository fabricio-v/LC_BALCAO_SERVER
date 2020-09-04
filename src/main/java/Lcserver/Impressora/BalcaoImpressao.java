/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Impressora;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoDao;
import Lcserver.Balcao.BalcaoDet;
import Lcserver.Cliente.Cliente;
import Lcserver.Empresa.Empresa;
import Lcserver.Produto.Produto;
import SessaoAberta.SessaoAberta;

import Util.Funcoes;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class BalcaoImpressao {

    @Autowired
    private BalcaoDao balcaoDao;

    public BalcaoImpressao() {
    }

    public void imprimir(Integer idEmpresa, Integer codBalcao, Impressora i) throws Exception {
        Balcao balcao = balcaoDao.getBalcaoByIdByIdEmpresa(codBalcao, idEmpresa);
        if (i.getTipo().equals("BOBINA")) {
            if (i.getModelo().equals("Daruma DR700L") | i.getModelo().equals("Elgin 9") || i.getModelo().equals("Epson TM-20")) {
                impressao48(balcao, i);
            } else if (i.getModelo().equals("BEMA MP-4200 TH") || i.getModelo().equals("BEMA MP-2500 TH")) {
                impressaoBema25004200(balcao, i);
            } else if (i.getModelo().equals("Sweda SI-300S")) {
                impressao42(balcao, i);
            }
        }
    }

    public void imprimirPaginaTeste(Impressora i) throws Exception {
        if (i.getTipo().equals("BOBINA")) {
            ImpressoraLC print = new ImpressoraLC(i.getPorta());
            print.imprimir(txtPaginaTeste(i));
            print.acionaGuilhotina();
        }
    }

    private void impressaoBema25004200(Balcao balcao, Impressora i) throws Exception {
        String cupom = ""; //50 caracter
        Cliente c = balcao.getCliente();
        Empresa e = balcao.getEmpresa();
        ImpressoraLC print = new ImpressoraLC(i.getPorta());
        cupom += "--------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("O R C A M E N T O", 50) + "\n";
        cupom += "--------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto(e.getFantasia(), 50) + "\n";
        cupom += Funcoes.centralizarTexto(e.getEndereco() + ", " + e.getNumero() + " - " + e.getBairro(), 50) + "\n";
        cupom += Funcoes.centralizarTexto(balcao.getEmpresa().getCidade().getNome() + " - " + e.getEstado().getNome(), 50) + "\n";
        cupom += Funcoes.centralizarTexto(e.getTelefone(), 50) + "\n";
        cupom += Funcoes.centralizarTexto("*** SEM VALOR FISCAL ***", 50) + "\n";
        cupom += Funcoes.algLeft("OR:" + Funcoes.formatoCodigo9(balcao.getId()), 12);
        cupom += Funcoes.algLeft("DH:" + Funcoes.formataDataHoraBr(balcao.getDataHora()), 22);
        cupom += Funcoes.algLeft("VD:" + balcao.getUsuario().getLogin(), 13) + "\n";
        if (balcao.getCliente().getId() != 1) {
            cupom += "--------------------------------------------------\n";
            cupom += Funcoes.centralizarTexto("C L I E N T E", 50) + "\n";
            cupom += "--------------------------------------------------\n";
            cupom += Funcoes.algLeft("CODIGO:  " + Funcoes.formatoCodigo6(c.getId()), 49) + "\n";
            cupom += Funcoes.algLeft("CPF/CNPJ:" + Funcoes.formataCpf_Cnpj(c.getCpfCnpj()), 49) + "\n";
            cupom += Funcoes.algLeft("CLIENTE: " + c.getNome(), 49) + "\n";
            cupom += Funcoes.algLeft("ENDERECO:" + c.getEndereco(), 49) + "\n";
            cupom += Funcoes.algLeft("BAIRRO:  " + c.getBairro(), 49) + "\n";
            cupom += Funcoes.algLeft("NUMERO:  " + c.getNumero(), 49) + "\n";
            cupom += Funcoes.algLeft("CIDADE:  " + c.getCidade().getNome() + " - " + c.getEstado().getUf(), 49) + "\n";
            cupom += Funcoes.algLeft("CEP:     " + c.getCep(), 49) + "\n";
            cupom += Funcoes.algLeft("TELEFONE:" + balcao.getCliente().getTelefone(), 49) + "\n";
            cupom += Funcoes.algLeft("REF:     " + balcao.getCliente().getReferencia(), 49) + "\n";
        }
        cupom += "--------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("P R O D U T O S", 50) + "\n";
        cupom += "--------------------------------------------------\n";
        cupom += "ITEM CODIGO       DESCRICAO                       \n";
        cupom += "QTD         PRECO         DESC               TOTAL\n";
        cupom += "--------------------------------------------------\n";
        for (BalcaoDet det : balcao.getBalcaoDet()) {
            Produto produto = det.getProduto();
            cupom += Funcoes.algLeft(Funcoes.formatoCodigo3(Integer.parseInt(det.getItem())), 3);
            cupom += Funcoes.algLeft(produto.getCodigoBarras().length() == 0 ? produto.getId() + "" : produto.getCodigoBarras(), 13);
            cupom += Funcoes.algLeft(produto.getNome(), 31) + "\n";
            if (SessaoAberta.getConfig().getBalcaoCompImprimirFabricante().equals("SIM")) {
                cupom += Funcoes.algLeft("FAB:" + produto.getFabricante().getNome(), 49) + "\n";
            }
            cupom += Funcoes.algLeft(Funcoes.formatoPeso(det.getQuantidade()), 11);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getPreco()), 13);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getValorDesconto()), 5);
            cupom += Funcoes.algRight(Funcoes.formatoReal(det.getSubTotal()), 17) + "\n";
        }
        cupom += "--------------------------------------------------\n";
        cupom += Funcoes.algRight("TOTAL:        ", 21) + Funcoes.algRight(Funcoes.formatoReal(balcao.getTotal()), 21) + "\n";
        if (balcao.getValorDesconto() > 0) {
            cupom += Funcoes.algRight("DESCONTO:     ", 21) + Funcoes.algRight(Funcoes.formatoReal(balcao.getValorDesconto()), 21) + "\n";
        }
        if (balcao.getArredondamento() > 0) {
            cupom += Funcoes.algRight("ARREDON.:     ", 21) + Funcoes.algRight(Funcoes.formatoReal(balcao.getArredondamento()), 21) + "\n";
        }
        if ((balcao.getArredondamento() + balcao.getValorDesconto()) > 0) {
            cupom += Funcoes.algRight("SUBTOTAL:     ", 21) + Funcoes.algRight(Funcoes.formatoReal(balcao.getSubTotal()), 21) + "\n";
        }
        cupom += "--------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("www.lcsistemas.com.br " + SessaoAberta.getVersao(), 50) + "\n";
        System.out.println(cupom);
        print.imprimirBema25004200(cupom);
        print.acionaGuilhotina();
    }

    private void impressao48(Balcao balcao, Impressora i) throws Exception {
        String cupom = ""; //48 caracter
        Cliente c = balcao.getCliente();
        Empresa e = balcao.getEmpresa();
        ImpressoraLC print = new ImpressoraLC(i.getPorta());
        cupom += "------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("O R C A M E N T O", 47) + "\n";
        cupom += "------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto(e.getFantasia(), 47) + "\n";
        cupom += Funcoes.centralizarTexto(e.getEndereco() + ", " + e.getNumero() + " - " + e.getBairro(), 47) + "\n";
        cupom += Funcoes.centralizarTexto(balcao.getEmpresa().getCidade().getNome() + " - " + e.getEstado().getNome(), 47) + "\n";
        cupom += Funcoes.centralizarTexto(e.getTelefone(), 47) + "\n";
        cupom += Funcoes.centralizarTexto("*** SEM VALOR FISCAL ***", 47) + "\n";
        cupom += Funcoes.algLeft("OR:" + Funcoes.formatoCodigo9(balcao.getId()), 12);
        cupom += Funcoes.algLeft("DH:" + Funcoes.formataDataHoraBr(balcao.getDataHora()), 22);
        cupom += Funcoes.algLeft("VD:" + balcao.getUsuario().getLogin(), 11) + "\n";
        if (balcao.getCliente().getId() != 1) {
            cupom += "------------------------------------------------\n";
            cupom += Funcoes.centralizarTexto("C L I E N T E", 48) + "\n";
            cupom += "------------------------------------------------\n";
            cupom += Funcoes.algLeft("CODIGO:  " + Funcoes.formatoCodigo6(c.getId()), 47) + "\n";
            cupom += Funcoes.algLeft("CPF/CNPJ:" + Funcoes.formataCpf_Cnpj(c.getCpfCnpj()), 47) + "\n";
            cupom += Funcoes.algLeft("CLIENTE: " + c.getNome(), 47) + "\n";
            cupom += Funcoes.algLeft("ENDERECO:" + c.getEndereco(), 47) + "\n";
            cupom += Funcoes.algLeft("BAIRRO:  " + c.getBairro(), 47) + "\n";
            cupom += Funcoes.algLeft("NUMERO:  " + c.getNumero(), 47) + "\n";
            cupom += Funcoes.algLeft("CIDADE:  " + c.getCidade().getNome() + " - " + c.getEstado().getUf(), 47) + "\n";
            cupom += Funcoes.algLeft("CEP:     " + c.getCep(), 47) + "\n";
            cupom += Funcoes.algLeft("TELEFONE:" + balcao.getCliente().getTelefone(), 47) + "\n";
            cupom += Funcoes.algLeft("REF:     " + balcao.getCliente().getReferencia(), 47) + "\n";
        }
        cupom += "------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("P R O D U T O S", 48) + "\n";
        cupom += "------------------------------------------------\n";
        cupom += "ITEM CODIGO       DESCRICAO                     \n";
        cupom += "QTD         PRECO         DESC             TOTAL\n";
        cupom += "------------------------------------------------\n";
        for (BalcaoDet det : balcao.getBalcaoDet()) {
            Produto produto = det.getProduto();
            cupom += Funcoes.algLeft(Funcoes.formatoCodigo3(Integer.parseInt(det.getItem())), 3);
            cupom += Funcoes.algLeft(produto.getCodigoBarras().length() == 0 ? produto.getId() + "" : produto.getCodigoBarras(), 13);
            cupom += Funcoes.algLeft(produto.getNome(), 29) + "\n";
            if (SessaoAberta.getConfig().getBalcaoCompImprimirFabricante().equals("SIM")) {
                cupom += Funcoes.algLeft("FAB:" + produto.getFabricante().getNome(), 47) + "\n";
            }
            cupom += Funcoes.algLeft(Funcoes.formatoPeso(det.getQuantidade()), 11);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getPreco()), 13);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getValorDesconto()), 5);
            cupom += Funcoes.algRight(Funcoes.formatoReal(det.getSubTotal()), 15) + "\n";
        }
        cupom += "------------------------------------------------\n";
        cupom += Funcoes.algRight("TOTAL:        ", 20) + Funcoes.algRight(Funcoes.formatoReal(balcao.getTotal()), 20) + "\n";
        if (balcao.getValorDesconto() > 0) {
            cupom += Funcoes.algRight("DESCONTO:     ", 20) + Funcoes.algRight(Funcoes.formatoReal(balcao.getValorDesconto()), 20) + "\n";
        }
        if (balcao.getArredondamento() > 0) {
            cupom += Funcoes.algRight("ARREDON.:     ", 20) + Funcoes.algRight(Funcoes.formatoReal(balcao.getArredondamento()), 20) + "\n";
        }
        if ((balcao.getArredondamento() + balcao.getValorDesconto()) > 0) {
            cupom += Funcoes.algRight("SUBTOTAL:     ", 20) + Funcoes.algRight(Funcoes.formatoReal(balcao.getSubTotal()), 20) + "\n";
        }
        cupom += "------------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("www.lcsistemas.com.br " + SessaoAberta.getVersao(), 48) + "\n";
        System.out.println(cupom);
        print.imprimir(cupom);
        print.acionaGuilhotina();
    }

    private void impressao42(Balcao balcao, Impressora i) throws Exception {
        String cupom = ""; //48 caracter
        Cliente c = balcao.getCliente();
        Empresa e = balcao.getEmpresa();
        ImpressoraLC print = new ImpressoraLC(i.getPorta());
        cupom += "------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("O R C A M E N T O", 42) + "\n";
        cupom += "------------------------------------------\n";
        cupom += Funcoes.centralizarTexto(e.getFantasia(), 41) + "\n";
        cupom += Funcoes.centralizarTexto(e.getEndereco() + ", " + e.getNumero() + " - " + e.getBairro(), 41) + "\n";
        cupom += Funcoes.centralizarTexto(balcao.getEmpresa().getCidade().getNome() + " - " + e.getEstado().getNome(), 41) + "\n";
        cupom += Funcoes.centralizarTexto(e.getTelefone(), 41) + "\n";
        cupom += Funcoes.centralizarTexto("*** SEM VALOR FISCAL ***", 41) + "\n";
        cupom += Funcoes.centralizarTexto("OR:" + Funcoes.formatoCodigo9(balcao.getId()) + " DH:" + Funcoes.formataDataHoraBr(balcao.getDataHora()), 42) + "\n";
        cupom += Funcoes.algLeft("   VD:" + balcao.getUsuario().getLogin(), 31) + "LJ:" + Funcoes.formatoCodigo3(e.getId()) + "\n";
        if (balcao.getCliente().getId() != 1) {
            cupom += "------------------------------------------\n";
            cupom += Funcoes.centralizarTexto("C L I E N T E", 41) + "\n";
            cupom += "------------------------------------------\n";
            cupom += Funcoes.algLeft("CODIGO:  " + Funcoes.formatoCodigo6(c.getId()), 41) + "\n";
            cupom += Funcoes.algLeft("CPF/CNPJ:" + Funcoes.formataCpf_Cnpj(c.getCpfCnpj()), 41) + "\n";
            cupom += Funcoes.algLeft("CLIENTE: " + c.getNome(), 41) + "\n";
            cupom += Funcoes.algLeft("ENDERECO:" + c.getEndereco(), 41) + "\n";
            cupom += Funcoes.algLeft("BAIRRO:  " + c.getBairro(), 41) + "\n";
            cupom += Funcoes.algLeft("NUMERO:  " + c.getNumero(), 41) + "\n";
            cupom += Funcoes.algLeft("CIDADE:  " + c.getCidade().getNome() + " - " + c.getEstado().getUf(), 41) + "\n";
            cupom += Funcoes.algLeft("CEP:     " + c.getCep(), 41) + "\n";
            cupom += Funcoes.algLeft("TELEFONE:" + balcao.getCliente().getTelefone(), 41) + "\n";
            cupom += Funcoes.algLeft("REF:     " + balcao.getCliente().getReferencia(), 41) + "\n";
        }
        cupom += "------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("P R O D U T O S", 42) + "\n";
        cupom += "------------------------------------------\n";
        cupom += "ITEM CODIGO       DESCRICAO               \n";
        cupom += "QTD         PRECO         DESC       TOTAL\n";
        cupom += "------------------------------------------\n";
        for (BalcaoDet det : balcao.getBalcaoDet()) {
            Produto produto = det.getProduto();
            cupom += Funcoes.algLeft(Funcoes.formatoCodigo3(Integer.parseInt(det.getItem())), 3);
            cupom += Funcoes.algLeft(produto.getCodigoBarras().length() == 0 ? produto.getId() + "" : produto.getCodigoBarras(), 13);
            cupom += Funcoes.algLeft(produto.getNome(), 23) + "\n";
            if (SessaoAberta.getConfig().getBalcaoCompImprimirFabricante().equals("SIM")) {
                cupom += Funcoes.algLeft("FAB:" + produto.getFabricante().getNome(), 41) + "\n";
            }
            cupom += Funcoes.algLeft(Funcoes.formatoPeso(det.getQuantidade()), 11);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getPreco()), 13);
            cupom += Funcoes.algLeft(Funcoes.formatoReal(det.getValorDesconto()), 5);
            cupom += Funcoes.algRight(Funcoes.formatoReal(det.getSubTotal()), 9) + "\n";
        }
        cupom += "------------------------------------------\n";
        cupom += Funcoes.algRight("TOTAL:        ", 18) + Funcoes.algRight(Funcoes.formatoReal(balcao.getTotal()), 18) + "\n";
        if (balcao.getValorDesconto() > 0) {
            cupom += Funcoes.algRight("DESCONTO:     ", 17) + Funcoes.algRight(Funcoes.formatoReal(balcao.getValorDesconto()), 15) + "\n";
        }
        if (balcao.getArredondamento() > 0) {
            cupom += Funcoes.algRight("ARREDON.:     ", 17) + Funcoes.algRight(Funcoes.formatoReal(balcao.getArredondamento()), 17) + "\n";
        }
        if ((balcao.getArredondamento() + balcao.getValorDesconto()) > 0) {
            cupom += Funcoes.algRight("SUBTOTAL:     ", 17) + Funcoes.algRight(Funcoes.formatoReal(balcao.getSubTotal()), 17) + "\n";
        }
        cupom += "------------------------------------------\n";
        cupom += Funcoes.centralizarTexto("www.lcsistemas.com.br " + SessaoAberta.getVersao(), 42) + "\n";
        System.out.println(cupom);
        print.imprimir(cupom);
        print.acionaGuilhotina();
    }

    private String txtPaginaTeste(Impressora impressora) {
        String msg = "";
        msg += Funcoes.centralizarTexto("Impressao Realizada com Sucesso!", 48) + "\n";
        msg += "Impressora Pronta para uso!" + "\n";
        msg += "Tipo:  " + impressora.getTipo() + "\n";
        msg += "Modelo:" + impressora.getModelo() + "\n";
        msg += "Porta: " + impressora.getPorta() + "\n\n";
        msg += Funcoes.centralizarTexto("LC BALCAO - " + SessaoAberta.getVersao(), 48) + "\n";
        return msg;
    }

}
