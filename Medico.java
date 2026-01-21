/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Dray
 */
public class Medico extends Pessoa {
    public ArrayList<String> horarios = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    public Medico(String nome, String senha, String CPF, String tel){
        super(nome, senha, CPF, tel);
    }
    
    public void cadastrarHorario(){
        String txt;
        System.out.println("Digite uma data e horário (dd/mm/aa hh:mm): ");
        txt = scan.next();
        horarios.add(txt);
    }
}
