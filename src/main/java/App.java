import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args){


        Timer timer = new Timer(); // creating timer
        TimerTask task = new NewsTask(); // creating timer task
        timer.schedule(task, 0, 1800000);
        Utils.convertToCSV(Utils.readFromFile());
    }
}

