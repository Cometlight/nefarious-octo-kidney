package at.fhv.itb5c.model;

import java.io.File;

/**
 * Some utility methods for database tests.
 */
public class DatabaseTestUtility {
    public static void deleteDatabaseFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        
        File recoveryFile = new File(filename + "$");
        if (recoveryFile.exists()) {
        	recoveryFile.delete();
        }
    }
}
