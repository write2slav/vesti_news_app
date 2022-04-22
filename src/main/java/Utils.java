import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private final static String FILE_NAME = "listOfNewsHeader.txt";
    //CSS selector to select all the <a> elements contained in "h3".
    private final static String CSS_SELECTOR = "h3 a";
    // Website to parse
    private final static String URL = "https://www.vesti.ru/";

    public static List<String> getListOfNewsHeaders() {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).timeout(100 * 1000).get();
        } catch (IOException e) {
            System.out.println(e);
        }
        Elements selectedElements = doc.select(CSS_SELECTOR);

        //Create a list of text found in selected HTML elements
        List<String> list = new ArrayList<String>();
        for (Element element : selectedElements) list.add(element.text());

        return list;
    }

    public static void createFileIfNotExist() {
        try {
            File myObj = new File(FILE_NAME);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void appendToFile(String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.append(str);
        writer.append('\n');
        writer.close();
    }

    public static String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss Z");
        String strDate = dateFormat.format(date);

        return strDate;
    }

}

