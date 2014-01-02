/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Works;
import java.util.List;

/**
 *
 * @author eren
 */
public interface WorksDaoInterface {
    List<Works> getMyWorks(int userID);
    void addWork(Works w);
    List<Works> getFileWork(int fileID);
    void changeWorkStatus(int userID, int fileID);
}
