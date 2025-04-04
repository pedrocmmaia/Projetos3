package model;

public class Usuario {
    public int id;   
    public String nome;
    public String email;
    public String senha;
    public String telefone;
    public Tipo tipo;

    public enum Tipo{
        Morador(1), Sindico(2);

        private int codigo;

        Tipo(int codigo){
            this.codigo = codigo;
        }

        public int getCodigo(){
            return codigo;
        }
    }


    public Usuario(int id, String nome, String email, String senha,String telefone, Tipo tipo){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String  getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public Tipo getTipo(){
        return tipo;
    }

    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "Usuário{" +
                "nome=" + nome +
                ", email=" + email +
                ", telefone=" + telefone +
                ", tipo=" + tipo +
                "}";
    }
    
}
