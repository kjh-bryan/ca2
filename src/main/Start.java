/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Classes.TestGrabManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kangj
 */
public class Start extends Application{

    public static Queue<String> queue = new LinkedList<>();
    public static Set<String> marked = new HashSet<>();
    public static String regex = "http[s]*.://(\\w+\\.)*(\\w+)";
    public static ObservableList<String> collectedUrl;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED); // Remove the window's title bar
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size
        Parent root = FXMLLoader.load(getClass().getResource("GUI/WebCrawlerInterface.fxml"));// Set the window's scene to Menu.fxml which is the first thing it will show.
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setX(d.width / 2 - (primaryStage.getWidth() / 2)); // Setting the stage to the middle of the screen
        primaryStage.setY(d.height / 2 - (primaryStage.getHeight() / 2));// Setting the stage to the middle of the screen
    }
    public static void main(String[] args) {
        try {
            launch(args); // Launch the window
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getCause());

        }
    }
     public static void changeStage(Class getClass, JFXButton btn, String fxmlResource) {
        //Change stage e.g. from menu to search menu 
        try {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // get screen size
            Stage window = (Stage) btn.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass.getClass().getResource(fxmlResource));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            stage.setX(d.width / 2 - (stage.getWidth() / 2));
            stage.setY(d.height / 2 - (stage.getHeight() / 2));
            window.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getCause());
            System.out.println("From Class : " + getClass.getName());
        }
    }
     
     public static void loadDialog(ActionEvent event,StackPane stackPane,String heading,String body){
         JFXDialogLayout content = new JFXDialogLayout();
         if(!heading.isEmpty())
             content.setHeading(new Text(heading));
         if(!body.isEmpty())
             content.setBody(new Text(body));
         JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
         JFXButton button = new JFXButton("Okay");
         button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 dialog.close();
             }
         });
         content.setActions(button);
         dialog.show();
     }
     
     public static void showSpinner(JFXSpinner spinner)
     {
            System.out.println("Start showSpinner getOpacity : " + spinner.getOpacity());
            if(spinner.getOpacity() == 100)
            {
                spinner.setOpacity(0);
                System.out.println("Set opacity to 0 : " + spinner.getOpacity());
            }
            else if(spinner.getOpacity() == 0)
            {
                spinner.setOpacity(100);
                System.out.println("Set opacity to 100 : " + spinner.getOpacity());
            }
     }
     
     
}
