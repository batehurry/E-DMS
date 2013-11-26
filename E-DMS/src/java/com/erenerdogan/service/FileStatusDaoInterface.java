/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.FileStatus;
import java.util.List;

/**
 *
 * @author eren
 */
public interface FileStatusDaoInterface {
    List<FileStatus> getAllFileStatus();
    FileStatus getFileStatus(int id);
    
}
