import java.util.Scanner;
import java.util.ArrayList;

public class Paciente extends Pessoa implements PermPacientes {
    public ArrayList<String> consultas = new ArrayList<>();

    public Paciente(String nome, String senha, String CPF, String tel){
        super(nome, senha, CPF, tel);
    }

    public void iniciarAgendamento(ArrayList<Medico> medicos){
        Scanner scan = new Scanner(System.in);
        System.out.println("Médicos disponíveis:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + ". " + medicos.get(i).nome);
        }
        int escolha = scan.nextInt();
        scan.nextLine();

        if(escolha > 0 && escolha <= medicos.size()){
            Medico med = medicos.get(escolha - 1);
            if(med.horarios.isEmpty()){
                System.out.println("Sem horários livres.");
                return;
            }
            for (String h : med.horarios) System.out.println(h);

            System.out.println("Digite o horário desejado (igual a lista): ");
            String dataHora = scan.nextLine();

            if (med.receberAgendamento(dataHora, this.nome)) {
                ProjetoERP.salvarLog("Paciente " + this.nome + " agendou com " + med.nome);
                consultas.add(dataHora + " com o médico " + med.nome);
                System.out.println("Agendado!");
            } else {
                System.out.println("Horário indisponível.");
            }
        }
    }

    @Override
    public void agendarConsulta(String dataHora) {}

    @Override
    public void listarConsultas() {
        System.out.println("Minhas Consultas:");
        for (String c : consultas) System.out.println(c);
    }

    @Override
    public void cancelarConsulta(String dataHora, Medico med) {
        String termoBusca = dataHora + " com o médico " + med.nome;

        if (consultas.remove(termoBusca)) {
            med.restaurarHorario(dataHora, this.nome);
            ProjetoERP.salvarLog("Paciente " + this.nome + " cancelou consulta de " + dataHora);
            System.out.println("Cancelado com sucesso.");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }
}
