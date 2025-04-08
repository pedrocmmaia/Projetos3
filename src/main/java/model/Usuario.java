package model;

public class Usuario {
    public int id;   
    public String nome;
    public String email;
    public String senha;
    public String telefone;
    public TipoUsario tipoUsario;

    public enum TipoUsario{
        SINDICO,
        MORADOR,
        ADMINISTRADOR
    }

    public Usuario(){

    }


    public Usuario(String nome, String email, String senha,String telefone, TipoUsario tipoUsario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsario = tipoUsario;

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

    public TipoUsario getTipoUsario(){
        return tipoUsario;
    }

    public void setTipoUsario(TipoUsario tipoUsario){
        this.tipoUsario = tipoUsario;
    }

    @Override
    public String toString() {
        return  "Usuário{" +
                "nome=" + nome +
                ", email=" + email +
                ", telefone=" + telefone +
                ", tipo=" + tipoUsario +
                "}";
    }
    
}
