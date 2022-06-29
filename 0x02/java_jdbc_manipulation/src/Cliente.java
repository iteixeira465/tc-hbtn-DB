import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String rg;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, Integer idade, String cpf, String rg) {
        setId(id);
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
        setRg(rg);
    }

    public Cliente(String nome, Integer idade, String cpf, String rg) {
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
        setRg(rg);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String CPF) {
        this.cpf = CPF;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String RG) {
        this.rg = RG;
    }

    @Override
    public String toString() {
        return "{id: " + getId() + ", nome: " + getNome() + ", idade: " + getIdade() + ", cpf: " + getCpf() + ", rg: " + getRg() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(idade, cliente.idade) && Objects.equals(cpf, cliente.cpf) && Objects.equals(rg, cliente.rg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, cpf, rg);
    }

}
