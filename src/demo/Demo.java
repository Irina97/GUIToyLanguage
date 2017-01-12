package demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * Created by dell on 1/12/2017.
 */
public class Demo {

    private BorderPane borderPane;
    ListView<String> list;
    private ObservableList programs = FXCollections.observableArrayList();

    private TableView<Students> studentsTable = new TableView<>();
    private static final ObservableList<Students> studentsData = FXCollections.observableArrayList();

    private void initBorderPane() {
        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setLeft(initLeft());
        borderPane.setCenter(initCentre());
    }

    public Demo() {
        initBorderPane();
    }
    public Parent getView(){
        return borderPane;
    }

    private Node initCentre() {
        AnchorPane anchorPane=new AnchorPane();
        TableColumn name = new TableColumn("Name");
        TableColumn age = new TableColumn("Age");
        name.setCellValueFactory(new PropertyValueFactory<Students,String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Students,Students>("age"));
        studentsTable.getColumns().addAll(name, age);
        studentsTable.setItems(studentsData);

        studentsData.add(new Students("Irina",19));

        AnchorPane.setTopAnchor(studentsTable, 20d);
        AnchorPane.setLeftAnchor(studentsTable, 20d);
        anchorPane.getChildren().add(studentsTable);
        return anchorPane;
    }

    private Node initLeft() {
        AnchorPane anchorPane=new AnchorPane();
        list = new ListView<String>(programs);
        programs.add("Irina este cea mai frumoasa fiinta din lume!!!!");
        programs.add("Te ador!");
        list.setMinWidth(200d);
        AnchorPane.setTopAnchor(list, 20d);
        AnchorPane.setLeftAnchor(list, 20d);
        anchorPane.getChildren().add(list);
        return anchorPane;
    }

    private Node initTop() {
        AnchorPane anchorPane = new AnchorPane();
        Label l = new Label("Select the program to be executed:");
        l.setFont(new Font(20));
        AnchorPane.setTopAnchor(l, 20d);
        AnchorPane.setLeftAnchor(l, 20d);
        anchorPane.getChildren().add(l);
        return anchorPane;
    }
}
