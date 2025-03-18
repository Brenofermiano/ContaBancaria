// Interface Tributavel
interface Tributavel {
    double calcularImposto(); // Método para calcular imposto
}

// Classe base ContaBancaria
class ContaBancaria {
    String titular;
    double saldo;

    ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println(titular + " depositou R$ " + valor);
        } else {
            System.out.println("Depósito inválido.");
        }
    }

    boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println(titular + " sacou R$ " + valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
            return false;
        }
    }

    boolean transferir(ContaBancaria destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println(titular + " transferiu R$ " + valor + " para " + destino.titular);
            return true;
        }
        return false;
    }

    void exibirSaldo() {
        System.out.println("Saldo de " + titular + ": R$ " + saldo);
    }
}

// Conta Corrente (cobre taxa de R$5 por transferência)
class ContaCorrente extends ContaBancaria {
    private static final double TAXA_TRANSFERENCIA = 5.0;

    ContaCorrente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    boolean transferir(ContaBancaria destino, double valor) {
        if (super.sacar(valor + TAXA_TRANSFERENCIA)) {
            destino.depositar(valor);
            System.out.println(titular + " transferiu R$ " + valor + " (com taxa de R$5) para " + destino.titular);
            return true;
        }
        return false;
    }
}

// Conta Poupança (rendimento de 0.3% ao mês)
class ContaPoupanca extends ContaBancaria {
    private static final double RENDIMENTO_MENSAL = 0.003;

    ContaPoupanca(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    void aplicarRendimento() {
        double rendimento = saldo * RENDIMENTO_MENSAL;
        saldo += rendimento;
        System.out.println("Rendimento aplicado: R$ " + rendimento);
    }
}

// Conta Investimento (com tributação)
class ContaInvestimento extends ContaBancaria implements Tributavel {
    private double taxaRendimento; // % de rendimento ao mês
    private static final double ALIQUOTA_IMPOSTO = 0.15; // 15% de imposto sobre o rendimento

    ContaInvestimento(String titular, double saldoInicial, double taxaRendimento) {
        super(titular, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    // Implementação do método da interface Tributavel
    @Override
    public double calcularImposto() {
        return saldo * taxaRendimento * ALIQUOTA_IMPOSTO;
    }

    // Aplicar rendimento e descontar imposto
    void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        double imposto = calcularImposto();
        saldo += (rendimento - imposto); // Deduzindo o imposto do saldo
        System.out.println("Rendimento aplicado: R$ " + rendimento + " | Imposto cobrado: R$ " + imposto);
    }
}