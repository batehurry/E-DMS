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
public interface SearchDaoInterface {
    List<Files> searchTag(int userID, String tag);
}
