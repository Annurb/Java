package Unidade3.MainForm2;
import java.util.ArrayList;

public class Dominio {
}

class Categoria{
    private String nome;
    private String descricao;
    private ArrayList<Categoria> subCategorias;
    private ArrayList<Produto> produtos;

    Categoria(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        subCategorias = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    void addSubCategoria(Categoria sub){
        subCategorias.add(sub);
    }
    void addProduto( Produto prod){
        produtos.add(prod);
    }
    public String toString(){
        String texto = "nome=" + nome+", descricao"+descricao;
        texto += ",produtos=[";
        for(Produto p:produtos){
            texto+=p+",";
        }
        texto+="], subcategorias=[";
        for(Categoria sub:subCategorias){
            texto+=sub.nome+",";
        }
        return texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(ArrayList<Categoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
}
class Produto{
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    private String descricao;
    private double preco;
    private Categoria categoria;

    Produto(String nome, String descricao, double preco, Categoria categoria){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria =  categoria;
    }
    public String toStrng(){
        return "nome='"+ nome+ "', descricao='"+ descricao+"', preco"+preco+",categoria="+categoria.getClass();
    }
}