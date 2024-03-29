import java.io.*;
import java.net.URL;
import java.nio.file.*;

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
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                System.out.format("Line#%d: %s \n", counter++, line);
                writer.newLine();
            }


            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}