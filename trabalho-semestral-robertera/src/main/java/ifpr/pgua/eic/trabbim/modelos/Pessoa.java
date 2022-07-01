package ifpr.pgua.eic.trabbim.modelos;

import java.io.Serializable;

public class Pessoa implements Serializable{
    String cpf;
    String nome;
    String email;
    String telefone;

    public Pessoa(String cpf, String nome, String email, String telefone) {
        this.setCpf(cpf);
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome(){
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

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String toString(){
        return nome;
    }

    public String paraTexto(){
        return "Nome: "+nome+"; Cpf: "+cpf+"; Email: "+email+"; Telefone: "+telefone;
    }

}
