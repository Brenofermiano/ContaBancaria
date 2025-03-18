// Classe principal para testes
public class Banco {
    public static void main(String[] args) {
        // Criando contas
        ContaCorrente cc = new ContaCorrente("Ana", 1000);
        ContaPoupanca cp = new ContaPoupanca("Bruno", 2000);
        ContaInvestimento ci = new ContaInvestimento("Carlos", 5000, 0.01); // 1% ao mês

        // Testando operações
        cc.depositar(500);
        cc.sacar(200);
        cc.exibirSaldo();
        System.out.println("----------------------");

        cp.aplicarRendimento();
        cp.exibirSaldo();
        System.out.println("----------------------");

        ci.aplicarRendimento();
        ci.exibirSaldo();
        System.out.println("----------------------");

        // Testando transferência com taxa
        cc.transferir(cp, 100);
        cc.exibirSaldo();
        cp.exibirSaldo();
    }
}