package ifpr.pgua.eic.trabbim.telas;

import ifpr.pgua.eic.trabbim.modelos.Aluno;
import ifpr.pgua.eic.trabbim.modelos.Curso;
import ifpr.pgua.eic.trabbim.modelos.Professor;
import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Listas {

    @FXML
    private ListView<Aluno> listaAlunos;

    @FXML
    private ListView<Professor> listaProfessores;

    @FXML
    private ListView<Curso> listaCursos;
    
    @FXML
    private TextField tfNomeAluno;

    @FXML
    private TextField tfNomeProfessor;

    @FXML
    private TextField tfNomeCurso;

    @FXML
    private TextArea taStatus;

    private Escola escola;


    public Listas(Escola escola){
        this.escola = escola;
    }

    public void initialize(){
        listaAlunos.getItems().addAll(escola.listarAlunos());
        listaProfessores.getItems().addAll(escola.listarProfessores());
        listaCursos.getItems().addAll(escola.listarCursos());
    }

    @FXML
    public void mostraAlunos(KeyEvent evnt){

        String aux = tfNomeAluno.getText();

        if(aux.length() >= 2){
            listaAlunos.getItems().clear();
            listaAlunos.getItems().addAll(escola.filtraNomeAlunos(aux));
        }else{
            listaAlunos.getItems().clear();
            listaAlunos.getItems().addAll(escola.listarAlunos());
        }
    }

    @FXML
    public void mostraProfessores(KeyEvent evnt){

        String aux = tfNomeAluno.getText();

        if(aux.length() >= 2){
            listaProfessores.getItems().clear();
            listaProfessores.getItems().addAll(escola.filtraNomeProf(aux));
        }else{
            listaProfessores.getItems().clear();
            listaProfessores.getItems().addAll(escola.listarProfessores());
        }
    }

    @FXML
    public void mostraCursos(KeyEvent evnt){
        String aux = tfNomeCurso.getText();

        if(aux.length() >= 2){
            listaCursos.getItems().clear();
            listaCursos.getItems().addAll(escola.filtraNomeCurso(aux));
        }else{
            listaCursos.getItems().clear();
            listaCursos.getItems().addAll(escola.listarCursos());
        }
    }


    @FXML
    public void atualizaStatus(MouseEvent evnt){
        String msg="";

        if(evnt.getSource() == listaAlunos){
            Aluno a = listaAlunos.getSelectionModel().getSelectedItem();
            if(a !=null){
                msg = a.getNome()+"("+a.getCpf()+") - "+a.getEmail();
            }
        }else if(evnt.getSource() == listaProfessores){
            Professor p = listaProfessores.getSelectionModel().getSelectedItem();
            if(p !=null){
                msg = p.getNome()+"("+p.getCpf()+") - "+p.getEmail();
            }
        }else if(evnt.getSource() == listaCursos){
            Curso c = listaCursos.getSelectionModel().getSelectedItem();
            if(c !=null){
                msg = c.getNome()+"("+c.getCodigo()+") - "+c.getDescricao();
            }
        }

        taStatus.setText(msg);
    }

    
}
