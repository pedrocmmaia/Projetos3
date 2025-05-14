package main;

import config.DatabaseConfig;
//import view.OcorrenciaView;
//import view.ApartamentoBlocoView;
//import view.UsuarioView;
//import view.ComunicadoView;
import view.AreaComumView;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig.criarTabelas();
        //ApartamentoBlocoView.ApartamentoBlocoMenu();
        //UsuarioView.UsuarioMenu();
        //OcorrenciaView.menuOcorrencias();
        //ComunicadoView.menuComunicado();
        AreaComumView.AreaComumMenu();
    }
}
