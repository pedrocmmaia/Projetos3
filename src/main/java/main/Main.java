package main;

import config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando conexão com o banco de dados...");
        DatabaseConfig.criarTabelas();
        System.out.println("Fim do processo.");
    }
}