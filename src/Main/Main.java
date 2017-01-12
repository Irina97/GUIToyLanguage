package Main;
import Model.Expression.*;
import Model.ProgramState.ProgramState;
import Model.Statement.*;
import Repository.*;
import Controller.*;
import Utils.*;
import View.*;

import javax.swing.plaf.nimbus.State;
import java.util.Scanner;

/**
 * Created by dell on 10/16/2016.
 */
public class Main {
    public static void main(String[] args) {
        //Statement 1
        Statement s1 = new CompStmt(new AssignStmt("v",new ConstExpr(2)),new PrintStmt(new VarExpr("v")));
        ProgramState prg1=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s1);
        IRepository repo1=new Repository("logFile.txt");
        repo1.addProgramState(prg1);
        Controller ctrl1=new Controller(repo1);

        //Statement 2
        Statement s2=new CompStmt(
                new AssignStmt("a", new AritmExpr('+',new ConstExpr(2),new AritmExpr('*',new ConstExpr(3),new ConstExpr(5)))),
                new CompStmt(new AssignStmt("b",new AritmExpr('+',new VarExpr("a"),new ConstExpr(1))),
                            new PrintStmt(new VarExpr("b"))));
        ProgramState prg2=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                                            new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s2);
        IRepository repo2=new Repository("logFile.txt");
        repo2.addProgramState(prg2);
        Controller ctrl2=new Controller(repo2);

        //Statement 3
        Statement s3 = new CompStmt(new AssignStmt("a",new AritmExpr('-',new ConstExpr(2),new ConstExpr(2))),
                                    new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)),
                                                            new AssignStmt("v",new ConstExpr(3))),
                                                new PrintStmt(new VarExpr("v"))));
        ProgramState prg3=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s3);
        IRepository repo3=new Repository("logFile.txt");
        repo3.addProgramState(prg3);
        Controller ctrl3=new Controller(repo3);

        //Statement 4
        OpenFileStmt open=new OpenFileStmt("var_f","test.txt");
        ReadFileStmt read=new ReadFileStmt(new VarExpr("var_f"),"var_c");
        PrintStmt print=new PrintStmt(new VarExpr("var_c"));
        PrintStmt printZero=new PrintStmt(new ConstExpr(0));
        CloseFileStmt close=new CloseFileStmt(new VarExpr("var_f"));

        Statement s4=new CompStmt(new CompStmt(open,read),new CompStmt(new CompStmt(print,read),new CompStmt(print,close)));
        ProgramState prg4=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s4);
        IRepository repo4=new Repository("logFile.txt");
        repo4.addProgramState(prg4);
        Controller ctrl4=new Controller(repo4);

        //Statement 5
        Statement s5=new CompStmt(new CompStmt(open,read),new CompStmt(print, new CompStmt
                (new IfStmt(new VarExpr("var_c"),new CompStmt(read,print),printZero),close)));

        ProgramState prg5=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s5);
        IRepository repo5=new Repository("logFile.txt");
        repo5.addProgramState(prg5);
        Controller ctrl5=new Controller(repo5);

        //Statement 6
        OpenFileStmt open6=new OpenFileStmt("var_f","test.txt");
        ReadFileStmt read6=new ReadFileStmt(new AritmExpr('+',new VarExpr("var_f"),new ConstExpr(2)),"var_c");
        CloseFileStmt close6=new CloseFileStmt(new VarExpr("var_f"));
        PrintStmt print6=new PrintStmt(new VarExpr("var_c"));

        Statement s6=new CompStmt(new CompStmt(open6,read6),new CompStmt(print,
                new CompStmt(new IfStmt(new VarExpr("var_c"),new CompStmt(read,print6),printZero),close6)));
        ProgramState prg6=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s6);
        IRepository repo6=new Repository("logFile.txt");
        repo6.addProgramState(prg6);
        Controller ctrl6=new Controller(repo6);

        //Statement 7 Create new Heap Entry
        Statement s7=new CompStmt(new NewHeapEntry("v",new ConstExpr(20)),new CompStmt(new NewHeapEntry("a",new ConstExpr(22)),
                new PrintStmt(new VarExpr("v"))));
        ProgramState prg7=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s7);
        IRepository repo7=new Repository("logFile.txt");
        repo7.addProgramState(prg7);
        Controller ctrl7=new Controller(repo7);

        //Statement 8 Read from the heap
        Statement s8=new CompStmt(new CompStmt(new NewHeapEntry("v",new ConstExpr(20)),new NewHeapEntry("a",new ConstExpr(22))),
                                    new CompStmt(new PrintStmt(new AritmExpr('+',new ConstExpr(100),new ReadHeapExpr("v"))),
                                                new PrintStmt(new AritmExpr('+',new ConstExpr(100),new ReadHeapExpr("a")))));
        ProgramState prg8=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s8);
        IRepository repo8=new Repository("logFile.txt");
        repo8.addProgramState(prg8);
        Controller ctrl8=new Controller(repo8);

        Statement s9=new CompStmt(new CompStmt(new NewHeapEntry("v",new ConstExpr(20)),new NewHeapEntry("a",new ConstExpr(22))),
                new CompStmt(new WriteHeapStmt("a",new ConstExpr(30)),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new PrintStmt(new ReadHeapExpr("a")))));

        ProgramState prg9=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s9);
        IRepository repo9=new Repository("logFile.txt");
        repo9.addProgramState(prg9);
        Controller ctrl9=new Controller(repo9);

        //Statement 10 Write in the heap
        Statement s10=new CompStmt(new CompStmt(new NewHeapEntry("v",new ConstExpr(20)),new NewHeapEntry("a",new ConstExpr(22))),
                                    new CompStmt(new CompStmt(new WriteHeapStmt("a",new ConstExpr(30)),new PrintStmt(new VarExpr("a"))),
                                            new CompStmt(new PrintStmt(new ReadHeapExpr("a")), new AssignStmt("a",new ConstExpr(0)))));

        ProgramState prg10=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s10);
        IRepository repo10=new Repository("logFile.txt");
        repo10.addProgramState(prg10);
        Controller ctrl10=new Controller(repo10);

        //Statement 11 Comparison Expression
        Statement s11= new AssignStmt("a",new AritmExpr('+',new ConstExpr(10),new ComparisonExpr("<",new ConstExpr(2),new ConstExpr(6))));
        ProgramState prg11=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s11);
        IRepository repo11=new Repository("logFile.txt");
        repo11.addProgramState(prg11);
        Controller ctrl11=new Controller(repo11);

        //Statement 12 Comparison Expression
        Statement s12=new AssignStmt("a",new ComparisonExpr("<",new AritmExpr('+',new ConstExpr(10),new ConstExpr(2)),
                                                                new ConstExpr(6)));

        ProgramState prg12=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s12);
        IRepository repo12=new Repository("logFile.txt");
        repo12.addProgramState(prg12);
        Controller ctrl12=new Controller(repo12);
        //Statement 13 While Statement
        Statement s13= new CompStmt(new AssignStmt("v",new ConstExpr(6)),
                new CompStmt(new WhileStmt(new AritmExpr('-',new VarExpr("v"),new ConstExpr(4)),
                        new CompStmt(new PrintStmt(new VarExpr("v")), new AssignStmt("v",
                                        new AritmExpr('-',new VarExpr("v"),new ConstExpr(1))))),new PrintStmt(new VarExpr("v"))));

        ProgramState prg13=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s13);
        IRepository repo13=new Repository("logFile.txt");
        repo13.addProgramState(prg13);
        Controller ctrl13=new Controller(repo13);

        //Statment 14 Fork Statement

        Statement s14= new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new NewHeapEntry("a",new ConstExpr(22))),
                                    new CompStmt(new ForkStmt(new WriteHeapStmt("a",new ConstExpr(30))),
                                            new CompStmt(new ForkStmt(new AssignStmt("v",new ConstExpr(32))),
                                                    new CompStmt(new ForkStmt(new PrintStmt(new VarExpr("v"))),
                                                            new CompStmt(new ForkStmt(new PrintStmt(new ReadHeapExpr("a"))),
                                                                    new CompStmt(new PrintStmt(new VarExpr("v")),new PrintStmt( new ReadHeapExpr("a"))))))));

        ProgramState prg14=new ProgramState(new ExeStack<Statement>(), new SymbolTable<String,Integer>(),
                new Out<Integer>(),new FileTable<Integer,FileData>(),new Heap<Integer,Integer>(),s14);
        IRepository repo14=new Repository("logFile.txt");
        repo14.addProgramState(prg14);
        Controller ctrl14=new Controller(repo14);

        TextMenu menu=new TextMenu();
        menu.addCommand(new ExitCommand(0,"Exit"));
        menu.addCommand(new RunExample(1,s1.toString(),ctrl1));
        menu.addCommand(new RunExample(2,s2.toString(),ctrl2));
        menu.addCommand(new RunExample(3,s3.toString(),ctrl3));
        menu.addCommand(new RunExample(4,s4.toString(),ctrl4));
        menu.addCommand(new RunExample(5,s5.toString(),ctrl5));
        menu.addCommand(new RunExample(6,s6.toString(),ctrl6));
        menu.addCommand(new RunExample(7,s7.toString(),ctrl7));
        menu.addCommand(new RunExample(8,s8.toString(),ctrl8));
        menu.addCommand(new RunExample(9,s9.toString(),ctrl9));
        menu.addCommand(new RunExample(10,s10.toString(),ctrl10));
        menu.addCommand(new RunExample(11,s11.toString(),ctrl11));
        menu.addCommand(new RunExample(12,s12.toString(),ctrl12));
        menu.addCommand(new RunExample(13,s13.toString(),ctrl13));
        menu.addCommand(new RunExample(14,s14.toString(),ctrl14));
        menu.addCommand(new Serialize(15,"Serialize Program State",menu));
        menu.addCommand(new Deserialize(16,"Deserialize Program State",menu));
        menu.show();
    }
}