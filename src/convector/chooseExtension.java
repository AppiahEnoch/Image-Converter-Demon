package convector;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class chooseExtension {

    @FXML
    private RadioButton png;

    @FXML
    private ToggleGroup rGroup;

    @FXML
    private RadioButton jpj;

    @FXML
    private RadioButton jpeg;

    @FXML
    private RadioButton svg;

    @FXML
    private RadioButton bmp;

    @FXML
    private RadioButton webp;

    @FXML
    private RadioButton tff;

    @FXML
    private RadioButton gif;

    @FXML
    private RadioButton ico;

    @FXML
    private JFXButton btConvert;

    @FXML
    void convert(ActionEvent event) {
            selected();
            ShareData m=ShareData.getInstance();
            m.convert1();
            m.openFolder();





    }

    private double xOffset;
    private double yOffset;
    Stage currentStage=new Stage();
    @FXML
    void mouseDraged_(MouseEvent event) {

        currentStage.setX(event.getScreenX() + xOffset);
        currentStage.setY(event.getScreenY() + yOffset);
    }


    @FXML
    void mousePressed_(MouseEvent event) {
        currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        xOffset =  currentStage.getX() - event.getScreenX();
        yOffset =  currentStage.getY() - event.getScreenY();
    }




    @FXML
    void selectedItem(ActionEvent event) {

    }


    String selected (){
        String userExt="";
        if (png.isSelected()){
           userExt="png";
        }
      else   if (jpj.isSelected()){
            userExt="jpg";
        }
        else   if (jpeg.isSelected()){
            userExt="jpeg";
        }
        else    if (bmp.isSelected()){
            userExt="bmp";
        }
        else     if (svg.isSelected()){
            userExt="svg";
        }
        else   if (webp.isSelected()){
            userExt="webp";
        }
        else    if (tff.isSelected()){
            userExt="tff";
        }
        else    if (gif.isSelected()){
            userExt="gif";
        }
        else     if (ico.isSelected()){
            userExt="ico";
        }


ShareData.cExt=userExt;
        System.out.println("myfile:"+ShareData.cExt);
        return userExt;
    }




}
