import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

public class NewsTask extends TimerTask  {
    public void run() {
        System.out.println("Task is running");
        List<String> listOfNewsHeaders = Utils.getListOfNewsHeaders();
        listOfNewsHeaders.add(0, Utils.getDate());
        String datedNewsHeaders = listOfNewsHeaders.toString().substring(1, listOfNewsHeaders.toString().length() - 1);

        Utils.createFileIfNotExist();
        try {
            Utils.appendToFile(datedNewsHeaders);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Task is finished");

    }
}
