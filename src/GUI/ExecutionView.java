package GUI;

import Controller.Controller;
import Model.ProgramState.ProgramState;
import Model.Statement.Statement;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Stack;

/**
 * Created by dell on 1/13/2017.
 */
public class ExecutionView {
    private BorderPane borderPane;

    private Controller ctrl;
    private int prgStateIndex=0;

    private TextField nrField=new TextField();
    private TextField output=new TextField();

    private Button buttonOne = new Button("Run one step");
    private Button buttonAll=new Button("Run all step");

    private TableView<HeapTable>  heapTable= new TableView<>();
    private static final ObservableList<HeapTable> heapData= FXCollections.observableArrayList();

    private TableView<FileTableGUI> filetable= new TableView<>();
    private static final ObservableList<FileTableGUI> fileTableData=FXCollections.observableArrayList();

    private TableView<SymbolTableGUI> symTable=new TableView<>();
    private static final ObservableList<SymbolTableGUI> symTableData=FXCollections.observableArrayList();

    private ListView<String> prgState;
    private ObservableList prgStateDataa =FXCollections.observableArrayList();

    private ListView<String> exeStack=new ListView<>();
    private static final ObservableList exeStackData=FXCollections.observableArrayList();


    public ExecutionView(Controller ctrl) {
        this.ctrl=ctrl;
        initBorderPane();
        refreshTables();
    }

    private void initBorderPane() {
        borderPane=new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
    }

    private Node initBottom() {
        AnchorPane anchorPane=new AnchorPane();
        Label label=new Label("Output: ");
        label.setFont(new Font(16d));
        label.setStyle("-fx-font-weight: bold");

        HBox hb=new HBox(10,label,output);

        anchorPane.setLeftAnchor(hb,20d);
        anchorPane.setRightAnchor(hb,20d);
        anchorPane.setTopAnchor(hb,20d);
        anchorPane.setBottomAnchor(hb,20d);

        anchorPane.getChildren().add(hb);
        return anchorPane;
    }

    private Node initCenter() {
        GridPane gridPane=new GridPane();
        Node heapT=initHeapTable();
        Node fileT=initFileTable();
        Node symT=initSymTable();
        Node prgS=initPrgState();
        Node exeS=initExeStack();
        gridPane.add(heapT,1,1);
        gridPane.add(fileT,2,1);
        gridPane.add(symT,3,1);
        gridPane.add(prgS,4,1);
        gridPane.add(exeS,5,1);
        return gridPane;
    }
    private Node initExeStack(){
        AnchorPane anchorPane=new AnchorPane();

        Label title=new Label("Exe Stack");
        title.setFont(new Font(16d));
        title.setStyle("-fx-font-weight: bold");

        anchorPane.setRightAnchor(title,20d);
        anchorPane.setLeftAnchor(title,20d);

        exeStack=new ListView<>(exeStackData);
        exeStack.setMinWidth(500d);
        exeStack.setMaxWidth(500d);

        anchorPane.setTopAnchor(exeStack,20d);
        anchorPane.setBottomAnchor(exeStack,20d);
        anchorPane.setRightAnchor(exeStack,20d);
        anchorPane.setLeftAnchor(exeStack,20d);

        anchorPane.getChildren().add(title);
        anchorPane.getChildren().add(exeStack);

        return anchorPane;
    }

    private Node initHeapTable() {
        AnchorPane anchorPane=new AnchorPane();
        Label title=new Label("Heap table");
        title.setFont(new Font(16d));
        title.setStyle("-fx-font-weight: bold");

        anchorPane.setRightAnchor(title,20d);
        anchorPane.setLeftAnchor(title,20d);

        TableColumn address=new TableColumn("Address");
        TableColumn value=new TableColumn("Value");
        address.setCellValueFactory(new PropertyValueFactory<HeapTable,String>("address"));
        value.setCellValueFactory(new PropertyValueFactory<HeapTable,Integer>("value"));


        heapTable.setMaxWidth(163d);
        heapTable.setMinWidth(163d);
        heapTable.getColumns().addAll(address,value);
        heapTable.setItems(heapData);

        anchorPane.setTopAnchor(heapTable,20d);
        anchorPane.setBottomAnchor(heapTable,20d);
        anchorPane.setRightAnchor(heapTable,20d);
        anchorPane.setLeftAnchor(heapTable,20d);

        anchorPane.getChildren().add(title);
        anchorPane.getChildren().add(heapTable);
        return anchorPane;

    }

    private Node initFileTable(){
        AnchorPane anchorPane= new AnchorPane();
        Label title=new Label("File Table");
        title.setFont(new Font(16d));
        title.setStyle("-fx-font-weight: bold");

        anchorPane.setRightAnchor(title,20d);
        anchorPane.setLeftAnchor(title,20d);

        TableColumn identifier=new TableColumn("Identifier");
        TableColumn filename=new TableColumn("FileName");
        identifier.setCellValueFactory(new PropertyValueFactory<FileTableGUI,Integer>("identifier"));
        filename.setCellValueFactory(new PropertyValueFactory<FileTableGUI,String>("filename"));
        filetable.setMaxWidth(163d);
        filetable.setMinWidth(163d);
        filetable.getColumns().addAll(identifier,filename);
        filetable.setItems(fileTableData);

        anchorPane.setTopAnchor(filetable,20d);
        anchorPane.setBottomAnchor(filetable,20d);
        anchorPane.setRightAnchor(filetable,20d);
        anchorPane.setLeftAnchor(filetable,20d);

        anchorPane.getChildren().add(title);
        anchorPane.getChildren().add(filetable);

        return anchorPane;
    }

    private Node initSymTable(){
        AnchorPane anchorPane=new AnchorPane();

        Label title=new Label("Symbol Table");
        title.setFont(new Font(16d));
        title.setStyle("-fx-font-weight: bold");

        anchorPane.setRightAnchor(title,20d);
        anchorPane.setLeftAnchor(title,20d);

        TableColumn varName=new TableColumn("Name");
        TableColumn value=new TableColumn("Value");
        varName.setCellValueFactory(new PropertyValueFactory<SymbolTableGUI,Integer>("varName"));
        value.setCellValueFactory(new PropertyValueFactory<SymbolTableGUI,String>("value"));
        symTable.setMaxWidth(163d);
        symTable.setMinWidth(163d);
        symTable.getColumns().addAll(varName,value);
        symTable.setItems(symTableData);

        anchorPane.setTopAnchor(symTable,20d);
        anchorPane.setBottomAnchor(symTable,20d);
        anchorPane.setRightAnchor(symTable,20d);
        anchorPane.setLeftAnchor(symTable,20d);

        anchorPane.getChildren().add(title);
        anchorPane.getChildren().add(symTable);

        return anchorPane;
    }

    private Node initPrgState(){
        prgState=new ListView<>(prgStateDataa);
        prgState.getSelectionModel().selectedIndexProperty().addListener(this::selectedPrgState);

        AnchorPane anchorPane=new AnchorPane();
        Label label =new Label("Program State");
        label.setFont(new Font(16d));
        label.setStyle("-fx-font-weight: bold");

        anchorPane.setRightAnchor(label,20d);
        anchorPane.setLeftAnchor(label,20d);


        anchorPane.setTopAnchor(prgState,20d);
        anchorPane.setBottomAnchor(prgState,20d);
        anchorPane.setRightAnchor(prgState,20d);
        anchorPane.setLeftAnchor(prgState,20d);

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(prgState);

        return anchorPane;
    }

    private Node initTop() {
        AnchorPane anchorPane=new AnchorPane();

        Label prgLabel=new Label("Number of Program States: ");
        prgLabel.setFont(new Font(16d));
        prgLabel.setStyle("-fx-font-weight: bold");


        Label label =new Label("Choose an execution mode: ");
        label.setFont(new Font(16d));
        label.setStyle("-fx-font-weight: bold");

        buttonOne.setFont(new Font(16d));
        buttonOne.setOnAction(this::executeOnce);
        buttonAll.setFont(new Font(16d));
        buttonAll.setOnAction(this::executeAll);

        HBox hBox=new HBox(10,prgLabel,nrField,label,buttonOne,buttonAll);
        anchorPane.setTopAnchor(hBox,20d);
        anchorPane.setBottomAnchor(hBox,20d);
        anchorPane.setRightAnchor(hBox,20d);
        anchorPane.setLeftAnchor(hBox,20d);

        anchorPane.getChildren().add(hBox);
        return anchorPane;
    }

    private void executeAll(ActionEvent actionEvent) {
        try {
            ctrl.executeAll();
            refreshTables();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void executeOnce(ActionEvent actionEvent) {
        ctrl.oneStepGUI();
        refreshTables();
    }

    private void selectedPrgState(javafx.beans.Observable e){
        prgStateIndex = prgState.getSelectionModel().getSelectedIndices().get(0);
        refreshSymbolTable();
        refreshExeStack();
    }

    private void refreshSymbolTable() {
        symTableData.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        try {
            ProgramState p = ctrl.getRepo().getProgramState(prgStateIndex);
            ISymbolTable<String,Integer> symTable =p.getTable();

            for(String k: symTable.keySet()){
                   symTableData.add(new SymbolTableGUI(k,symTable.get(k)));
            }

        }catch(Exception e){

        }
    }

    private void refreshPrgState(){
        prgStateDataa.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        List<String> s = ctrl.getProgramStatesIDCtrl();
        prgStateDataa.addAll(s);
        prgState.getSelectionModel().select(0);
    }

    private void refreshTextField(){
        nrField.clear();
        nrField.setText("" + ctrl.getNrPrgCtrl());
    }
    private void refreshExeStack(){
        exeStackData.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        try {
            ProgramState p = ctrl.getRepo().getProgramState(prgStateIndex);
            Iterable<Statement> stack=p.getExeStack().getAll();
            for(Statement s:stack){
                exeStackData.add(s.toString());
            }

        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    private void refreshHeapTable(){
        heapData.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        try {
            ProgramState p = ctrl.getRepo().getProgramState(prgStateIndex);
            IHeap<Integer,Integer> list =p.getHeap();
            for(Integer k: list.keySet()){
                heapData.add(new HeapTable(k.toString(),list.get(k)));
            }
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void refreshFileTable(){
        fileTableData.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        try {
            ProgramState p = ctrl.getRepo().getProgramState(prgStateIndex);
            IFileTable<Integer,FileData> list =p.getFileTable();
            for(Integer k: list.keySet()){
                fileTableData.add(new FileTableGUI(k,list.get(k).getFilename()));
            }
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void refreshOutput(){
        output.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        try {
            ProgramState p = ctrl.getRepo().getProgramState(prgStateIndex);
            IOut<Integer> out=p.getOut();
            output.setText(""+ out);
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void refreshTables(){
        refreshPrgState();
        refreshTextField();
        refreshExeStack();
        refreshSymbolTable();
        refreshHeapTable();
        refreshFileTable();
        refreshOutput();
    }

    public BorderPane getExecutionView(){
        return borderPane;
    }
}
