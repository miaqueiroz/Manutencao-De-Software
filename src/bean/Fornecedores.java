package bean;

/**
 *
 * @author gabri
 */
public class Fornecedores {
    String nome;
    String fone;
    String endereco;
    String cnpj;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Fornecedores{" + "nome=" + nome + ", fone=" + fone + ", endereco=" + endereco + ", cnpj=" + cnpj + '}';
    }

}
