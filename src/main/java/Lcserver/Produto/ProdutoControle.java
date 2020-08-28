/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import Lcserver.Exception.NotFoundException;
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

    public List<Produto> getProdutoIdCodEan(String cod) {
        return produtoDao.getProdutoByIdByCodBarrasByCod(cod);
    }

    public List<Produto> getProdutoIdCodEan2(String cod) {
        return produtoDao.getProdutoByIdByCodBarrasByCod(cod);
    }

    public List<Produto> getProdutoCodEanNomeDescRefFab(String nome, String descricao, String cod, String fabricante, String referencia) {
        return produtoDao.getListaProdutoAtivo(nome + "%", descricao + "%", cod + "%", fabricante + "%", referencia + "%");
    }

    public Produto findById(int id) {
        return produtoDao.findById(id).get();
    }

    public List<Produto> findAll() {
        return produtoDao.findAll();
    }
    
    public boolean verificaPromocao(int id){
        return produtoDao.verificaPromocao(id)>=0;
    }

}
