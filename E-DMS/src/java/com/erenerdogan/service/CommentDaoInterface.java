/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

/**
 *
 * @author eren
 */
public interface CommentDaoInterface {
    void addComment(int userID, int fileID, String title, String description);
    
}
