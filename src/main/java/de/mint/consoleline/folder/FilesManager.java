package de.mint.consoleline.folder;

import java.io.File;
import java.io.IOException;

public class FilesManager implements FileManager{


    @Override
    public boolean createFile(String path) {
        final File file = new File(path);

        if (file.exists()) {
            return false;
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
