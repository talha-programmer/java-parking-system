package parkingsystem.BusinessLogic;

import parkingsystem.Database.WorkerDB;
import parkingsystem.Enums.UserTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Worker extends User{
    private static WorkerDB db = new WorkerDB();

    public ArrayList<Worker> getAllWorker(){
        return db.getAllWorker();
    }

    public HashMap<String, Integer> getAllWorkerUsernames(){
        HashMap<String, Integer> allUsernames = new HashMap<>();
        ArrayList<Worker> allWorkers = db.getAllWorker();
        for(Worker worker:allWorkers){
            int userId = worker.getId();
            String username = worker.getUsername();
            allUsernames.put(username, userId);
        }
        return allUsernames;
    }
}
