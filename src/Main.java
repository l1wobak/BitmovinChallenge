import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String URL = "https://www.w3.org/TR/2003/REC-PNG-20031110/iso_8859-1.txt";

    private static final String LOCALDIRECTORY = System.getProperty("user.dir");
    private static final String SAVEPATH = LOCALDIRECTORY + File.separator + "downloadedFile.txt";

    public static void main(String[] args) {

        try {
            Files.deleteIfExists(Paths.get(SAVEPATH));

            URL url = new URL(URL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(SAVEPATH));

            String line;
            int counter=0;
            char[] charArray;
            Map<Character, Integer> charCountMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                System.out.format("Line#%d: %s \n", counter++, line);
                writer.newLine();
                charArray=line.toCharArray();
                for (char c : charArray){
                    charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
                }
            }

            List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(charCountMap.entrySet());
            sortedEntries.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());

            for (Map.Entry<Character, Integer> entry : sortedEntries) {
                System.out.format("%s: %d \n", entry.getKey(), entry.getValue());
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}