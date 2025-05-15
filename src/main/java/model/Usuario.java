package model;

public class Usuario {
    public Integer id;
    public String nome;
    public String email;
    public String senha;
    public String telefone;
    public TipoUsuario tipoUsuario;



    public enum TipoUsuario{
        SINDICO,
        MORADOR,
        ADMINISTRADOR;

        public static TipoUsuario fromString(String tipo) {
            tipo = tipo.toUpperCase();
            if (tipo.contains("SINDICO")) return SINDICO;
            if (tipo.contains("MORADOR")) return MORADOR;
            if (tipo.contains("ADMINISTRADOR")) return ADMINISTRADOR;
            throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
        }
    }

    public Usuario(){

    }


    public Usuario(String nome, String email, String senha,String telefone, TipoUsuario tipoUsuario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;

    }

    public Usuario(int id, String nome, String email, String senha, String telefone, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = TipoUsuario.valueOf(tipoUsuario);
    }

    public Integer getId(){
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

    public TipoUsuario getTipoUsario(){
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return  "Usuário{" +
                "nome=" + nome +
                ", email=" + email +
                ", telefone=" + telefone +
                ", tipo=" + tipoUsuario +
                "}";
    }
    
}
