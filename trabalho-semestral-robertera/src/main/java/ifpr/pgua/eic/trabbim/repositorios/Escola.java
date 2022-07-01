package ifpr.pgua.eic.trabbim.repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.time.LocalDate;
import java.util.ArrayList;

import ifpr.pgua.eic.trabbim.modelos.Aluno;
import ifpr.pgua.eic.trabbim.modelos.Curso;

import ifpr.pgua.eic.trabbim.modelos.Professor;

public class Escola {

    private ArrayList<Professor> professores;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> cursos;

    private static final String NOME_ARQUIVO = "dados.txt";
    private static final String NOME_ARQUIVO_BIN = "dados.bin";
    
    public Escola(){
        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        cursos = new ArrayList<>();
    }

    //========================================================== Buscas ==========================================================

    //Busca aluno pelo cpf
    public Aluno buscaAlunoCpf(String cpf){
        return alunos.stream().filter(a -> a.getCpf().equals(cpf)).findFirst().orElseGet(()->null);
    }

    //Busca prof pelo cpf
    public Professor buscaProfessorCpf(String cpf){
        return professores.stream().filter(a -> a.getCpf().equals(cpf)).findFirst().orElseGet(()->null);
    }

    //Busca curso pelo codigo
    public Curso buscaCursoNome(String nome){
        return cursos.stream().filter(a -> a.getNome().equals(nome)).findFirst().orElseGet(()->null);
    }

    //========================================================== Cadastros ==========================================================

    //Cadastra aluno
    public boolean cadastrarAluno(String cpf, String nome, String email, String telefone, LocalDate dataMatricula){
        Aluno a = new Aluno(cpf, nome, email, telefone, dataMatricula);

        if(buscaAlunoCpf(cpf) == null){
            alunos.add(a);
            return true;
        }
        return false;
    }

    //Cadastra professor
    public boolean cadastrarProfesor(String cpf, String nome, String email, String telefone, double salario){
        Professor p = new Professor(cpf, nome, email, telefone, salario);

        if(buscaProfessorCpf(cpf) == null){
            professores.add(p);
            return true;
        }
        return false;
    }

    //Cadastrar curso
    public boolean cadastrarCurso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor){
        Curso c = new Curso(codigo, nome, descricao, cargaHoraria, professor);

        if(buscaCursoNome(nome)==null){
            cursos.add(c);
            return true;
        }
        return false;
    }


    //========================================================== listas ==========================================================
 
    //lista alunos
    public ArrayList<Aluno> listarAlunos(){
        return alunos;
    }

    //filtra alunos na lista
    public ArrayList<Aluno> filtraNomeAlunos(String aux){
        ArrayList<Aluno> retorno = new ArrayList<>();

        for(Aluno a:alunos){
            if(a.getNome().toLowerCase().startsWith(aux.toLowerCase())){
                retorno.add(a);
            }
        }
        return retorno;
    }

   //lista professores
    public ArrayList<Professor> listarProfessores(){
      return professores;
    }

    //filtra professores na lista
    public ArrayList<Professor> filtraNomeProf(String aux){
        ArrayList<Professor> retorno = new ArrayList<>();

        for(Professor p:professores){
            if(p.getNome().toLowerCase().startsWith(aux.toLowerCase())){
                retorno.add(p);
            }
        }
        return retorno;
    }

    //lista cursos
    public ArrayList<Curso> listarCursos(){
        return cursos;
    }

    //filtra cursos na lista
    public ArrayList<Curso> filtraNomeCurso(String aux){
        ArrayList<Curso> retorno = new ArrayList<>();

        for(Curso c:cursos){
            if(c.getNome().toLowerCase().startsWith(aux.toLowerCase())){
                retorno.add(c);
            }
        }
        return retorno;
    }

    //========================================================== Matricula Aluno ==========================================================

    // lista matriculados
    public ArrayList<Aluno> listarAlunosMatriculados(Curso curso){
        return curso.getAlunos();
    }

    //matricular aluno
    public boolean matricularAluno(Aluno aluno, Curso curso){
        return curso.matricula(aluno);
    }

    //desmatricular aluno
    public boolean desmatriculaAluno(Aluno aluno, Curso curso){
        return curso.desmatricula(aluno.getCpf());
    }

    //========================================================== Arquivo Txt e Bin ==========================================================

    public void salvaDados() throws IOException{

        File arq = new File(NOME_ARQUIVO);
        FileWriter fw = new FileWriter(arq);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("[ALUNO]\n");
        for(Aluno a:alunos){
            bw.write("\t"+a.paraTexto());
            bw.newLine();
        }

        bw.write("[PROFESSOR]\n");
        for(Professor a:professores){
            bw.write("\t"+a.paraTexto());
            bw.newLine();
        }

        bw.write("[CURSO]\n");
        for(Curso a:cursos){
            bw.write("\t"+a.paraTexto());
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    public void leDados() throws IOException{
        File arq = new File(NOME_ARQUIVO);
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);
        int tipo = 0;

        String linha;

        while((linha=br.readLine())!=null){

            if(linha.contains("[ALUNOS]")){
                tipo = 0;
            }else if(linha.contains("[PROFESSORES]")){
                tipo = 1;
            }else if(linha.contains("[CURSOS]")){
                tipo = 2;
            }
            if(!linha.contains("[")){
                linha = linha.replace("\t", "");
                String[] pedacos = linha.split(";");


                if(tipo == 0){
                    String nome = pedacos[0].split(":")[1];
                    String cpf = pedacos[1].split(":")[1];
                    String email = pedacos[2].split(":")[1];
                    String numero = pedacos[3].split(":")[1];
                    String dataMatricula = pedacos[4].split(":")[1];
                    LocalDate dataMatriculaDate = LocalDate.parse(dataMatricula);

                    Aluno a = new Aluno(nome, cpf, email, numero, dataMatriculaDate);
                    this.alunos.add(a);

                }else if(tipo == 1){

                    String nome = pedacos[0].split(":")[1];
                    String cpf = pedacos[1].split(":")[1];
                    String email = pedacos[2].split(":")[1];
                    String numero = pedacos[3].split(":")[1];
                    String salario = pedacos[4].split(":")[1];
                    Double salarioD = Double.valueOf(salario).doubleValue();

                    Professor a = new Professor(nome, cpf, email, numero, salarioD);
                    this.professores.add(a);
                }else if(tipo == 2){

                    String codigo = pedacos[0].split(":")[1];
                    int codigoD = Integer.valueOf(codigo).intValue();
                    String nome = pedacos[1].split(":")[1];
                    String descricao = pedacos[2].split(":")[1];
                    String cargaHoraria = pedacos[3].split(":")[1];
                    int cargaHorariaD = Integer.valueOf(cargaHoraria).intValue();
                    String professor = pedacos[4].split(":")[1];
                    Professor professorD = Professor.parse(professor);
                   

                    Curso a = new Curso(codigoD, nome, descricao, cargaHorariaD, professorD);
                    this.cursos.add(a);

                }
            }

        }
    }

    public void salvaDadosBin() throws IOException{
        File arq = new File(NOME_ARQUIVO_BIN);
        FileOutputStream fos = new FileOutputStream(arq);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(alunos);
        oos.writeObject(professores);
        oos.writeObject(cursos);

        oos.close();
        fos.close();
    }

    public void leDadosBin() throws IOException,ClassNotFoundException{

        File arq = new File(NOME_ARQUIVO_BIN);
        FileInputStream fis = new FileInputStream(arq);
        ObjectInputStream ois = new ObjectInputStream(fis);

        alunos = (ArrayList) ois.readObject();
        professores = (ArrayList) ois.readObject();
        cursos = (ArrayList) ois.readObject();

        ois.close();
        fis.close();
    }

    

}
