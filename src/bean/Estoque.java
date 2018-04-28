package bean;

/**
 *
 * @author gabri
 */
public class Estoque {
    int id_estoque;
    int quantidade;
    int id_produto;
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    @Override
    public String toString() {
        return "Estoque{" + "id_estoque=" + id_estoque + ", quantidade=" + quantidade + ", id_produto=" + id_produto + ", nome=" + nome + '}';
    }


   
}
