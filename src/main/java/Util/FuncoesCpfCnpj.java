/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Jordan LC Sistemas
 */
public class FuncoesCpfCnpj {
    
    
    public static boolean ValidaCPF(String sDado) {
        byte[] D1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int DF1, DF2, DF3, DF4, DF5, DF6, Resto1, Resto2, PrimeiroDigito, SegundoDigito;
        String sConvert = sDado.trim();
        char[] Dado = sConvert.toCharArray();
        boolean bRetorno = true;
        if (sDado.trim().length() == 11) {
            for (int i = 1; i <= 9; i++) {
                if (Character.isDigit(Dado[i])) {
                    D1[i] = Byte.parseByte("" + Dado[i - 1]);
                } else {
                    bRetorno = false;
                }
            }
            if (bRetorno) {
                DF1 = 10 * D1[1] + 9 * D1[2] + 8 * D1[3] + 7 * D1[4] + 6
                        * D1[5] + 5 * D1[6] + 4 * D1[7] + 3 * D1[8] + 2 * D1[9];
                DF2 = DF1 / 11;
                DF3 = DF2 * 11;
                Resto1 = DF1 - DF3;
                if ((Resto1 == 0) || (Resto1 == 1)) {
                    PrimeiroDigito = 0;
                } else {
                    PrimeiroDigito = 11 - Resto1;
                }
                DF4 = 11 * D1[1] + 10 * D1[2] + 9 * D1[3] + 8 * D1[4] + 7
                        * D1[5] + 6 * D1[6] + 5 * D1[7] + 4 * D1[8] + 3 * D1[9]
                        + 2 * PrimeiroDigito;
                DF5 = DF4 / 11;
                DF6 = DF5 * 11;
                Resto2 = DF4 - DF6;
                if ((Resto2 == 0) || (Resto2 == 1)) {
                    SegundoDigito = 0;
                } else {
                    SegundoDigito = 11 - Resto2;
                }
                if ((PrimeiroDigito != Integer.parseInt(Dado[9] + ""))
                        || (SegundoDigito != Integer.parseInt(Dado[10] + ""))) {
                    bRetorno = false;
                }
            }
        } else if (sDado.trim().length() != 0) {
            bRetorno = false;
        }
        return bRetorno;
    }

    public static boolean ValidaCNPJ(String sDado) {
        byte[] D1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int DF1, DF2, DF3, DF4, DF5, DF6, Resto1, Resto2, PrimeiroDigito, SegundoDigito;
        String sConvert = " " + sDado.trim();
        char[] Dado = sConvert.toCharArray();
        boolean bRetorno = true;
        if (sDado.trim().length() == 14) {
            for (int i = 1; i <= 12; i++) {
                if (Character.isDigit(Dado[i])) {
                    D1[i] = Byte.parseByte("" + Dado[i]);
                } else {
                    bRetorno = false;
                }
            }
            if (bRetorno) {
                DF1 = 5 * D1[1] + 4 * D1[2] + 3 * D1[3] + 2 * D1[4] + 9 * D1[5]
                        + 8 * D1[6] + 7 * D1[7] + 6 * D1[8] + 5 * D1[9] + 4
                        * D1[10] + 3 * D1[11] + 2 * D1[12];
                DF2 = DF1 / 11;
                DF3 = DF2 * 11;
                Resto1 = DF1 - DF3;
                if ((Resto1 == 0) | (Resto1 == 1)) {
                    PrimeiroDigito = 0;
                } else {
                    PrimeiroDigito = 11 - Resto1;
                }
                DF4 = 6 * D1[1] + 5 * D1[2] + 4 * D1[3] + 3 * D1[4] + 2 * D1[5]
                        + 9 * D1[6] + 8 * D1[7] + 7 * D1[8] + 6 * D1[9] + 5
                        * D1[10] + 4 * D1[11] + 3 * D1[12] + 2 * PrimeiroDigito;
                DF5 = DF4 / 11;
                DF6 = DF5 * 11;
                Resto2 = DF4 - DF6;
                if ((Resto2 == 0) | (Resto2 == 1)) {
                    SegundoDigito = 0;
                } else {
                    SegundoDigito = 11 - Resto2;
                }
                if ((PrimeiroDigito != Integer.parseInt("" + Dado[13]))
                        | (SegundoDigito != Integer.parseInt("" + Dado[14]))) {
                    bRetorno = false;
                }
            }
        } else if (sDado.trim().length() != 0) {
            bRetorno = false;
        }
        return bRetorno;
    }

    public static boolean ValidaCpfCnpj(String eDado) {
        String removeMascaras = Funcoes.removeMascaras(eDado).trim();
        boolean ret = false;
        if (removeMascaras.length() == 11) {
            ret = ValidaCPF(removeMascaras);
        } else if (removeMascaras.length() == 14) {
            ret = ValidaCNPJ(removeMascaras);
        }
        return ret;
    }

    
}
