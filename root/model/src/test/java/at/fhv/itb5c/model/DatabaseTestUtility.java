package at.fhv.itb5c.model;

import java.io.File;

public class DatabaseTestUtility {
    public static void deleteDatabaseFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
