/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Comments;
import java.util.List;

/**
 *
 * @author eren
 */
public interface CommentDaoInterface {
    void addComment(int userID, int fileID, String title, String description);
    boolean removeComment(int userID, int commentID);
    List<Comments> getAllComment(int fileID);
}
