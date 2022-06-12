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

    public static String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        return strDate;
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

    public static String readFromFile() {
        String data = new String();
        StringBuilder builder = new StringBuilder();
        try {
            File myObj = new File(FILE_NAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine());
                builder.append('\n');
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data = builder.toString();
    }
    public static String [] convertToCSV (String text){

        String lines[] = text.split("\\r?\\n");

        List <String []> table = new ArrayList <String []>();

        System.out.println(lines.length);
        System.out.println(lines[0]);
        for (String line: lines) {
            String row [] = line.split(",");
            StringBuilder separated = new StringBuilder();
            for(String header: row){

                separated.append(header.replaceAll("\\s(?=(?:[^'\"`]*(['\"`])[^'\"`]*\\1)*[^'\"`]*$)", ","));
            }
        }

        return lines;
    }
}

