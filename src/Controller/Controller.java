package Controller;
import Model.ArithmeticException;
import Model.ProgramState.ProgramState;
import Model.Statement.Statement;
import Model.Statement.StatementException;
import Utils.*;
import Repository.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by dell on 10/22/2016.
 */
public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository repo){
        this.repo=repo;
    }
    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAll(List<ProgramState> prgList) throws ControllerException {

        prgList.forEach(prg->{try{repo.logPrgStateExec(prg);}
                                catch(Exception e){System.out.println(e.getMessage());}
                                ;});
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)() -> {return p.executeOneStep();})
                .collect(Collectors.toList());
        try {
            List<ProgramState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }).filter(p -> p != null).collect(Collectors.toList());
            prgList.addAll(newPrgList);
            repo.setPrgStates(prgList);
        }catch(Exception e){
            throw new ControllerException(e.getMessage());
        }

    }
    public void executeAll() throws ControllerException {
        try {
            repo.clearFile();
        }catch(Exception e){
            throw new ControllerException(e.getMessage());
        }
        executor= Executors.newFixedThreadPool(2);
        while(true){
            List<ProgramState> prgList =removeCompletedPrg(repo.getPrgStates());
            if (prgList.size()==0)
                break;
            oneStepForAll(prgList);
        }
        executor.shutdownNow();
    }
    public IRepository getRepo(){
        return repo;
    }
    public Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){

        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    public void serializeCtrl(String filename) throws ControllerException {
        try {
            repo.serialize(repo.getProgramState(0),filename);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }
    public static ProgramState deserializeCtrl(String filename) throws ControllerException {
        try{
            return IRepository.deserialize(filename);
        }catch(Exception e){
            throw new ControllerException(e.getMessage());
        }
    }
    public int getNrPrg(){
        return repo.getNrPrg();
    }

    public List<String> getProgramStatesID(){
        List<ProgramState> list=repo.getPrgStates();
        List<String> listID= new ArrayList<>();

        for(ProgramState p: list){
            String str="Program State "+  p.getID();
            listID.add(str);
        }
        return listID;
    }
}
