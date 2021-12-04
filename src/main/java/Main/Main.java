/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 18/11/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe principal do projeto
 * @author Diego Cerqueira e Joanderson Santos
 *
 */
public class Main extends Application {
    
    /**
     * Metodo da interface Application que inicializa a tela principal do projeto
     * @param stage
     */
    @Override
    public void start(Stage stage) throws Exception {
    
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainScreen.fxml"));
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root);
        
        stage.setTitle("MainScreen");
        //stage.setMaximized(true);
        stage.setResizable(true);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        Image image = new Image("https://cdn.pixabay.com/photo/2017/05/27/22/25/cruz-2349636_960_720.png");

        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
