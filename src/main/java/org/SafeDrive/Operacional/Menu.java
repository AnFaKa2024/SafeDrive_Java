package org.SafeDrive.Operacional;

import java.util.Scanner;

public class Menu {

    private Object scanner;

    public void mostrarMenu(Scanner scanner) {

        int escolha;

        while(true){

            System.out.println(">> SEJA BEM VINDO AO SAFE DRIVE <<");
            System.out.println(">> Escolha a opção desejada ");


            System.out.println("""
                    0 - SAIR DO SAFE DRIVE
                    1 - CADASTRAR LOGIN E SENHA
                    2 - CADASTRAR USUÁRIO
                    3 - CADASTRAR VEÍCULO
                    """);

        }
    }

}
