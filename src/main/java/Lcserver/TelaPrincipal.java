/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver;

import Lcserver.Auditoria.Auditoria;
import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Configuracao.BalcaoConfigDao;
import Lcserver.Mensagens.MsgTelaAtencao;
import Lcserver.Mensagens.MsgTelaErro;
import Lcserver.Mensagens.msgTelaOK;
import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Configuracao.ConfigDao;
import Lcserver.Empresa.Empresa;
import Lcserver.Empresa.EmpresaController;
import Lcserver.Empresa.EmpresaRepository;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Mensagens.msgTelaPergunta;
import Lcserver.Usuario.Usuario;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaTeste
     */
    private String txt = "";
    private String txtErro = "";
    private DefaultTableModel modeloMobile = null;
    private BalcaoMobile mobileTemp = null;
    public ArrayList<BalcaoMobile> listMobile = new ArrayList();
    public ArrayList<Empresa> listEmpresa = new ArrayList();
    public static TelaPrincipal TelaPrincipal;
    public static BalcaoSysTray balcaoSysTray;

    @Autowired
    private BalcaoMobileControle mobileControle;
    @Autowired
    private BalcaoConfigDao balcaoConfigDao;
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private EmpresaController empresaController;

    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        modeloMobile = (DefaultTableModel) jtb_tabelaMobile.getModel();
        setTitle("LC Balcão Server " + SessaoAberta.getVersao());
    }

    private void tela_dispositivo() {
        jd_dispositivo.setSize(440, 167);
        jd_dispositivo.setModal(true);
        jd_dispositivo.setLocationRelativeTo(null);
        jd_dispositivo.setVisible(true);
    }

    public void setLog(String txt) {
        this.txt = jTextArea1.getText();
        this.txt += txt + " - " + Funcoes.getDataHoraPC() + "\n";
        jTextArea1.setText(this.txt);
    }

    public void setLogAndValidaImei(String log, String imei, Integer idEmpresa) {
        this.txt = jTextArea1.getText();
        this.txt += log + " - " + Funcoes.getDataHoraPC() + "\n";
        jTextArea1.setText(this.txt);
        BalcaoMobile mobile = mobileControle.validaAndroid(imei, idEmpresa);
        atualizaTabela(mobile.getEmpresa());
        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException("Usuário inativo! Ative-o no servidor!");
        }
    }

    public void setLog(String txt, Usuario usuario) {
        this.txt = jTextArea1.getText();
        this.txt += txt + " - " + Funcoes.getDataHoraPC() + "\n";
        this.txt += "Usuário: " + usuario.getId() + " - " + usuario.getLogin() + "\n";
        jTextArea1.setText(this.txt);
    }

    public void setLogAuditoria(BalcaoMobile mobile, ArrayList<Auditoria> listAuditoria, Empresa empresa) {
        txt += "-----------------------------------------------------------\n";
        txt += "DISPOSITIVO: " + mobile.getId() + " | " + mobile.getNome() + " | " + mobile.getUsuario() + " | " + mobile.getImei() + " | " + mobile.getStatus() + "\n";
        txt += "-----------------------------------------------------------\n";
        for (Auditoria auditoria : listAuditoria) {
            txt += auditoria.getId() + " - Auditoria: " + Funcoes.formataDataHoraBr(auditoria.getDataHora()) + "\r\n";
            txt += "USUARIO: " + auditoria.getIdUsuario() + "\r\n";
            txt += "EMPRESA: " + empresa.getId() + "\r\n";
            txt += "LOCAL: " + auditoria.getLocal() + "\r\n";
            txt += "METODO: " + auditoria.getMetodo() + "\r\n";
            txt += "DESCRICAO:\n" + auditoria.getDescricao().replace("\n", "\n");
            txt += "-------------------------------------------------------\n";
        }
        jTextArea1.setText(this.txt);
    }

    public void setErro(String nome_metodo, Exception ex) {
        this.txtErro = jtxtLogErro.getText();
        this.txtErro += "ERRO: " + Funcoes.getDataHoraPC() + "\n" + nome_metodo + ":" + "\n{" + ex.toString() + "\n" + ex.getMessage() + "\n}\n";
        jtxtLogErro.setText(this.txtErro);
    }

    public void atualizaTabela(Empresa empresa) {
        SessaoAberta.setEmpresa(empresa);
        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getEmpresa().getCnpj(), balcaoConfigDao.getBalcaoConfigById(SessaoAberta.getEmpresa().getId())));
        modeloMobile.setNumRows(0);
        listMobile = (ArrayList<BalcaoMobile>) mobileControle.getListMobileByIdEmpresa(String.valueOf(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId()));
        for (BalcaoMobile m : listMobile) {
            modeloMobile.addRow(new Object[]{
                m.getId(),
                m.getEmpresa().getId(),
                m.getNome(),
                m.getUsuario(),
                m.getImei(),
                m.getStatus(),});
        }
    }

    public void atualizaTabela(ArrayList<BalcaoMobile> list, Empresa empresa) {
        this.listMobile = list;
        atualizaTabela(empresa);
    }

    public void atualizaEmpresas() {
        jcb_empresa1.removeAll();
        listEmpresa = (ArrayList<Empresa>) empresaController.getEmpresas();
        for (Empresa e : listEmpresa) {
            jcb_empresa1.addItem(e.getFantasia() + " - CNPJ: " + e.getCnpj());
        }
    }

    public void atualizaEmpresas(ArrayList<Empresa> list) {
        this.listEmpresa = list;
        atualizaEmpresas();
    }

    public void abriTela(TelaPrincipal tl) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            SwingUtilities.updateComponentTreeUI(jd_dispositivo);
            TelaPrincipal = tl;
            TelaPrincipal.setVisible(false);
            balcaoSysTray = new BalcaoSysTray();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fechar() {
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_dispositivo = new javax.swing.JDialog();
        jPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jb_sair2 = new javax.swing.JButton();
        jb_salvar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jtf_id1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtf_imei1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jtf_usuario1 = new javax.swing.JTextField();
        jtf_dispPermitido1 = new javax.swing.JTextField();
        jtf_nomeMobile1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtb_tabelaMobile = new javax.swing.JTable();
        jb_atualizar = new javax.swing.JButton();
        jb_detalhar = new javax.swing.JButton();
        jb_sair = new javax.swing.JButton();
        jb_excluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jtf_codInstalacao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtn_registrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtLogErro = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcb_empresa1 = new javax.swing.JComboBox<>();

        jd_dispositivo.setUndecorated(true);
        jd_dispositivo.setResizable(false);

        jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Dispositivo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jb_sair2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_sair2.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagem/door_in.png"));
        jb_sair2.setText("Sair");
        jb_sair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_sair2ActionPerformed(evt);
            }
        });

        jb_salvar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_salvar.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagem/disk.png"));
        jb_salvar.setText("Salvar");
        jb_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_salvarActionPerformed(evt);
            }
        });

        jLabel11.setText("Status:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATIVO", "INATIVO" }));

        jLabel12.setText("Código:");

        jtf_id1.setEditable(false);
        jtf_id1.setBackground(new java.awt.Color(255, 255, 0));

        jLabel13.setText("IMEI:");

        jtf_imei1.setEditable(false);
        jtf_imei1.setBackground(new java.awt.Color(255, 255, 0));

        jLabel14.setText("Usuário:");

        jLabel15.setText("Permitidos:");

        jtf_usuario1.setEditable(false);
        jtf_usuario1.setBackground(new java.awt.Color(255, 255, 0));

        jtf_dispPermitido1.setEditable(false);
        jtf_dispPermitido1.setBackground(new java.awt.Color(255, 255, 0));
        jtf_dispPermitido1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setText("Nome:");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jb_salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_sair2))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtf_imei1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jtf_id1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jtf_nomeMobile1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_dispPermitido1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox2, jtf_dispPermitido1, jtf_id1, jtf_imei1, jtf_nomeMobile1, jtf_usuario1});

        jPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jb_sair2, jb_salvar});

        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jtf_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jtf_dispPermitido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(jtf_imei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jtf_usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtf_nomeMobile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_sair2)
                    .addComponent(jb_salvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jtf_id1, jtf_imei1, jtf_nomeMobile1});

        javax.swing.GroupLayout jd_dispositivoLayout = new javax.swing.GroupLayout(jd_dispositivo.getContentPane());
        jd_dispositivo.getContentPane().setLayout(jd_dispositivoLayout);
        jd_dispositivoLayout.setHorizontalGroup(
            jd_dispositivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jd_dispositivoLayout.setVerticalGroup(
            jd_dispositivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jtb_tabelaMobile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "EMPRESA", "NOME", "USUARIO", "IMEI", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtb_tabelaMobile.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtb_tabelaMobile.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtb_tabelaMobile);
        if (jtb_tabelaMobile.getColumnModel().getColumnCount() > 0) {
            jtb_tabelaMobile.getColumnModel().getColumn(0).setMinWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(0).setPreferredWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(0).setMaxWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(1).setMinWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(1).setMaxWidth(80);
            jtb_tabelaMobile.getColumnModel().getColumn(5).setMinWidth(100);
            jtb_tabelaMobile.getColumnModel().getColumn(5).setPreferredWidth(100);
            jtb_tabelaMobile.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jb_atualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_atualizar.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagens/arrow_refresh.png"));
        jb_atualizar.setText("Atualizar");
        jb_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_atualizarActionPerformed(evt);
            }
        });

        jb_detalhar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_detalhar.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagens/page_edit.png"));
        jb_detalhar.setText("Detalhar");
        jb_detalhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_detalharActionPerformed(evt);
            }
        });

        jb_sair.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_sair.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagens/door_in.png"));
        jb_sair.setText("Sair");
        jb_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_sairActionPerformed(evt);
            }
        });

        jb_excluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jb_excluir.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagens/cancelar_16x16.png"));
        jb_excluir.setText("Excluir");
        jb_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 583, Short.MAX_VALUE)
                        .addComponent(jb_atualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_excluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_detalhar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_sair)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jb_atualizar, jb_detalhar, jb_excluir, jb_sair});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_detalhar)
                    .addComponent(jb_atualizar)
                    .addComponent(jb_sair)
                    .addComponent(jb_excluir))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Disp. Movel", jPanel2);

        jtf_codInstalacao.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 1, 16)); // NOI18N
        jtf_codInstalacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Código de Ativação");

        jbtn_registrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jbtn_registrar.setIcon(new javax.swing.ImageIcon("src/main/java/Lcserver/imagem/disk.png"));
        jbtn_registrar.setText("Registrar");
        jbtn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_registrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(jbtn_registrar))
                    .addComponent(jtf_codInstalacao)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtf_codInstalacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(jbtn_registrar)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtn_registrar, jtf_codInstalacao});

        jTabbedPane1.addTab("Registro", jPanel6);

        jLabel2.setText("Log:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Log de Erros:");

        jtxtLogErro.setEditable(false);
        jtxtLogErro.setColumns(20);
        jtxtLogErro.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jtxtLogErro.setLineWrap(true);
        jtxtLogErro.setRows(5);
        jScrollPane2.setViewportView(jtxtLogErro);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Log", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Servidor Balcão - Android");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Empresa");

        jcb_empresa1.setToolTipText("");
        jcb_empresa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_empresa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcb_empresa1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcb_empresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_sair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_sair2ActionPerformed
        jd_dispositivo.dispose();
    }//GEN-LAST:event_jb_sair2ActionPerformed

    private void jb_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_salvarActionPerformed
        int numAtivo = mobileControle.getTotalMobileAtivo(mobileTemp.getEmpresa().getId());
        if (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("INATIVO")) {
            salvar();
        } else {
            if (numAtivo >= SessaoAberta.getQntMobilePermitida()) {
                new MsgTelaAtencao(null, true, "Número de Dispositivos Mobile Ativo Excedido !\nDispositivos Ativos: " + numAtivo + "\nPermitidos: " + SessaoAberta.getQntMobilePermitida()).setVisible(true);
            } else {
                salvar();
            }
        }
    }//GEN-LAST:event_jb_salvarActionPerformed

    private void jb_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_atualizarActionPerformed
        BalcaoConfig balcaoConfig = balcaoConfigDao.getBalcaoConfigById(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId());
//        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getCnpj(), balcaoConfig));
        jtf_codInstalacao.setText(balcaoConfig == null ? "" : balcaoConfig.getSerial());
        atualizaTabela(listEmpresa.get(jcb_empresa1.getSelectedIndex()));
    }//GEN-LAST:event_jb_atualizarActionPerformed

    private void jb_detalharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_detalharActionPerformed
        detalhar();
//        System.out.println(listMobile.get(jtb_tabelaMobile.getSelectedRow()) + "");
    }//GEN-LAST:event_jb_detalharActionPerformed

    private void jb_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_sairActionPerformed
        if (balcaoSysTray == null) {
            balcaoSysTray = new BalcaoSysTray();
        }
        this.setVisible(false);
    }//GEN-LAST:event_jb_sairActionPerformed

    private void jbtn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_registrarActionPerformed
        registrar();
    }//GEN-LAST:event_jbtn_registrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (balcaoSysTray == null) {
            balcaoSysTray = new BalcaoSysTray();
        }
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jb_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_jb_excluirActionPerformed

    private void jcb_empresa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_empresa1ActionPerformed
        atualizaTabela(listEmpresa.get(jcb_empresa1.getSelectedIndex()));
    }//GEN-LAST:event_jcb_empresa1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jb_atualizar;
    private javax.swing.JButton jb_detalhar;
    private javax.swing.JButton jb_excluir;
    private javax.swing.JButton jb_sair;
    private javax.swing.JButton jb_sair2;
    private javax.swing.JButton jb_salvar;
    private javax.swing.JButton jbtn_registrar;
    private javax.swing.JComboBox<String> jcb_empresa1;
    private javax.swing.JDialog jd_dispositivo;
    private javax.swing.JTable jtb_tabelaMobile;
    private javax.swing.JTextField jtf_codInstalacao;
    private javax.swing.JTextField jtf_dispPermitido1;
    private javax.swing.JTextField jtf_id1;
    private javax.swing.JTextField jtf_imei1;
    private javax.swing.JTextField jtf_nomeMobile1;
    private javax.swing.JTextField jtf_usuario1;
    private javax.swing.JTextArea jtxtLogErro;
    // End of variables declaration//GEN-END:variables

    private BalcaoMobile carregaMobile() {
        BalcaoMobile mobile = mobileTemp;
        mobile.setNome(jtf_nomeMobile1.getText());
        mobile.setStatus(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        return mobile;
    }

    private void registrar() {
        if (jtf_codInstalacao.getText().toString().trim().isEmpty()) {
            new MsgTelaAtencao(this, true, "Serial para Liberação de Dispositivos Invalido !").setVisible(true);
        } else {
            try {
//                balcaoConfigDao.deleteById(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId());
                balcaoConfigDao.save(new BalcaoConfig(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId(), listEmpresa.get(jcb_empresa1.getSelectedIndex()), jtf_codInstalacao.getText().toString()));
                SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getCnpj(), balcaoConfigDao.getBalcaoConfigById(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId())));
                if (SessaoAberta.getQntMobilePermitida() == 0) {
                    new MsgTelaAtencao(this, true, "Código de Instalação Cadastrado Invalido para Este CNPJ !\n").setVisible(true);
                } else {
                    new msgTelaOK(this, true, "Código de Instalação Cadastrado com Sucesso !\n" + "Dispositivos: " + SessaoAberta.getQntMobilePermitida()).setVisible(true);
                }
            } catch (Exception e) {
                setErro("jbtn_registrarActionPerformed", e);
                new MsgTelaErro(this, true, "Erro ao Registrar", e, getClass(), "jbtn_registrarActionPerformed", listEmpresa.get(jcb_empresa1.getSelectedIndex())).setVisible(true);
            }
        }
    }

    private void salvar() {
        mobileControle.cadastrarMobile(carregaMobile());
        novo();
        atualizaTabela(SessaoAberta.getEmpresa());
        jd_dispositivo.dispose();
    }

    private void novo() {
        jtf_id1.setText("");
        jtf_imei1.setText("");
        jtf_nomeMobile1.setText("");
        jtf_usuario1.setText("");
        jComboBox2.setSelectedIndex(0);
        mobileTemp = null;
    }

    private void excluir() {
        if (jtb_tabelaMobile.getSelectedRow() < 0) {
            new MsgTelaAtencao(this, true, "Selecione um dispositivo na tabela para poder excluir").setVisible(true);
        } else {
            BalcaoMobile mobile = listMobile.get(jtb_tabelaMobile.getSelectedRow());
            if (mobile.getStatus().equals("ATIVO")) {
                if (new msgTelaPergunta(this, true, "Dispositivo está ativo, deseja excluir mesmo assim?").pergunta()) {
                    mobileControle.removerMobile(listMobile.get(jtb_tabelaMobile.getSelectedRow()));
                    atualizaTabela(mobile.getEmpresa());
                }
            } else {
                if (new msgTelaPergunta(this, true, "Deseja excluir este dispositivo?").pergunta()) {
                    mobileControle.removerMobile(listMobile.get(jtb_tabelaMobile.getSelectedRow()));
                    atualizaTabela(mobile.getEmpresa());
                }
            }
        }
    }

    private void detalhar() {
        if (jtb_tabelaMobile.getSelectedRow() < 0) {
            new MsgTelaAtencao(this, true, "Selecione um dispositivo na tabela").setVisible(true);
        } else {
            SessaoAberta.setConfig(configDao.getConfigByIdEmpresa(listEmpresa.get(jcb_empresa1.getSelectedIndex()).getId()));
            SessaoAberta.setEmpresa(listEmpresa.get(jcb_empresa1.getSelectedIndex()));
            SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getEmpresa().getCnpj(), balcaoConfigDao.getBalcaoConfigById(listMobile.get(jtb_tabelaMobile.getSelectedRow()).getEmpresa().getId())));
            novo();
            mobileTemp = listMobile.get(jtb_tabelaMobile.getSelectedRow());
            jtf_id1.setText(mobileTemp.getId() + "");
            jtf_imei1.setText(mobileTemp.getImei());
            jtf_nomeMobile1.setText(mobileTemp.getNome());
            jtf_usuario1.setText(mobileTemp.getUsuario());
            jtf_dispPermitido1.setText(SessaoAberta.getQntMobilePermitida() + "");
            jComboBox2.setSelectedItem(mobileTemp.getStatus());
            tela_dispositivo();
        }
    }

}
