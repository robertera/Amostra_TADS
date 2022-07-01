package ifpr.pgua.eic.trabbim.telas;

import ifpr.pgua.eic.trabbim.modelos.Aluno;
import ifpr.pgua.eic.trabbim.modelos.Curso;

import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ListasMatriculados {

    @FXML
    private ListView<Aluno> listaMatriculados;

    @FXML
    private ComboBox<Curso> cbCursos;

    Escola escola;

    public ListasMatriculados(Escola escola){
        this.escola = escola;
    }

    public void initialize(){
        cbCursos.getItems().clear();
        cbCursos.getItems().addAll(escola.listarCursos());
    }

    @FXML
    private void busca(ActionEvent event){
        Curso curso = cbCursos.getSelectionModel().getSelectedItem();
        listaMatriculados.getItems().clear();
        
        String msg = "";
        boolean flag = true;

        if(curso == null){
            msg += "Deve ser selecionado o curso!";
            flag = false;

            Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
            alert.showAndWait();
        }else{
            listaMatriculados.getItems().addAll(escola.listarAlunosMatriculados(curso));
        }
    }
    
    
}
