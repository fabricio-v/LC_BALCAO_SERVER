/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import Lcserver.Exception.NotFoundException;
import Lcserver.Exception.OperacaoInvalidaException;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Usuario.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class BalcaoControle {

    @Autowired
    private BalcaoDao balcaoDao;
    @Autowired
    private BalcaoDetDao balcaoDetDao;
    @Autowired
    private BalcaoRecDao balcaoRecDao;

    @Transactional(rollbackFor = OperacaoInvalidaException.class)
    public Balcao inserir(Balcao balcao) {
        balcao.setIdUsuarioex(0);
        balcaoDetDao.deleteIdBalcao(balcao.getId());
        balcaoRecDao.deleteIdBalcao(balcao.getId());
        return balcaoDao.save(balcao);
    }

    public List<Balcao> getBalcaoListByIdByClienteNomeByCpfCnpjByRazaoAndPF(Integer idEmpresa, String nome) {
        List<Balcao> list = balcaoDao.getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(nome + "%", "%", idEmpresa, new PageRequest(0, 100));
        return list;
    }

    public List<Balcao> getBalcaoListByIdByClienteNomeByCpfCnpjByRazaoAndPF(Integer idEmpresa, String nome, int id_usuario) {
        List<Balcao> list;
        //if (id_usuario == 0) {
        list = balcaoDao.getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(nome + "%", (id_usuario == 0 ? "%" : String.valueOf(id_usuario)), idEmpresa, new PageRequest(0, 100));
        // } else {
        //      list = balcaoDao.getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(nome + "%", String.valueOf(id_usuario), new PageRequest(0, 100));
        // }
        if (list == null || list.isEmpty()) {
            throw new NotFoundException("Orçamento não encontrado na base de dados!");
        }
        return list;
    }

    public Balcao getBalcao(Integer idEmpresa, Integer id) {
        Balcao balcao = balcaoDao.getBalcaoByIdByIdEmpresa(id, idEmpresa);
//        Optional<Balcao> balcao = balcaoDao.getBalcaoByIdByIdEmpresa(id, idEmpresa);
//        balcao.orElseThrow(() -> new NotFoundException("Orçamento nãoencontrado na base de dados!"));
        if (balcao == null) {
            throw new NotFoundException("Orçamento não encontrado na base de dados!");
        }
        return balcao;
    }

    @Transactional(rollbackFor = {NullPointerException.class, IllegalArgumentException.class, org.hibernate.exception.ConstraintViolationException.class})
    public void delete(Usuario usuario, Integer idBalcao) {
        Balcao balcao = balcaoDao.getOne(idBalcao);

        if (balcao.getStatus().equals("OC") && !usuario.getPermissoes().contains("BALCAO_EXCLUIR")) {
            throw new PermissaoInsuficienteException(usuario.getLogin() + ", você não tem permissão para esta operação!");
        }
        balcao.setIdUsuarioex(usuario.getId());
        balcao.setDataHoraex(balcaoDao.getDataServer());
        balcao.setStatus("EX");
        balcaoDao.save(balcao);
        //balcaoDao.deleteById(id);
    }

}
