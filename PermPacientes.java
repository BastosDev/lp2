public interface PermPacientes {
  void agendarConsulta(String dataHora);
  void cancelarConsulta(String dataHora, Medico medico);
  void listarConsultas();
}
