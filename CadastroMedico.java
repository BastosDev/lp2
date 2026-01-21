/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;

/**
 *
 * @author Dray
 */
public class CadastroMedico {
    public Medico cadastro() {
        String nome, senha, CPF, tel, conSenha, respS;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Digite o seu nome: ");
            nome = scan.next();
            System.out.println("Este nome está correto? S/N");
            respS = scan.next();
            if (respS.contentEquals("S") || respS.contentEquals("s")) {
                break;
            } else {
                System.out.println("Tente novamente.");
            }
        }
        while (true) {
            System.out.println("Digite o seu CPF (apenas número): ");
            CPF = scan.next();
            if (CPF.length() == 11) {
                break;
            } else {
                System.out.println("CPF inválido, número de digitos não confere.");
            }
        }
        while (true) {
            System.out.println("Digite o seu número de telefone (Ex: 9--------");
            tel = scan.next();
            if(tel.length()==9){
                break;
            } else {
                System.out.println("Número inválido, tente novamente.");
            }
        }
        while (true) {
            System.out.println("Digite uma senha");
            senha = scan.next();
            System.out.println("Digite a senha novamente para confirmar: ");
            conSenha = scan.next();
            if (senha.contentEquals(conSenha)) {
                break;
            } else {
                System.out.println("As senhas não correspondem, tente novamente.");
            }
        }
        return new Medico(nome, senha, CPF, tel);
    }
}
