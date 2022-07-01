package ifpr.pgua.eic.trabbim.telas;

import ifpr.pgua.eic.trabbim.modelos.Professor;
import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroCurso {

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfCargaHoraria;

    @FXML
    private ComboBox<Professor> cbProfessor;

    private Escola escola;

    public CadastroCurso(Escola escola){
        this.escola = escola;
    }

    public void initialize(){
        cbProfessor.getItems().clear();
        cbProfessor.getItems().addAll(escola.listarProfessores());
    }

    @FXML
    private void cadastrar(){
        int codigo = Integer.valueOf(tfCodigo.getText());
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        int cargaHoraria = Integer.valueOf(tfCargaHoraria.getText());
        Professor professor = cbProfessor.getSelectionModel().getSelectedItem();

        boolean flag = true;
        String msg = "";

        if(professor == null){
            msg += "O professor deve ser selecionado!";
            flag = false;
        }

        if(flag){
            boolean ret = escola.cadastrarCurso(codigo, nome, descricao, cargaHoraria, professor);
            if(ret){
                msg = "Curso cadastrado!!!";
                limpar(null);
            }else{
                msg = "Problema no cadastro do curso!!!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void limpar(ActionEvent evnt){
        tfCodigo.clear();
        tfNome.clear();
        tfDescricao.clear();
        tfCargaHoraria.clear();
        cbProfessor.getSelectionModel().clearSelection();
        
    }
    
}
