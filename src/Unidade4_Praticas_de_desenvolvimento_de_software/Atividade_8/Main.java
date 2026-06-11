package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_8;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DocumentoProposta proposta = new DocumentoProposta(

                "Proposta inicial",

                "Fornecimento de equipamentos para escritório.",

                5.0,

                "Cliente solicitou prazo estendido."

        );



        HistoricoDocumento historico = new HistoricoDocumento();



        historico.salvar(proposta);

        proposta.aplicarDesconto(12.0);

        proposta.alterarCorpo("Texto revisado com novas condições comerciais.");



        historico.desfazer(proposta);

        /*
         * Novo requisito:
         *
         * O documento de proposta deve passar a conter novos dados internos:
         * - validade da proposta;
         * - moeda;
         * - condição de pagamento;
         * - lista de itens negociados;
         * - anexos internos.
         *
         * A implementação atual não é adequada para isso, pois HistoricoDocumento
         * conhece explicitamente os campos internos de DocumentoProposta e precisará
         * ser alterado sempre que a estrutura interna do documento mudar.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para permitir
         * que o histórico armazene estados anteriores sem examinar seus detalhes
         * internos e sem violar o encapsulamento do documento.
         */
    }
}

/**
 * Representa uma proposta comercial editável.
 *
 * <p>
 * Esta versão inicial expõe explicitamente partes do seu estado interno para que
 * outra classe possa salvar e restaurar versões anteriores. Essa exposição é
 * intencional neste código inicial, pois representa o problema de projeto a ser
 * refatorado.
 * </p>
 */
class DocumentoProposta {
    private String titulo;
    private String corpo;
    private double percentualDesconto;
    private String observacoesInternas;

    /**
     * Cria um documento de proposta.
     *
     * @param titulo título da proposta.
     * @param corpo corpo textual da proposta.
     * @param percentualDesconto percentual de desconto aplicado.
     * @param observacoesInternas observações internas da equipe comercial.
     */
    public DocumentoProposta(String titulo, String corpo, double percentualDesconto, String observacoesInternas) {
        validarTextoObrigatorio(titulo, "O título não pode ser vazio.");
        validarTextoObrigatorio(corpo, "O corpo não pode ser vazio.");
        validarDesconto(percentualDesconto);

        this.titulo = titulo;
        this.corpo = corpo;
        this.percentualDesconto = percentualDesconto;
        this.observacoesInternas = observacoesInternas == null ? "" : observacoesInternas;
    }

    public void alterarTitulo(String titulo) {
        validarTextoObrigatorio(titulo, "O título não pode ser vazio.");
        this.titulo = titulo;
    }

    public void alterarCorpo(String corpo) {
        validarTextoObrigatorio(corpo, "O corpo não pode ser vazio.");
        this.corpo = corpo;
    }

    public void aplicarDesconto(double percentualDesconto) {
        validarDesconto(percentualDesconto);
        this.percentualDesconto = percentualDesconto;
    }

    public void alterarObservacoesInternas(String observacoesInternas) {
        this.observacoesInternas = observacoesInternas == null ? "" : observacoesInternas;
    }

    /**
     * Restaura o estado do documento campo a campo.
     *
     * <p>
     * Este método evidencia o problema da versão inicial: outra classe precisa
     * conhecer os campos que compõem o estado interno do documento.
     * </p>
     *
     */
    public void restaurar(
            EstadoDocumento estado
    ) {
        validarTextoObrigatorio(titulo, "O título não pode ser vazio.");
        validarTextoObrigatorio(corpo, "O corpo não pode ser vazio.");
        validarDesconto(percentualDesconto);
        if(estado == null ) return;
        this.titulo = estado.getTitulo();
        this.corpo = estado.getCorpo();
        this.percentualDesconto = estado.getPercentualDesconto();
        this.observacoesInternas = observacoesInternas == null ? "" : estado.getObservacoesInternas();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public String getObservacoesInternas() {
        return observacoesInternas;
    }

    /**
     * Renderiza uma versão textual simples da proposta.
     *
     * @return proposta formatada.
     */
    public String renderizar() {
        return "Título: " + titulo + System.lineSeparator()
                + "Corpo: " + corpo + System.lineSeparator()
                + "Desconto: " + String.format("%.1f%%", percentualDesconto) + System.lineSeparator()
                + "Observações internas: " + observacoesInternas + System.lineSeparator();
    }

    private void validarTextoObrigatorio(String valor, String mensagemErro) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }

    private void validarDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("O desconto deve estar entre 0 e 100.");
        }
    }
    public EstadoDocumento criarEstado(){
        return new EstadoDocumento(
                this.titulo,
                this.corpo,
                this.percentualDesconto,
                this.observacoesInternas
        );
    }
    static class EstadoDocumento {
        private final String titulo;
        private final String corpo;
        private final double percentualDesconto;
        private final String observacoesInternas;

        /**
         * Cria um registro de estado.
         *
         * @param titulo título salvo.
         * @param corpo corpo salvo.
         * @param percentualDesconto desconto salvo.
         * @param observacoesInternas observações internas salvas.
         */
        public EstadoDocumento(
                String titulo,
                String corpo,
                double percentualDesconto,
                String observacoesInternas
        ) {
            this.titulo = titulo;
            this.corpo = corpo;
            this.percentualDesconto = percentualDesconto;
            this.observacoesInternas = observacoesInternas;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getCorpo() {
            return corpo;
        }

        public double getPercentualDesconto() {
            return percentualDesconto;
        }

        public String getObservacoesInternas() {
            return observacoesInternas;
        }
    }
}

/**
 * Representa uma cópia explícita dos campos internos de DocumentoProposta.
 */


/**
 * Guarda versões anteriores do documento.
 *
 * <p>
 * Esta versão inicial conhece explicitamente os campos internos do documento.
 * Esse acoplamento é intencional neste código inicial.
 * </p>
 */
class HistoricoDocumento {
    //modificacao para deque
    private final Deque<DocumentoProposta.EstadoDocumento> estados = new ArrayDeque<>();
    /**
     * Salva o estado atual do documento.
     *
     * @param documento documento cujo estado será salvo.
     */
    public void salvar(DocumentoProposta documento) {
        if (documento == null) {
            throw new IllegalArgumentException("O documento não pode ser nulo.");
        }

        estados.push(documento.criarEstado());
    }

    /**
     * Restaura o último estado salvo do documento.
     *
     * @param documento documento que será restaurado.
     */
    public void desfazer(DocumentoProposta documento) {
        if (documento == null) {
            throw new IllegalArgumentException("O documento não pode ser nulo.");
        }
        if (estados.isEmpty()) {
            throw new IllegalStateException("Não há estados salvos para desfazer.");
        }
        DocumentoProposta.EstadoDocumento estadoAnterior = estados.pop();
        documento.restaurar(estadoAnterior);
    }

    public int quantidadeEstadosSalvos() {
        return estados.size();
    }
}