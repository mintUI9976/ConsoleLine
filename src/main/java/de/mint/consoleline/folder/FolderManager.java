package de.mint.consoleline.folder;

public interface FolderManager {

    boolean createFolder(String path);
    boolean deleteFolder(String path);
    boolean deleteFolderRecursive(String path);
}
