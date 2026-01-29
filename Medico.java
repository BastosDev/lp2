import java.util.ArrayList;
import java.util.Scanner;

public class Medico extends Pessoa implements PermMedicos {
    public ArrayList<String> horarios = new ArrayList<>();
    public ArrayList<String> consultasMarcadas = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Medico(String nome, String senha, String CPF, String tel){
        super(nome, senha, CPF, tel);
    }

    public void cadastrarHorario(){
        String txt;
        System.out.println("Digite uma data e horário (dd/mm/aa hh:mm): ");
        txt = scan.next();
        horarios.add(txt);
        ProjetoERP.salvarLog("Medico " + this.nome + " cadastrou horario: " + txt);
        System.out.println("Horário cadastrado.");
    }

    public boolean receberAgendamento(String dataHora, String nomePaciente) {
        if (horarios.contains(dataHora)) {
            horarios.remove(dataHora);
            consultasMarcadas.add(dataHora + " - Paciente: " + nomePaciente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void modificarHorario() {
        System.out.println("Horários atuais do médico " + this.nome + " :");
        for (String horario : horarios) {
            System.out.println(horario);
        }
        System.out.println("Digite o horário que deseja remover (dd/mm/aa hh:mm): ");
        String horarioRemover = scan.next();
        if (horarios.remove(horarioRemover)) {
            ProjetoERP.salvarLog("Medico " + this.nome + " removeu horario: " + horarioRemover);
            System.out.println("Horário " + horarioRemover + " removido.");
        } else {
            System.out.println("Horário não encontrado.");
        }
    }

    @Override
    public void cancelarConsulta() {
    }

    @Override
    public void listarConsultas() {
        System.out.println("Agenda do Dr(a). " + this.nome + ":");
        for (String consulta : consultasMarcadas) {
            System.out.println(consulta);
        }
    }
}
