/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.GUIController;

import Classes.GrabManager;
import Classes.TestGrabManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Start;

/**
 * FXML Controller class
 *
 * @author kangj
 */
public class WebCrawlerInterfaceController implements Initializable {

     @FXML
    private JFXComboBox<Integer> noOfThreadComboBox;
    @FXML
    private JFXListView<String> queryListView;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXButton webContentBtn;

    @FXML
    private JFXTextArea htmltextArea;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton htmlBtn;

    @FXML
    private JFXTextField queryTxt;
    
      @FXML
    private JFXButton statisticBtn;

    @FXML
    void handleHtmlAction(ActionEvent event) {

    }

    @FXML
    void handleWebContentAction(ActionEvent event) {

    }

    @FXML
    void handleStatsBtn(ActionEvent event) {

    }
    
    @FXML
    void handleExitAction(ActionEvent event) {
        Stage window = (Stage) exitBtn.getScene().getWindow();
        window.close();
    }

    @FXML
    void handleSearchAction(ActionEvent event) {
        if(queryTxt.getText()!= "")
        {
            ObservableList<String> data = getSearchQueryData();
            queryListView.setItems(data);
        }
        else
        {
            Start.loadDialog(event,stackPane,"Error","Please enter a value to the search phrase");
        }
    }
    
    ObservableList<String> getSearchQueryData(){
        String query = queryTxt.getText().replace(" ", "+");
        TestGrabManager t = new TestGrabManager();
        ObservableList<String> resultGoogle = FXCollections.observableArrayList();
        ObservableList<String> resultBing = FXCollections.observableArrayList();
        try
        {
            resultGoogle = t.happy(noOfThreadComboBox.getValue(), query, 1);
            resultBing = t.happy(noOfThreadComboBox.getValue(), query, 2);
        }
        catch(InterruptedException ex){
            
        }
        catch(IOException e)
        {
            
        }
        ObservableList<String> finalResult = FXCollections.observableArrayList();
        
        for(String g : resultGoogle)
        {
            finalResult.add(g);
        }
        for(String b : resultBing)
        {
            finalResult.add(b);
        }
        
        return finalResult;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        queryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String currentItemSelected = queryListView.getSelectionModel().getSelectedItem();
                }
            }
        });

    }
    
    void initComboBox(){
        ObservableList<Integer> noOfThreadList = FXCollections.observableArrayList(1,2,4,8);
        noOfThreadComboBox.setValue(1);
        noOfThreadComboBox.setItems(noOfThreadList);
    }
}
