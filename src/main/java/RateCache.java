import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aldres on 08.02.2018.
 */
public class RateCache {

    private File file;
    private String cacheFolder;

    private void writeToCache(ApiResponse response){
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fileWriter);
            String cachedString = buildCachedString(response);
            br.write(cachedString);
            br.newLine();
            br.close();

        } catch (IOException e) {
            System.out.println("Error, while caching: " + e.getMessage());
        }

    }

    private String buildCachedString(ApiResponse response) {
        StringBuilder cachedStringBuilder = new StringBuilder()
                .append(response.getBase())
                .append(" ")
                .append(response.getRates().getName())
                .append(" ")
                .append(response.getRates().getRate());
        return cachedStringBuilder.toString();
    }

    private void readCache() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] singleLine = line.split(" ");
                ApiResponse item = new ApiResponse("test", "test");
                item.setBase(singleLine[0]);
                item.getRates().setName(singleLine[1]);
                item.getRates().setRate(Double.valueOf(singleLine[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentDateFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date) + ".txt";
    }

    private boolean isFileFolderEmpty() {
        File cacheFolder = new File("\\src\\main\\resources");
        return cacheFolder.list().length > 0;
    }

    private void createFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void createCacheFile() {
        String currentFileName = getCurrentDateFileName();
        if (isFileFolderEmpty()) {
            file = new File(cacheFolder = "\\" + currentFileName);
            createFile();
        } else {
            File[] files = new File(cacheFolder).listFiles();
            String existingFileName = files[0].getName();
            if (existingFileName.equals(currentFileName)){
                file = files[0];
            } else {
                files[0].delete();
                file = new File(cacheFolder + "\\" + currentFileName);
                createFile();
            }
        }
    }

}
