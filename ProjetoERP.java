import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ProjetoERP {

    public static void salvarLog(String msg) {
        try {
            FileWriter fw = new FileWriter("log.txt", true);
            fw.write(msg + "\n");
            fw.close();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {

       Scanner scan = new Scanner(System.in);
       CadastroPaciente cadPa = new CadastroPaciente();
       CadastroMedico cadMe = new CadastroMedico();
       ArrayList<Paciente> pacientes = new ArrayList<>();
       ArrayList<Medico> medicos = new ArrayList<>();

       boolean ini = true;

       while(ini){
            System.out.print("\n---- MENU ----\n1) Cadastrar como paciente\n2) Cadastrar como médico\n3) Login\n4) Sair\nOpção: ");
            int resp = scan.nextInt();
            scan.nextLine();

            switch(resp){
                case 1:
                    Paciente p = cadPa.cadastro();
                    pacientes.add(p);
                    salvarLog("Novo paciente: " + p.nome);
                    System.out.println("Paciente cadastrado!");
                    break;
                case 2:
                    Medico m = cadMe.cadastro();
                    medicos.add(m);
                    salvarLog("Novo medico: " + m.nome);
                    System.out.println("Médico cadastrado!");
                    break;
                case 3:
                    System.out.println("Digite seu CPF: ");
                    String loginCpf = scan.nextLine();
                    System.out.println("Digite sua senha: ");
                    String loginSenha = scan.nextLine();

                    Paciente pacLogado = null;
                    Medico medLogado = null;

                    for(Paciente pa : pacientes){
                        if(pa.CPF.equals(loginCpf) && pa.senha.equals(loginSenha)){
                            pacLogado = pa;
                            break;
                        }
                    }

                    if(pacLogado == null){
                        for(Medico me : medicos){
                            if(me.CPF.equals(loginCpf) && me.senha.equals(loginSenha)){
                                medLogado = me;
                                break;
                            }
                        }
                    }

                    if(pacLogado != null){
                        salvarLog("Login Paciente: " + pacLogado.nome);
                        boolean menuP = true;
                        while(menuP){
                            System.out.println("\n--- Área do Paciente " + pacLogado.nome + " ---");
                            System.out.println("1) Agendar Consulta");
                            System.out.println("2) Ver Minhas Consultas");
                            System.out.println("3) Cancelar Consulta");
                            System.out.println("0) Voltar");
                            int op = scan.nextInt();
                            scan.nextLine();

                            if(op == 1) pacLogado.iniciarAgendamento(medicos);
                            else if(op == 2) pacLogado.listarConsultas();
                            else if(op == 3) {
                                System.out.println("Selecione o médico da consulta:");
                                for(int i=0; i<medicos.size(); i++){
                                    System.out.println((i+1) + ") " + medicos.get(i).nome);
                                }
                                int idMed = scan.nextInt();
                                scan.nextLine();
                                if(idMed > 0 && idMed <= medicos.size()){
                                    Medico medicoAlvo = medicos.get(idMed-1);
                                    System.out.println("Digite o horário exato (copie da sua lista):");
                                    pacLogado.listarConsultas();
                                    String dataHora = scan.nextLine();
                                    pacLogado.cancelarConsulta(dataHora, medicoAlvo);
                                } else {
                                    System.out.println("Opção inválida.");
                                }
                            }
                            else if(op == 0) menuP = false;
                        }
                    } else if(medLogado != null){
                        salvarLog("Login Medico: " + medLogado.nome);
                        boolean menuM = true;
                        while(menuM){
                            System.out.println("\n--- Área do Médico " + medLogado.nome + " ---");
                            System.out.println("1) Cadastrar Horário Livre");
                            System.out.println("2) Ver Agenda (Consultas Marcadas)");
                            System.out.println("3) Modificar Horário Livre");
                            System.out.println("4) Cancelar Consulta Agendada");
                            System.out.println("0) Voltar");
                            int op = scan.nextInt();
                            scan.nextLine();

                            if(op == 1) medLogado.cadastrarHorario();
                            else if(op == 2) medLogado.listarConsultas();
                            else if(op == 3) medLogado.modificarHorario();
                            else if(op == 4) medLogado.cancelarConsulta();
                            else if(op == 0) menuM = false;
                        }
                    } else {
                        System.out.println("Login incorreto.");
                    }
                    break;
                case 4:
                    ini = false;
                    break;
                default:
                    System.out.println("Comando inválido");
            }
        }
    }
}
