/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Dray
 */
public class ProjetoERP {

    public static void main(String[] args) {

       Scanner scan = new Scanner(System.in);
       CadastroPaciente cadPa = new CadastroPaciente();
       CadastroMedico cadMe = new CadastroMedico();
       ArrayList<Paciente> pacientes = new ArrayList<>();
       ArrayList<Medico> medicos = new ArrayList<>();
       int resp, aux=0;
       String user, pass, trig;
       boolean isPaciente = false, login = false, cancel = false, ini=true;

       // menu inicial
       while(ini){
            System.out.print("---- MENU ----\n\n1) Cadastrar como paciente\n2) Cadastrar como médico\n3) Login\n4) Desligar\n");
            resp = scan.nextInt();
            switch(resp){
                case 1:
                    pacientes.add(cadPa.cadastro());
                case 2:
                    medicos.add(cadMe.cadastro());
                case 3:
                    boolean quebra = true;
                    cancel = false;
                    while(quebra){
                        aux=0;
                        System.out.println("Digite seu nome: ");
                        user = scan.next();
                        boolean noResult = true;
                        for (Paciente temp : pacientes) {
                            if (temp.nome.equals(user)) {
                                isPaciente = true;
                                quebra = false;
                                noResult = false;
                                break;
                            }
                            aux++;
                        }
                        boolean NOResult = true;
                        if(noResult){
                            aux=0;
                            for(Medico Temp : medicos){
                                if(Temp.nome.equals(user)) {
                                    quebra = false;
                                    NOResult = false;
                                    break;
                                }
                                aux++;
                            }
                        }
                        if(quebra){
                            System.out.println("Usuário não encontrado. Continuar? S/N");
                            trig = scan.next();
                            if(trig.equals("N") || trig.equals("n")) {
                                cancel = true;
                                quebra = false;
                            }
                        }
                    }
                    if(cancel) {
                        break;
                    }
                    quebra = true;
                    while(quebra){
                        System.out.println("Digite sua senha: ");
                        pass = scan.next();
                        if(isPaciente) {
                            if(pass.equals(pacientes.get(aux).senha)){
                                login = true;
                                quebra = false;
                            } else {
                                System.out.println("Senha incorreta, deseja continuar? S/N");
                                trig = scan.next();
                                if(trig.equals("N") || trig.equals("n")) {
                                    cancel = true;
                                    quebra = false;
                                }
                            }
                        } else {
                            if(pass.equals(medicos.get(aux).senha)) {
                                login = true;
                                quebra = false;
                            } else {
                                System.out.println("Senha incorreta, deseja continuar? S/N");
                                trig = scan.next();
                                if(trig.equals("N") || trig.equals("n")) {
                                    cancel = true;
                                    quebra = false;
                                }
                            }
                        }
                        if(cancel){
                            break;
                        }
                    }
                    quebra = true;
                    if(login){
                        if(isPaciente) {
                            while(quebra) {

                            }
                        }
                    }
                case 4:
                    ini = false;
                    break;
                default:
                    System.out.println("Comando inválido");
            }
        }
    } 
}
