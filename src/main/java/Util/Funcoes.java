/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Mensagens.MsgTelaAtencao;
import Lcserver.SpringConfig.Database.Database;
import Lcserver.Usuario.Usuario;
import SessaoAberta.SessaoAberta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class Funcoes {

    public static String getDataHoraPC() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dt.format(new Date().getTime());
    }

    public static String getDataHoraAmericanoPC() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dt.format(new Date().getTime());
    }

    public static String formataDataHoraBr(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dt.format(date.getTime());
    }

    public static String formataDataHoraAmericano(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dt.format(date.getTime());
    }

    public static String formataCpf_Cnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 14) {
            cpfCnpj = cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2, 5) + "." + cpfCnpj.substring(5, 8) + "/" + cpfCnpj.substring(8, 12) + "-" + cpfCnpj.substring(12, 14);
        } else if (cpfCnpj.length() == 11) {
            cpfCnpj = cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6) + "." + cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9, 11);
        }
        return cpfCnpj;
    }

    public static String removeMascaras(String txt) {
        if (txt != null) {
            txt = txt.replaceAll("\\.", "").replace("(", "").replace(")", "").replace("-", "").replaceAll("/", "");
            txt = txt.trim();
        }
        return txt;
    }

    public static Date getDataHoraPCMySql() {
        Date data = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dt.format(data);
        try {
            data = dt.parse(dt.format(data));
        } catch (ParseException ex) {
            Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public static String getHoraEntreDatas(String dataHoraInicial, String dataHoraFinal) throws Exception {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.set(Calendar.YEAR, Integer.parseInt(dataHoraInicial.substring(0, 4)));
        c1.set(Calendar.MONTH, Integer.parseInt(dataHoraInicial.substring(5, 7)));
        c1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataHoraInicial.substring(8, 10)));
        c1.set(Calendar.HOUR, Integer.parseInt(dataHoraInicial.substring(11, 13)));
        c1.set(Calendar.MINUTE, Integer.parseInt(dataHoraInicial.substring(14, 16)));
        c1.set(Calendar.SECOND, Integer.parseInt(dataHoraInicial.substring(17, 19)));

        c2.set(Calendar.YEAR, Integer.parseInt(dataHoraFinal.substring(0, 4)));
        c2.set(Calendar.MONTH, Integer.parseInt(dataHoraFinal.substring(5, 7)));
        c2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataHoraFinal.substring(8, 10)));
        c2.set(Calendar.HOUR, Integer.parseInt(dataHoraFinal.substring(11, 13)));
        c2.set(Calendar.MINUTE, Integer.parseInt(dataHoraFinal.substring(14, 16)));
        c2.set(Calendar.SECOND, Integer.parseInt(dataHoraFinal.substring(17, 19)));

        long diff = c2.getTimeInMillis() - c1.getTimeInMillis();

        long hours = (60 * 60 * 1000);
        long diffHoras = diff / hours;
        long diffHorasMinutos = (diff % hours) / (60 * 1000);
        long diffSegundos = (diff / 1000) % 60;

        return Funcoes.formatoCodigo2(diffHoras) + ":" + Funcoes.formatoCodigo2(diffHorasMinutos) + ":" + Funcoes.formatoCodigo2(diffSegundos);
    }

    public static String formatoCodigo2(double id) {
        DecimalFormat df = new DecimalFormat("00");
        String format = df.format(id);
        return format;
    }

    public static String formatoCodigo3(double id) {
        DecimalFormat df = new DecimalFormat("000");
        String format = df.format(id);
        return format;
    }

    public static String formatoCodigo4(double id) {
        DecimalFormat df = new DecimalFormat("0000");
        String format = df.format(id);
        return format;
    }

    public static String formatoCodigo6(double id) {
        DecimalFormat df = new DecimalFormat("000000");
        String format = df.format(id);
        return format;
    }

    public static String formatoCodigo9(double id) {
        DecimalFormat df = new DecimalFormat("000000000");
        String format = df.format(id);
        return format;
    }

    public static String formatoReal(double valor) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String format = df.format(valor);
        return format;
    }

    public static double formatoReal2(double valor) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String format = df.format(valor);
        format = format.replace(".", "").replace(",", ".");
        return Double.parseDouble(format);
    }

    public static String formatoPeso(double peso) {
        DecimalFormat df = new DecimalFormat("#####0.000");
        String format = df.format(peso);
        return format;
    }

    public static double arredondarValor(double valor, int Qtscasa, boolean podeArrendodar) {
        double arredondado = valor;
        arredondado *= (Math.pow(10, Qtscasa));
        if (podeArrendodar) {
            arredondado = Math.ceil(arredondado);
        } else {
            arredondado = Math.floor(arredondado);
        }
        arredondado /= (Math.pow(10, Qtscasa));
        return arredondado;
    }

    public static double acrescimo(double valor, double vlrAcrecimoPercentual) {
        double percentual = vlrAcrecimoPercentual / 100.00;
        return formatoReal2(percentual * valor);
        // return arredondarValor((percentual * valor), 2, false);
    }

    public static String centralizarTexto(String palavra, int tamanho) {
        if (palavra == null) {
            palavra = "";
        }
        int p = (tamanho - palavra.length()) / 2;
        for (int i = 0; i < p; i++) {
            palavra = " " + palavra;
        }
        if (palavra.length() >= tamanho) {
            palavra = palavra.substring(0, tamanho);
        }
        return palavra;
    }

    public static String converte_Base_64(String txt) {
        String StrAutenticacao_Base64 = java.util.Base64.getEncoder().encodeToString(txt.getBytes());
        return StrAutenticacao_Base64;
    }

    public static Usuario decode_Base_64_usuario(String txt) {
        String[] txtUser = StringUtils.newStringUtf8(Base64.decodeBase64(txt)).split(":");
        Usuario usuario = new Usuario();
        for (int i = 0; i < txt.length(); i++) {
            if (i == 0) {
                usuario.setId(Integer.parseInt(txtUser[0]));
            } else if (i == 1) {
                usuario.setSenha(txtUser[1]);
            } else if (i == 2) {
                usuario.setLogin(txtUser[2]);
            }
        }
        return usuario;
    }

    public static String algLeft(String n, int t) {
        String s = "";
        if (n == null) {
            s = "";
        } else if (n.length() > t) {
            s = n.substring(0, t + 1);

        } else {
            for (int i = n.length(); i <= t; i++) {
                s += " ";
            }
            s = n + s;
        }
        return s;
    }

    public static String algRight(String n, int t) {
        String s = "";
        for (int i = n.length(); i <= t; i++) {
            s += " ";
        }
        return s + n;
    }

    public static int getMobilePermitido(String cnpj, BalcaoConfig config) {
        String senha;
        if (config == null || config.getSerial().isEmpty() || cnpj.equals("")) {
            return 0;
        } else {
            senha = config.getSerial();
        }

        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        senha = senha.toUpperCase();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) - 10;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        senhaCriptografada = senhaCriptografada.replaceAll("\\D", "");
        cnpj = cnpj.replaceAll("\\D", "");

        if (senhaCriptografada.length() > 3 && senhaCriptografada.substring(0, senhaCriptografada.length() - 3).equals(cnpj)) {
            return Integer.parseInt(senhaCriptografada.substring(senhaCriptografada.length() - 3, senhaCriptografada.length()));
        } else {
            return 0;
        }
    }

    public static String formatoJsonString(String msg) {
        return "{ \"response\" : \"" + msg + "\" }";
    }

    public static boolean getRedeTxt() throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\rede.txt");

        if (!file.exists()) {
            return false;
        } else if (file.length() == 0) {
            return false;
        } else {

            String linha = "";
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            Database databaseConfig = new Database();

            while ((linha = reader.readLine()) != null) {

                String[] split = linha.split(":");

                if (split[0].equalsIgnoreCase("IP")) {
                    databaseConfig.setIp(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("DB")) {
                    databaseConfig.setDatabase(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("USER")) {
                    databaseConfig.setUsuario(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("KEY")) {
                    databaseConfig.setSenha(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("PORT")) {
                    databaseConfig.setPorta(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("TERMINAL_TIPO")) {
                    databaseConfig.setTerminal_tipo(linha.split(":")[1]);
                }
                if (split[0].equalsIgnoreCase("ID_EMPRESA_PADRAO")) {
                    databaseConfig.setId_empresa_padrao(linha.split(":")[1]);
                }
            }
            fileReader.close();
            reader.close();

            SessaoAberta.setDatabase(databaseConfig);
            return true;
        }
    }

}
