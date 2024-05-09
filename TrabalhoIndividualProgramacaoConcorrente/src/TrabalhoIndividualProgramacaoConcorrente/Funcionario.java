package TrabalhoIndividualProgramacaoConcorrente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Funcionario extends Thread {
    private final Lock lock = new ReentrantLock();
    private Conta salarioLoja;
    private Conta investimento;
    private final double salario = 1400;
    private final double investimentoPercent = 0.2;

    public Funcionario(Conta salarioLoja, Conta investimento) {
        this.salarioLoja = salarioLoja;
        this.investimento = investimento;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Simulando período de trabalho
                receberSalario();
                investir();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void receberSalario() {
        lock.lock();
        try {
            salarioLoja.debitar(salario);
        } finally {
            lock.unlock();
        }
    }

    private void investir() {
        lock.lock();
        try {
            double valorInvestimento = salario * investimentoPercent;
            investimento.creditar(valorInvestimento);
            System.out.println("Funcionário investiu " + valorInvestimento + " em sua conta de investimentos.");
        } finally {
            lock.unlock();
        }
    }
}