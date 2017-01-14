package GUI;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by dell on 1/13/2017.
 */
public class StatementView {
    BorderPane borderPane;
    Button buttonSelect=new Button("Select");

    private Stage primaryStage;
    private ArrayList<Controller> controllers;
    private ListView<String> statementList;
    private ObservableList programs= FXCollections.observableArrayList();


    public StatementView(ArrayList<Controller> controllers, ArrayList<String> list,Stage primaryStage) {
        this.controllers = controllers;
        this.primaryStage=primaryStage;
        programs.addAll(list);
        initBorderPane();
    }

    private void initBorderPane() {
        borderPane= new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
    }

    private Node initBottom() {
        AnchorPane anchorPane=new AnchorPane();

        anchorPane.setTopAnchor(buttonSelect,20d);
        anchorPane.setLeftAnchor(buttonSelect, 20d);
        anchorPane.setRightAnchor(buttonSelect, 20d);
        anchorPane.setBottomAnchor(buttonSelect,20d);

        buttonSelect.setFont(new javafx.scene.text.Font(20));
        buttonSelect.setOnAction(this::selected);

        anchorPane.getChildren().add(buttonSelect);
        return anchorPane;
    }

    private void selected(ActionEvent actionEvent) {
        int index=statementList.getSelectionModel().getSelectedIndices().get(0);
        if (index > -1 && index<controllers.size()){

            ExecutionView executionView=new ExecutionView(this.controllers.get(index));
            primaryStage.setScene(new Scene(executionView.getExecutionView(),1500,650));
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Execution window");
            primaryStage.setResizable(false);
            primaryStage.show();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("You have to select a program for execution from the given list!");
            alert.showAndWait();
        }
    }

    private Node initCenter() {
        AnchorPane anchorPane=new AnchorPane();

        statementList=new ListView<>(programs);
        statementList.setMinWidth(400d);

        anchorPane.setTopAnchor(statementList,20d);
        anchorPane.setRightAnchor(statementList,20d);
        anchorPane.setBottomAnchor(statementList,20d);
        anchorPane.setLeftAnchor(statementList,20d);
        anchorPane.getChildren().add(statementList);

        return anchorPane;
    }

    private Node initTop() {
        AnchorPane anchorPane=new AnchorPane();

        Label label=new Label("Please select a program: ");
        label.setFont(new javafx.scene.text.Font(20d));
        label.setStyle("-fx-font-weight: bold");

        anchorPane.setTopAnchor(label,20d);
        anchorPane.setBottomAnchor(label,20d);
        anchorPane.setLeftAnchor(label,20d);
        anchorPane.getChildren().add(label);

        return anchorPane;
    }

    public BorderPane getStatementView(){
          return borderPane;
    }
}
