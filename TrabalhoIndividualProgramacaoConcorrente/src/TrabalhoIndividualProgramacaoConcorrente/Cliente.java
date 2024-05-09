package TrabalhoIndividualProgramacaoConcorrente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Cliente extends Thread {
    private final Lock lock = new ReentrantLock();
    private Conta conta;
    private final double[] compras = {100, 200};
    private int lojaAtual = 0;

    public Cliente(Conta conta) {
        this.conta = conta;
    }

    public void run() {
        while (conta.getSaldo() > 0) {
            try {
                Thread.sleep(1000); // Simulando intervalo entre compras
                realizarCompra();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void realizarCompra() {
        lock.lock();
        try {
            double valorCompra = compras[lojaAtual];
            if (conta.getSaldo() >= valorCompra) {
                conta.debitar(valorCompra);
                System.out.println("Cliente realizou uma compra de " + valorCompra);
                lojaAtual = (lojaAtual + 1) % 2; // Alternar entre lojas
            } else {
                System.out.println("Saldo insuficiente para realizar a compra.");
            }
        } finally {
            lock.unlock();
        }
    }
}
