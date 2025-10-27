package conectecare.model.vo;

abstract class PessoaVo {
    private int id;
    private String nome;
    private String cpf;
    private int idade;
    private String email;
    private String telefoneContato;

    public PessoaVo(String nome, String cpf, int idade, String email, String telefoneContato) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.telefoneContato = telefoneContato;
    }

    public PessoaVo(int id, String nome, String cpf, int idade, String email, String telefoneContato) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.telefoneContato = telefoneContato;
    }

    public PessoaVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }
}

