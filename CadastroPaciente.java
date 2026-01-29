import java.util.Scanner;

public class CadastroPaciente {

    public Paciente cadastro() {
        String nome, senha, CPF, tel, conSenha, respS;
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Digite o seu nome: ");
            nome = scan.nextLine();
            System.out.println("Este nome está correto? S/N");
            respS = scan.nextLine();
            if (respS.equalsIgnoreCase("S")) {
                break;
            } else {
                System.out.println("Tente novamente.");
            }
        }
        while (true) {
            System.out.println("Digite o seu CPF (apenas número): ");
            CPF = scan.nextLine();
            if (CPF.length() == 11) {
                break;
            } else {
                System.out.println("CPF inválido, número de digitos não confere.");
            }
        }
        while (true) {
            System.out.println("Digite o seu número de telefone (Ex: 9--------");
            tel = scan.nextLine();
            if(tel.length()==9){
                break;
            } else {
                System.out.println("Número inválido, tente novamente.");
            }
        }
        while (true) {
            System.out.println("Digite uma senha");
            senha = scan.nextLine();
            System.out.println("Digite a senha novamente para confirmar: ");
            conSenha = scan.nextLine();
            if (senha.equals(conSenha)) {
                break;
            } else {
                System.out.println("As senhas não correspondem, tente novamente.");
            }
        }
        return new Paciente(nome, senha, CPF, tel);
    }
}
