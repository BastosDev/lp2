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
        System.out.println("Digite data e horário (Ex: 20/10 14h): ");
        String txt = scan.nextLine();
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

    public void restaurarHorario(String dataHora, String nomePaciente) {
        String consultaFormatada = dataHora + " - Paciente: " + nomePaciente;
        if(consultasMarcadas.remove(consultaFormatada)){
            horarios.add(dataHora);
            System.out.println("Agenda atualizada: horário liberado.");
        }
    }

    @Override
    public void modificarHorario() {
        System.out.println("Horários livres:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ") " + horarios.get(i));
        }
        System.out.println("Número do horário para alterar: ");
        int indice = scan.nextInt();
        scan.nextLine();
        if (indice > 0 && indice <= horarios.size()) {
            System.out.println("Digite o novo horário: ");
            String novo = scan.nextLine();
            String antigo = horarios.set(indice - 1, novo);
            ProjetoERP.salvarLog("Medico " + this.nome + " alterou " + antigo + " para " + novo);
            System.out.println("Horário modificado.");
        }
    }

    @Override
    public void cancelarConsulta() {
        if (consultasMarcadas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (int i = 0; i < consultasMarcadas.size(); i++) {
            System.out.println((i + 1) + ") " + consultasMarcadas.get(i));
        }
        System.out.println("Número para cancelar:");
        int esc = scan.nextInt();
        scan.nextLine();

        if (esc > 0 && esc <= consultasMarcadas.size()) {
            String removida = consultasMarcadas.remove(esc - 1);
            String[] partes = removida.split(" - ");
            horarios.add(partes[0]);
            ProjetoERP.salvarLog("Medico " + this.nome + " cancelou: " + removida);
            System.out.println("Consulta cancelada.");
        }
    }

    @Override
    public void listarConsultas() {
        System.out.println("Agenda (" + this.nome + "):");
        for (String consulta : consultasMarcadas) {
            System.out.println(consulta);
        }
    }
}
