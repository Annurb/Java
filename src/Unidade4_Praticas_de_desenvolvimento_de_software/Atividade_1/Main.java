package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Composite
public class Main {
    public static void main(String[] args) {
        Produto notebook = new Produto("Notebook", 3500.00, 1800);
        Produto teclado = new Produto("Teclado", 180.00, 700);
        Produto mouse = new Produto("Mouse", 90.00, 200);
        Produto monitor = new Produto("Monitor", 1200.00, 3200);

        KitPromocional kitHomeOffice = new KitPromocional("Kit Home Office", 10.0);


        KitPromocional kitEscritorioCompleto = new KitPromocional("Kit Escritório Completo", 5.0);

        kitEscritorioCompleto.adicionarItem(kitHomeOffice);

        kitEscritorioCompleto.adicionarItem(monitor);



        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adicionarItem(kitEscritorioCompleto);
        carrinho.getDescricao("oi");

        /*
         * Novo requisito:
         *
         * A loja agora quer criar um "Kit Escritório Completo" contendo:
         * - o Kit Home Office;
         * - o Monitor.
         *
         * A implementação atual não permite isso diretamente, pois KitPromocional
         * armazena apenas Produto. Para resolver corretamente, refatore o projeto
         * aplicando o padrão Composite.
         */
    }
}
interface Component{
    public String getNome() ;
    public double calcularPreco();
    public double calcularPesoGramas();
    public String getDescricao(String recuo);
}
class Produto implements Component{
    private final String nome;
    private final double preco;
    private final double pesoGramas;

    public Produto(String nome, double preco, double pesoGramas) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (pesoGramas < 0) {
            throw new IllegalArgumentException("O peso não pode ser negativo.");
        }

        this.nome = nome;
        this.preco = preco;
        this.pesoGramas = pesoGramas;
    }

    public String getNome() {
        return nome;
    }

    public double calcularPreco() {
        return preco;
    }

    public double calcularPesoGramas() {
        return pesoGramas;
    }

    public String getDescricao(String recuo) {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}

class KitPromocional implements Component{
    private final String nome;
    private final double percentualDesconto;
    private final List<Component> produtos = new ArrayList<>();

    public KitPromocional(String nome, double percentualDesconto) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do kit não pode ser vazio.");
        }
        if (percentualDesconto < 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("O desconto deve estar entre 0 e 100.");
        }

        this.nome = nome;
        this.percentualDesconto = percentualDesconto;
    }

    public String getNome() {
        return nome;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void adicionarItem(Component produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        produtos.add(produto);
    }

    public List<Component> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public double calcularPreco() {
        double total = 0.0;

        for (Component produto : produtos) {
            total += produto.calcularPreco();
        }

        return total * (1.0 - percentualDesconto / 100.0);
    }

    public double calcularPesoGramas() {
        double total = 0.0;

        for (Component produto : produtos) {
            total += produto.calcularPesoGramas();
        }

        return total;
    }

    public String getDescricao(String recuo) {
        StringBuilder descricao = new StringBuilder();

        descricao.append(nome)
                .append(" (")
                .append(String.format("%.1f", percentualDesconto))
                .append("% de desconto)")
                .append(System.lineSeparator());

        for (Component produto : produtos) {
            descricao.append("  - ")
                    .append(produto.getDescricao("Ola"))
                    .append(System.lineSeparator());
        }

        return descricao.toString();
    }
}

class CarrinhoCompra {
    private final List<Component> produtos = new ArrayList<>();
    public void adicionarItem(Component produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        produtos.add(produto);
    }
    public List getItens(){
        return produtos;
    }

    public double calcularPrecoTotal() {
        double total = 0.0;

        for (Component produto : produtos) {
            total += produto.calcularPreco();
        }
        return total;
    }

    public double calcularPesoTotal() {
        double total = 0.0;

        for (Component produto : produtos) {
            total += produto.calcularPesoGramas();
        }


        return total;
    }

    public String getDescricao(String recuo) {
        StringBuilder descricao = new StringBuilder();

        for (Component produto : produtos) {
            descricao.append(produto.getDescricao("Ola"))
                    .append(System.lineSeparator());
        }

        return descricao.toString();
    }
}