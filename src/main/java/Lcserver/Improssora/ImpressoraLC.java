package Lcserver.Improssora;

import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jordan
 */
public class ImpressoraLC {

    private String Porta = "";
    private FileOutputStream Impressora = null;
    final static String ESC = "\u001B";

    public ImpressoraLC(String Porta) {
        this.Porta = Porta;
    }

    public void imprimir(String txt) throws Exception {
        Impressora = new FileOutputStream(Porta);
        Impressora.write((txt + "\n").getBytes());
        Impressora.flush();
        Impressora.close();
    }

    public void acionaGuilhotina() throws Exception {
        Impressora = new FileOutputStream(Porta);
        Impressora.write(("\n\n\n\n").getBytes());
        Impressora.write((ESC + "m").getBytes());
        Impressora.flush();
        Impressora.close();
    }

    public void imprimirBema25004200(String txt) throws Exception {
        Impressora = new FileOutputStream(Porta);
        Impressora.write((char) 29);
        Impressora.write((char) 249);
        Impressora.write((char) 32);
        Impressora.write((char) 0);
        Impressora.write((txt).getBytes());
        Impressora.write(("\n").getBytes());
        Impressora.flush();
        Impressora.close();
    }
}
