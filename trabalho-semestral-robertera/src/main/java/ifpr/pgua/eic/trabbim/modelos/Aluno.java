package ifpr.pgua.eic.trabbim.modelos;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    LocalDate dataMatricula;

    public Aluno(String cpf, String nome, String email, String telefone, LocalDate dataMatricula){
        super(cpf, nome, email, telefone);
        this.dataMatricula = dataMatricula;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String toString(){
        return nome;
    }

    public String paraTexto(){
        return super.paraTexto()+"; Data-Matricula: "+dataMatricula;
    }
}



