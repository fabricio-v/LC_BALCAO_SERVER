package Lcserver;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BalcaoSysTray {

    public static TrayIcon trayIcon = null;
    public static TelaPrincipal pai = TelaPrincipal.TelaPrincipal;

    BalcaoSysTray() {
        super();
        criaAreaNotificacao();
    }

    static void criaAreaNotificacao() {
        if (!SystemTray.isSupported()) {
            System.out.println("Não da pra fazer, nem tenta!");
            return;
        }
        final PopupMenu pop = new PopupMenu();

        final SystemTray tray = SystemTray.getSystemTray();
        String icoPath = "icoSysTrayBalcao.png";
        trayIcon = new TrayIcon(new ImageIcon(icoPath, "omt").getImage(), "<<< LC Balcão Server >>>");
        trayIcon.setImageAutoSize(true);

        MenuItem item_1 = new MenuItem("     LC Balcão Server - " + SessaoAberta.SessaoAberta.getVersao());
        MenuItem item_2 = new MenuItem("Abrir");
        MenuItem item_3 = new MenuItem("Fechar (Não Recomendável)");

        item_1.setEnabled(false);

        pop.add(item_1);
        pop.addSeparator();
        pop.add(item_2);
        pop.add(item_3);

        trayIcon.setPopupMenu(pop);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Não deu pra fazer isso...");
            return;
        }

        item_2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    //Simplesmente deixa-se a janela visivel novamente.
                    pai.setVisible(true);
                    //Esta linha deixa a janela sobre as outras, caso ela apareca minimizada.
                    pai.setExtendedState(JFrame.NORMAL);
                    //Agora basta remover (ou esconder) o icone da area de Notificacao
                    //tray.remove(trayIcon);
                    // trayIcon = null;

                } catch (Throwable e1) {
                    e1.printStackTrace();
                }
            }
        });

        item_3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja Realmente Finalizar LC Balcão Server", "LC Balcão Server", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (showConfirmDialog == 0) {
                    try {
                        tray.remove(trayIcon);
                        TelaPrincipal.balcaoSysTray.finalize();
                        trayIcon = null;
                        pai.fechar();
                    } catch (Throwable ex) {
                        Logger.getLogger(BalcaoSysTray.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = BalcaoSysTray.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Caminho nao encontrado: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
