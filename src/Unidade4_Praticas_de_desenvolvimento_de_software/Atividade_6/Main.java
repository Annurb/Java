package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_6;

// Padrao bridge
public class Main {
    public static void main(String[] args) {

        /*
         * Novo requisito:
         *
         * A plataforma agora quer adicionar:
         * - novos canais de envio: WhatsApp e push notification;
         * - novos tipos de notificação: promocional, segurança e recuperação de senha.
         *
         * A implementação atual não é adequada para isso, pois cada nova combinação
         * entre tipo de notificação e canal de envio tende a exigir uma nova classe.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para permitir
         * que tipos de notificação e canais de envio variem independentemente.
         */
        Cliente cliente = new Cliente("Ana", "ana@exemplo.com", "48999990000");



        CanalEnvio canal = new CanalEmail();

        Notificacao notificacao = new NotificacaoUrgente(canal);



        notificacao.enviar(cliente, "Pagamento recusado. Atualize seus dados de pagamento.");
    }
}

/**
 * Representa um cliente que pode receber notificações.
 */
abstract class Notificacao{
    protected CanalEnvio canalEnvio;
    public Notificacao(CanalEnvio canalEnvio){
        this.canalEnvio = canalEnvio;

    }
    public void enviar(Cliente cliente, String mensagem){
        if (cliente == null || mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Cliente ou mensagem inválidos.");
        }
        String assunto = gerarAssunto();
        String corpo = gerarCorpo(cliente, mensagem);
        canalEnvio.enviar(cliente, assunto, corpo);
    }
    abstract protected String gerarAssunto();
    abstract protected String gerarCorpo(Cliente cliente, String mensagem);
}
class NotificacaoUrgente extends Notificacao{
    NotificacaoUrgente(CanalEnvio canalEnvio){
        super(canalEnvio);
    }
    protected String gerarAssunto(){
        return "URGENTE";
    }
    protected String gerarCorpo(Cliente cliente, String mensagem){
        return String.format("ATENÇÃO, %s! %s", cliente.getNome(), mensagem);
    }
}
class NotificacaoSimples extends Notificacao{
    NotificacaoSimples(CanalEnvio canalEnvio){
        super(canalEnvio);
    }
    protected String gerarAssunto(){
        return "Notificação";
    }
    protected String gerarCorpo(Cliente cliente, String mensagem){
        return String.format("Olá, %s, %s. %s", cliente.getNome(), mensagem);
    }
}
interface CanalEnvio{
public void enviar(Cliente cliente, String mensagem, String corpo);
}
class CanalEmail implements CanalEnvio{
    public void enviar(Cliente cliente, String assunto, String corpo){
        System.out.printf(
                "[EMAIL] Para: %s <%s>%nAssunto:%s%nMensagem:  %s%n",
                cliente.getNome(),
                cliente.getEmail(),
                assunto, corpo
        );
    }
}
class CanalSms implements CanalEnvio{
    public void enviar(Cliente cliente, String assunto, String corpo){
        System.out.printf(
                "[SMS] Para: %s%nMensagem: %s: %s%n",
                cliente.getTelefone(),
                assunto, corpo
        );
    }
}
class Cliente {
    private final String nome;
    private final String email;
    private final String telefone;

    /**
     * Cria um cliente.
     *
     * @param nome nome do cliente.
     * @param email endereço de e-mail do cliente.
     * @param telefone número de telefone do cliente.
     */
    public Cliente(String nome, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O e-mail do cliente não pode ser vazio.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("O telefone do cliente não pode ser vazio.");
        }

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
