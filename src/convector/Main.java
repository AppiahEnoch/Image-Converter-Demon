package convector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("chooseExtension.fxml"));
        primaryStage.setTitle("Image Convector v1.1");
        primaryStage.initStyle(StageStyle.DECORATED. UNDECORATED);
        Image icon = new Image(getClass().getResourceAsStream("icons8_poison_30px.png"));
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        root.requestFocus();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {

        launch(args);
    }
}
