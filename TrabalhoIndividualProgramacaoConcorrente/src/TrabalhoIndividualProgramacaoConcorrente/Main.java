package TrabalhoIndividualProgramacaoConcorrente;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Loja loja1 = new Loja();
        Loja loja2 = new Loja();
        Conta contaInvestimento = new Conta();

        // Cria e inicia as threads dos funcionários
        for (int i = 0; i < 2; i++) {
            Conta contaFuncionario1 = new Conta(); // Cria uma nova conta para o funcionário 1
            Conta contaFuncionario2 = new Conta(); // Cria uma nova conta para o funcionário 2
            Funcionario funcionario1 = new Funcionario(loja1.getConta(), contaFuncionario1);
            Funcionario funcionario2 = new Funcionario(loja2.getConta(), contaFuncionario2);
            funcionario1.start();
            funcionario2.start();
        }

        // Cria e inicia as threads dos clientes
        for (int i = 0; i < 5; i++) {
            Conta contaCliente = new Conta(); // Cria uma nova conta para o cliente
            Cliente cliente = new Cliente(contaCliente);
            cliente.start();
        }
    }
}