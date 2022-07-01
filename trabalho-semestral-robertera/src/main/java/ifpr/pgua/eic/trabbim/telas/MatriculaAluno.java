package ifpr.pgua.eic.trabbim.telas;

import ifpr.pgua.eic.trabbim.modelos.Aluno;
import ifpr.pgua.eic.trabbim.modelos.Curso;
import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class MatriculaAluno {

    @FXML
    private ComboBox<Aluno> cbAluno;

    @FXML
    private ComboBox<Curso> cbCurso;

    
    private Escola escola;

    public MatriculaAluno(Escola escola){
        this.escola = escola;
    }

    public void initialize(){
        cbAluno.getItems().clear();
        cbAluno.getItems().addAll(escola.listarAlunos());

        cbCurso.getItems().clear();
        cbCurso.getItems().addAll(escola.listarCursos());
    }

    @FXML
    private void matricular(){
        Aluno aluno = cbAluno.getSelectionModel().getSelectedItem();
        Curso curso = cbCurso.getSelectionModel().getSelectedItem();

        boolean flag = true;
        String msg = "";

        if(aluno == null){
            msg += "O aluno deve ser selecionado!";
            flag = false;
        }

        if(curso == null){
            msg += "O curso de ve ser selecionado!";
            flag = false;
        }

        if(flag){
            boolean ret = escola.matricularAluno(aluno, curso);
            if(ret){
                msg = "Aluno matriculado!!";
                limpar(null);
            }else{
                msg = "Problema na matricula do aluno!!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void desmatricular(){
        
        Aluno aluno = cbAluno.getSelectionModel().getSelectedItem();
        Curso curso = cbCurso.getSelectionModel().getSelectedItem();

        boolean flag = true;
        String msg = "";

        if(aluno == null){
            msg += "O aluno deve ser selecionado!";
            flag = false;
        }

        if(curso == null){
            msg += "O curso de ve ser selecionado!";
            flag = false;
        }

        if(flag){
            boolean ret = escola.desmatriculaAluno(aluno, curso);
            if(ret){
                msg = "Aluno Desmatriculado!!";
                limpar(null);
            }else{
                msg = "Problema na desmatricula do aluno!!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();
    }

    private void limpar(ActionEvent event){
        cbAluno.getSelectionModel().clearSelection();
        cbCurso.getSelectionModel().clearSelection();
    }
    
}
