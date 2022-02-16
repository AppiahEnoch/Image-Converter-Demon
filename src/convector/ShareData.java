package convector;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


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

public class ShareData {
    private static ShareData instance;
    static File imageFile;
    static Path pathToDesktop = null;
    static String convectedFileName = null;
    static String cExt = null;

    public int data = 0;

    private ShareData() {
        createFolderOnDeskTop();
    }

    synchronized public static ShareData getInstance() {
        if (instance == null) {
            instance = new ShareData();
        }
        return instance;
    }

    public void createFolderOnDeskTop() {
        String folderName = "IMAGE CONVECTOR DEMON";

        try {

            String homeFolder = System.getProperty("user.home"); //path for folder
            java.nio.file.Path path = Paths.get(homeFolder, "Pictures", folderName);
            pathToDesktop = path;

            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                // Folder is already created

            } else {
                // folder is being created
                Paths.get(homeFolder, "Pictures", folderName).toFile().mkdir();

            }
        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    public String removeFileExtension(File fileName) {
        try {
            String rmx = getExtension(fileName).trim();
            String ext=rmx;
            int rx = rmx.length();
            StringBuilder name = new StringBuilder();
            name.append(fileName);
            int l = name.length();
            int endOfString = l - (rx + 1);
            String validName = name.substring(0, endOfString);

            return ext;

        } catch (Exception e) {

        }
return "";
    }



    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        String rawName=s;

        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }

        String str = rawName;

        System.out.println("ext"+ext);
        str = str.replace( ext, "");
        convectedFileName=str;




        return ext;
    }


  static   File file;

    void convert1(){
        String loc=pathToDesktop+"\\";
        String name= convectedFileName;
        String ext=cExt;


        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final BufferedImage image = ImageIO.read(fileInputStream);
            fileInputStream.close(); // ImageIO.read does not close the input stream

            final BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            convertedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);

            final FileOutputStream fileOutputStream = new FileOutputStream(loc+name+"."+ext);
            final boolean canWrite = ImageIO.write( convertedImage, ext, fileOutputStream);
            fileOutputStream.close(); // ImageIO.write does not close the output stream

            if (!canWrite) {
                throw new IllegalStateException("Failed to write image.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void openFolder(){
        try {
            Desktop desktop=Desktop.getDesktop();
            File f=new File(pathToDesktop+"");
            desktop.open(f);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}