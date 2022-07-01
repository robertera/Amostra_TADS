package ifpr.pgua.eic.trabbim.modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    
    int codigo, cargaHoraria;
    String nome, descricao;
    Professor professor;
    ArrayList<Aluno> alunos;


    public Curso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public Professor getProfessor(){
        return professor;
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
    }

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getCargaHoraria(){
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria){
        this.cargaHoraria = cargaHoraria;
    }

    public boolean matricula(Aluno aluno){
        for(Aluno a:alunos){
            if(a.getCpf().equals(aluno.getCpf())){
                return false;
            }
        }
        alunos.add(aluno);
        return true;
    }

    public boolean desmatricula(String cpf){
        for(Aluno a:alunos){
            if(a.getCpf().equals(cpf))
            alunos.remove(a);
            return true;
        }
        return false;

    }

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos){
        this.alunos = alunos;
    }


    public String toString(){
        return nome;
    }

    public String paraTexto(){
        return "Nome: " +nome+"; Codigo: "+codigo+"; Descricao: "+descricao+"; Carga-Horaria: "+cargaHoraria;
    }

    
    
   
}
