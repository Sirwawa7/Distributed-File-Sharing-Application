import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    private static final String STORAGE_PATH = "storage/";

    public void uploadFile(String fileName, byte[] fileBytes) throws IOException {
        Files.write(Paths.get(STORAGE_PATH + fileName), fileBytes);
    }

    public byte[] downloadFile(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(STORAGE_PATH + fileName));
    }
}