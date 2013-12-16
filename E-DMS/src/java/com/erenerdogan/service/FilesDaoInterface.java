/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import java.util.List;

/**
 *
 * @author eren
 */
public interface FilesDaoInterface {
    List<Files> myAllFiles(int userID);
    List<Files> getAllFiles();
    void uploadFile(Files file);
    Files getFileView(int id);    
    void deleteFile(int fileID);
    void editFile(Files file);
    List<Files> getAllDeadline();
    List<Files> getAllArchived();
}
