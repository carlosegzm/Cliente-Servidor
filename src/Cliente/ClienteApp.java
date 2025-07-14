package cliente;

import view.Tela;

public class ClienteApp {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Tela().setVisible(true));
    }
}
