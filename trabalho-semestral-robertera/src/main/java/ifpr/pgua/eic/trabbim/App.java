package ifpr.pgua.eic.trabbim;

import java.io.IOException;

import ifpr.pgua.eic.trabbim.repositorios.Escola;
import ifpr.pgua.eic.trabbim.telas.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * JavaFX App
 */
public class App extends Application {

    private Escola escola;
    
    @Override
    public void start(Stage stage) throws IOException {
        
        escola = new Escola();
        escola.leDados();
        
        /*SOMENTE EM TEMPO DE DESENVOLVIMENTO*/
        /*DESABILITAR EM PRODUCAO*/
        //escola.povoa();

        Parent root = loadTela("fxml/home.fxml", (o)->new Home(escola));

        Scene scene = new Scene(root, 720, 480);
        
        stage.setScene(scene);
        stage.show();
    }

    public void stop() throws Exception{
        super.stop();

        escola.salvaDados();
        escola.salvaDadosBin();
    }

    public static Parent loadTela(String fxml, Callback controller){
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(fxml));
            loader.setControllerFactory(controller);

            root = loader.load();
            
        }catch (Exception e){
            System.out.println("Problema no arquivo fxml. Está correto?? "+fxml);
        }
        return root;   
    }


    public static void main(String[] args) {
        launch();
    }

}