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
            Medico m = medicos.get(i);
            System.out.println((i + 1) + ". " + m.nome);
        }

        System.out.println("Escolha um médico pelo número: ");
        int escolha = scan.nextInt();

        if(escolha > 0 && escolha <= medicos.size()){
            Medico medicoEscolhido = medicos.get(escolha - 1);

            System.out.println("Horários disponíveis do médico " + medicoEscolhido.nome + " :");
            for (String horario : medicoEscolhido.horarios) {
                System.out.println(horario);
            }

            System.out.println("Digite o horário desejado (igual está na lista): ");
            String dataHora = scan.next();

            if (medicoEscolhido.receberAgendamento(dataHora, this.nome)) {
                ProjetoERP.salvarLog("Paciente " + this.nome + " agendou com " + medicoEscolhido.nome + " para " + dataHora);
                System.out.println("Consulta agendada para " + dataHora + " com o médico " + medicoEscolhido.nome);
                consultas.add(dataHora + " com o médico " + medicoEscolhido.nome);
            } else {
                System.out.println("Horário indisponível ou inválido.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    @Override
    public void agendarConsulta(String dataHora) {
    }

    @Override
    public void listarConsultas() {
        System.out.println("Minhas Consultas:");
        for (String consulta : consultas) {
            System.out.println(consulta);
        }
    }

    @Override
    public void cancelarConsulta(String dataHora, Medico medico) {
        String consulta = dataHora + " com o médico " + medico.nome;
        if (consultas.remove(consulta)) {
            ProjetoERP.salvarLog("Paciente " + this.nome + " cancelou consulta de " + dataHora);
            System.out.println("Consulta cancelada.");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }
}
