package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_7;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // A nova implementação deve ser capaz de fazer:
        Usuario usuario = new Usuario("Ana", PerfilUsuario.GERENTE);
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroControlado(usuario);
        PainelAdministrativo painel = new PainelAdministrativo(usuario, relatorio);

        painel.abrir();

        painel.exibirRelatorioDetalhado();

        /*
         * Novo requisito:
         *
         * A loja agora quer adicionar novos relatórios restritos:
         * - relatório fiscal;
         * - relatório de repasses;
         * - relatório antifraude;
         * - relatório de comissões.
         *
         * Esses relatórios também são caros de criar e possuem regras de acesso.
         *
         * A implementação atual não é adequada para isso, pois PainelAdministrativo
         * cria diretamente o relatório real e concentra verificações explícitas de
         * permissão antes de chamar suas operações.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para permitir
         * que o painel trabalhe com uma abstração de relatório, enquanto outro objeto
         * controla o acesso e adia a criação do relatório real até que ele seja necessário.
         */
    }
}

/**
 * Representa os perfis de usuário existentes no painel administrativo.
 */
enum PerfilUsuario {
    ATENDENTE,
    GERENTE,
    ADMINISTRADOR
}

/**
 * Representa um usuário autenticado no painel administrativo.
 */
class Usuario {
    private final String nome;
    private final PerfilUsuario perfil;

    /**
     * Cria um usmentação atual separa produtos simples e kits promocionais. uário.
     *
     * @param nome nome do usuário.
     * @param perfil perfil de acesso do usuário.
     */
    public Usuario(String nome, PerfilUsuario perfil) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }
        if (perfil == null) {
            throw new IllegalArgumentException("O perfil não pode ser nulo.");
        }

        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public boolean podeAcessarRelatorioFinanceiro() {
        return perfil == PerfilUsuario.GERENTE || perfil == PerfilUsuario.ADMINISTRADOR;
    }
}

/**
 * Representa o relatório financeiro real.
 *
 * <p>
 * Esta classe simula um objeto caro de criar. A criação carrega dados financeiros,
 * monta linhas de relatório e prepara exportação.
 * </p>
 */
interface RelatorioFinanceiro {
    String gerarResumo();
    String gerarDetalhado();
    String exportarCsv();

}

class RelatorioFinanceiroControlado implements RelatorioFinanceiro{
    Usuario usuario;
    RelatorioFinanceiroDetalhado relatorioReal;
    RelatorioFinanceiroControlado(Usuario usuario){
        this.usuario = usuario;
    }
    public String gerarResumo(){
        if (!usuario.podeAcessarRelatorioFinanceiro()) {
            return "[ACESSO NEGADO] Usuário sem permissão para resumo financeiro.";
        }

        return obterRelatorioReal().gerarResumo();
    }
    public String gerarDetalhado(){
        if (!usuario.podeAcessarRelatorioFinanceiro()) {
            return "[ACESSO NEGADO] Usuário sem permissão para relatorio financeiro detalhado.";
        }

        return obterRelatorioReal().gerarDetalhado();
    }
    public String exportarCsv(){
        if (!usuario.podeAcessarRelatorioFinanceiro()) {
            return "[ACESSO NEGADO] Usuário sem permissão para exportar csv.";
        }

        return obterRelatorioReal().exportarCsv();
    }
    public RelatorioFinanceiroDetalhado obterRelatorioReal(){
        if (this.relatorioReal == null) {
            this.relatorioReal = new RelatorioFinanceiroDetalhado();
        }

        return this.relatorioReal;
    }
}

class RelatorioFinanceiroDetalhado implements RelatorioFinanceiro{
    private final List<String> linhas = new ArrayList<>();

    /**
     * Cria e carrega o relatório financeiro detalhado.
     */
    public RelatorioFinanceiroDetalhado() {
        System.out.println("[RELATÓRIO] Carregando dados financeiros detalhados...");
        simularCargaPesada();

        linhas.add("Faturamento bruto: R$ 125000.00");
        linhas.add("Taxas de pagamento: R$ 3850.00");
        linhas.add("Custo de produtos vendidos: R$ 67200.00");
        linhas.add("Lucro operacional estimado: R$ 53950.00");

        System.out.println("[RELATÓRIO] Relatório financeiro carregado.");
    }

    /**
     * Gera um resumo financeiro.
     *
     * @return resumo do relatório.
     */
    public String gerarResumo() {
        return "Resumo financeiro: faturamento bruto de R$ 125000.00.";
    }

    /**
     * Gera a versão detalhada do relatório financeiro.
     *
     * @return relatório detalhado.
     */
    public String gerarDetalhado() {
        StringBuilder conteudo = new StringBuilder();

        conteudo.append("Relatório financeiro detalhado")
                .append(System.lineSeparator());

        for (String linha : linhas) {
            conteudo.append("- ")
                    .append(linha)
                    .append(System.lineSeparator());
        }

        return conteudo.toString();
    }

    /**
     * Exporta o relatório em formato CSV.
     *
     * @return conteúdo CSV.
     */
    public String exportarCsv() {
        StringBuilder csv = new StringBuilder();

        csv.append("descricao;valor")
                .append(System.lineSeparator());

        csv.append("Faturamento bruto;125000.00")
                .append(System.lineSeparator());
        csv.append("Taxas de pagamento;3850.00")
                .append(System.lineSeparator());
        csv.append("Custo de produtos vendidos;67200.00")
                .append(System.lineSeparator());
        csv.append("Lucro operacional estimado;53950.00")
                .append(System.lineSeparator());

        return csv.toString();
    }

    private void simularCargaPesada() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Carga do relatório interrompida.", exception);
        }
    }
}

/**
 * Representa o painel administrativo da loja.
 *
 * <p>
 * Esta versão inicial cria diretamente o relatório financeiro detalhado e possui
 * verificações explícitas de permissão. Esse acoplamento é intencional neste código
 * inicial, pois representa o problema de projeto a ser refatorado.
 * </p>
 */
class PainelAdministrativo {
    private final Usuario usuario;
    private final RelatorioFinanceiro relatorio;

    /**
     * Cria o painel administrativo para um usuário.
     *
     * @param usuario usuário autenticado.
     */
    public PainelAdministrativo(Usuario usuario, RelatorioFinanceiro relatorio) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }

        this.usuario = usuario;
        this.relatorio = relatorio;
    }

    /**
     * Abre o painel administrativo.
     */
    public void abrir() {
        System.out.printf("Painel aberto para %s (%s).%n", usuario.getNome(), usuario.getPerfil());
    }

    /**
     * Exibe um resumo financeiro.
     */
    public void exibirResumoFinanceiro() {

        System.out.println(relatorio.gerarResumo());
    }

    /**
     * Exibe o relatório financeiro detalhado.
     */
    public void exibirRelatorioDetalhado() {

        System.out.println(relatorio.gerarDetalhado());
    }

    /**
     * Exporta o relatório financeiro em CSV.
     */
    public void exportarRelatorioCsv() {

        System.out.println(relatorio.exportarCsv());
    }
}