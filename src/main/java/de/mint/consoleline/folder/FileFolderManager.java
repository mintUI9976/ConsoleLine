package de.mint.consoleline.folder;

import java.io.File;

public class FileFolderManager implements FolderManager {
  @Override
  public boolean createFolder(String path) {
    final File folder = new File(path);

    if (!folder.exists()) {
      return folder.mkdirs();
    }
    return false;
  }

  @Override
  public boolean deleteFolder(String path) {
    final File folder = new File(path);

    if (folder.exists() && folder.isDirectory()) {
      return folder.delete();
    }
    return false;
  }

  @Override
  public boolean deleteFolderRecursive(String path) {
    final File folder = new File(path);

    if (folder.exists()) {
      return deleteRecursive(folder);
    }
    return false;
  }

  private boolean deleteRecursive(File file) {
    if (file.isDirectory()) {
      final File[] files = file.listFiles();
      if (files != null) {
        for (final File filer : files) {
          deleteRecursive(filer);
        }
      }
    }
    return file.delete();
  }
}
