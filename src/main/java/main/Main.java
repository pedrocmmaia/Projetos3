package main;

import config.DatabaseConfig;
//import view.LoginView;
import view.ReservaView;
import view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        //LoginView.loginMenu();
        DatabaseConfig.criarTabelas();
        ReservaView.ReservaMenu();
        //UsuarioView.UsuarioMenu();
    }
}
