package ifpr.pgua.eic.trabbim.telas;

import java.time.LocalDate;

import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroAluno {
    
    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTelefone;

    @FXML
    private DatePicker datePickerAno;
    
    private Escola escola;

    public CadastroAluno(Escola escola){
        this.escola = escola;
    }

    @FXML
    private void cadastrar(){

        String cpf = tfCpf.getText();
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        LocalDate dataMatricula = datePickerAno.getValue();

        boolean flag = true;
        String msg = "";

        
        if(cpf == null){
            msg += "\nCPF não pode ser vazio!";
            flag = false;
        }
 
        if(flag){
            boolean ret = escola.cadastrarAluno(cpf, nome, email, telefone, dataMatricula);
            if(ret){

                limpar(null);
                
                msg = "Aluno cadastrado!";
                
            }else{
                msg = "Aluno não cadastrado! CPF repetido!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();

    }

    @FXML
    private void limpar(ActionEvent event){
        tfCpf.clear();
        tfNome.clear();
        tfEmail.clear();
        tfTelefone.clear();
        datePickerAno.setValue(LocalDate.now());
    }

    

}