import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLog {
    public synchronized static void writeToFile(String logString) {
        try {
            FileWriter fileLog = new FileWriter(new File(MyData.LOG_FILE), true);
            fileLog.write(logString);
            fileLog.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
