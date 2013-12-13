/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Groups;
import java.util.List;

/**
 *
 * @author eren
 */
public interface GroupSharedDaoInterface {
    List<GroupShared> getMyGroupShared(int id);
    void addGroupsShared(Files file,List<Groups> groupID);
    void editGroupsShared(Files file, List<Groups> groupID);
    void removeGroupsShared(Files file);
}
