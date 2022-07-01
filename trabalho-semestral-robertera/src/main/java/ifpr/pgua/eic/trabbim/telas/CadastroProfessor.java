package ifpr.pgua.eic.trabbim.telas;


import ifpr.pgua.eic.trabbim.repositorios.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroProfessor {
    
    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfSalario;
    
    private Escola escola;

    public CadastroProfessor(Escola escola){
        this.escola = escola;
    }

    @FXML
    private void cadastrar(){

        String cpf = tfCpf.getText();
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        double salario = Integer.valueOf(tfSalario.getText());

        boolean flag = true;
        String msg = "";

        
        if(cpf == null){
            msg += "\nCPF não pode ser vazio!";
            flag = false;
        }

        if(salario < 0){
            msg += "\nSalario não poder ser vazio!";
            flag = false;
        }
 

        if(flag){
            boolean ret = escola.cadastrarProfesor(cpf, nome, email, telefone, salario);
            if(ret){
                System.out.println(escola.listarProfessores());

                limpar(null);
                
                msg = "Professor cadastrado!";
                
            }else{
                msg = "Professor não cadastrado! CPF repetido!";
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
        tfSalario.clear();
    }

    

}