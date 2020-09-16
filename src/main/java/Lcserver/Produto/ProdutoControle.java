/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import Lcserver.EstoqueSaldo.EstoqueSaldo;
import Lcserver.EstoqueSaldo.EstoqueSaldoRepository;
import Lcserver.Exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class ProdutoControle {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private EstoqueSaldoRepository estoqueSaldoRepository;

    public List<Produto> getProdutoIdCodEan(String cod, Integer idEmpresa) {
        List<Produto> lista = new ArrayList<>();
        List<EstoqueSaldo> estoques = new ArrayList<>();
        List<Produto> produtos = produtoDao.getProdutoByIdByCodBarrasByCod(cod, idEmpresa);

        for (Produto p : produtos) {
            double saldo = 0;
            estoques = estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, p.getId());
            for (EstoqueSaldo es : estoques) {
                saldo += es.getQuantidade();
            }
            p.setEstoque(saldo);
            lista.add(p);
        }

        return lista;
    }

    public List<Produto> getProdutoIdCodEan2(String cod, Integer idEmpresa) {
        return produtoDao.getProdutoByIdByCodBarrasByCod(cod, idEmpresa);
    }

    public List<Produto> getProdutoCodEanNomeDescRefFab(String nome, String descricao, String cod, String fabricante, String referencia, Integer idEmpresa) {
        List<Produto> lista = new ArrayList<>();
        List<EstoqueSaldo> estoques = new ArrayList<>();
        List<Produto> produtos = produtoDao.getListaProdutoAtivo(nome + "%", descricao + "%", cod + "%", fabricante + "%", referencia + "%", idEmpresa);
        for (Produto p : produtos) {
            double saldo = 0;
            estoques = estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, p.getId());
            for (EstoqueSaldo es : estoques) {
                saldo += es.getQuantidade();
            }
            p.setEstoque(saldo);
            lista.add(p);
        }

//        return produtoDao.getListaProdutoAtivo(nome + "%", descricao + "%", cod + "%", fabricante + "%", referencia + "%", idEmpresa);
        return lista;
    }

    public Produto findById(int id, Integer idEmpresa) {
        List<EstoqueSaldo> estoques = new ArrayList<>();
        Produto p = produtoDao.findById(id).get();
        estoques = estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, p.getId());
        double saldo = 0;
        for (EstoqueSaldo es : estoques) {
            saldo += es.getQuantidade();
        }
        p.setEstoque(saldo);
        return p;
    }

    public List<Produto> findAll(Integer idEmpresa) {
        List<Produto> lista = new ArrayList<>();
        List<EstoqueSaldo> estoques = new ArrayList<>();
        List<Produto> produtos = produtoDao.findAll();

        for (Produto p : produtos) {
            double saldo = 0;
            estoques = estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, p.getId());
            for (EstoqueSaldo es : estoques) {
                saldo += es.getQuantidade();
            }
            p.setEstoque(saldo);
            lista.add(p);
        }

//        return produtoDao.getListaProdutoAtivo(nome + "%", descricao + "%", cod + "%", fabricante + "%", referencia + "%", idEmpresa);
        return lista;
    }

    public boolean verificaPromocao(int id) {
        return produtoDao.verificaPromocao(id) >= 0;
    }

}
