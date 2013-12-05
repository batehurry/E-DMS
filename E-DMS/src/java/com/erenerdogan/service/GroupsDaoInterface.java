/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import java.util.List;
import java.util.Set;

/**
 *
 * @author eren
 */
public interface GroupsDaoInterface {
    List<Groups> getUserGroups(int id);
    List<Groups> getAllGroups();
    Set<Groups> getHierarchyGroup(List<Groups> group);
    void editGroups(Users user, Set<Groups> group);
}
