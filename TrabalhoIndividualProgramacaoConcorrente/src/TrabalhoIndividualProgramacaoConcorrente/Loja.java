package TrabalhoIndividualProgramacaoConcorrente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja {
    private final Lock lock = new ReentrantLock();
    private Conta conta;
    private final double salarioFuncionario = 1400;

    public Loja() {
        this.conta = new Conta();
    }

    public Conta getConta() {
        return conta;
    }

    public void receberPagamento(double valor) {
        lock.lock();
        try {
            conta.creditar(valor);
            System.out.println("Pagamento recebido pela loja: " + valor);
            pagarFuncionarios();
        } finally {
            lock.unlock();
        }
    }

    private void pagarFuncionarios() {
        lock.lock();
        try {
            if (conta.getSaldo() >= salarioFuncionario) {
                conta.debitar(salarioFuncionario);
                System.out.println("Pagamento de salários realizado.");
            }
        } finally {
            lock.unlock();
        }
    }
}
