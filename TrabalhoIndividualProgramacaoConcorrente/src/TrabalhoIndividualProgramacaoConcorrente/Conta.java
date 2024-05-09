package TrabalhoIndividualProgramacaoConcorrente;


public class Conta {
    private final Object lock = new Object();
    private double saldo;

    public Conta() {
        this.saldo = 1000;
    }

    public double getSaldo() {
        synchronized (lock) {
            return saldo;
        }
    }

    public void creditar(double valor) {
        synchronized (lock) {
            saldo += valor;
        }
    }

    public void debitar(double valor) {
        synchronized (lock) {
            saldo -= valor;
        }
    }
}