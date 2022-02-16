package convector;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {


    @FXML
    private Pane imf;

    @FXML
    void Drag(MouseEvent event) {
        Dragboard db = imf.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        db.setContent(content);
        event.consume();
    }


    @FXML
    void dragOverMe(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }


    @FXML
    private ImageView imageView;

    @FXML
    private Text txtDrop;
    //imageView.setImage(new Image(new FileInputStream(files.get(0))));




    @FXML
    private JFXToolbar tb;

    @FXML
    private JFXButton btClose;

    private double xOffset;
    private double yOffset;

    @FXML
    void closeApplication(ActionEvent event) {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    void minimiseWindow(ActionEvent event) {
        currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setIconified(true);
        imageView.setVisible(false);
        txtDrop.setVisible(true);

        try {

            r2 = FXMLLoader.load(getClass().getResource("chooseExtension.fxml"));
            currentScene2 = new Scene(r2);
            //  currentStage2.setTitle("11");
            currentStage2.setScene(currentScene2);
            currentStage2.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }



    @FXML
    void mouseDraged_(MouseEvent event) {
     //   currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setX(event.getScreenX() + xOffset);
        currentStage.setY(event.getScreenY() + yOffset);
    }


    @FXML
    void mousePressed_(MouseEvent event) {
        currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        xOffset =  currentStage.getX() - event.getScreenX();
        yOffset =  currentStage.getY() - event.getScreenY();
    }




    Image img;
    File file;

    @FXML
    void droped(DragEvent event) {

        try {


            Dragboard db = event.getDragboard();

            if (event.getDragboard().hasFiles()) {
                file = db.getFiles().get(0);

                if (getImage()) {
                    img = new Image(file.toURI().toString());
                    txtDrop.setVisible(false);
                    imageView.setImage(img);
                    imageView.setVisible(true);
                    System.out.println("getimage: true");

                    openWindowByClick(event, "chooseExtension.fxml");
                }

                else {
                    System.out.println(" not ima");
                    txtDrop.setVisible(true);
                    imageView.setVisible(false);

                    try {

                        r2 = FXMLLoader.load(getClass().getResource("chooseExtension.fxml"));
                        currentScene2 = new Scene(r2);
                      //  currentStage2.setTitle("11");
                        currentStage2.setScene(currentScene2);
                        currentStage2.close();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

        } catch (Exception e) {
            txtDrop.setVisible(false);

            e.printStackTrace();
        }


    }

ShareData m=ShareData.getInstance();
    public boolean getImage() {
        try {

            String ext = m.removeFileExtension(file);
            ext = ext.toLowerCase();

            if (ext==null){
                return false;
            }
            else   if (ext.length()<1){
                return false;
            }

            String acceptedExt = ".png .jpg .jpeg .bmp .svg";

            if (acceptedExt.contains(ext)) {
                ShareData.file=file;
                return true;
            }
        } catch (Exception e) {
            return false;
        }


        return false;
    }




    private Stage currentStage;
    private Scene currentScene;
    private Parent r1;


    private Stage currentStage2=new Stage();

    private Scene currentScene2;
    private Parent r2;


    //   openWindowByClick(event,"msc.fxml");

boolean setUtility=true;

    @FXML
    void openWindowByClick(DragEvent event, String fxml) {
        System.out.println(setUtility);

        try {
            r2 = FXMLLoader.load(getClass().getResource(fxml));
            //currentStage2
            currentScene2=new Scene(r2);
            currentStage2.setScene(currentScene2);
            currentStage2.setTitle("IMAGE CONVECTOR ULTIMATE - AECleanCodes");

            r2.requestFocus();
            currentStage2.setResizable(false);
            if (setUtility){
                currentStage2.initStyle(StageStyle.UNDECORATED);
                setUtility=false;
            }


            currentStage2.show();
            r2.requestFocus();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            currentStage2.setX((primScreenBounds.getWidth() - currentStage2.getWidth()) / 2);
            currentStage2.setY((primScreenBounds.getHeight() - currentStage2.getHeight()) / 2);

        } catch (Exception e) {
e.printStackTrace();
        }


    }




}
