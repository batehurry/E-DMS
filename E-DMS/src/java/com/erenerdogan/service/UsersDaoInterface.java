/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Users;
import java.util.List;

/**
 *
 * @author eren
 */
public interface UsersDaoInterface {
    Users authenticate(String email, String password);
    boolean createUser(String email,String password,String name,String surname);
    Users getUser(int id);
    void changePassword(int id,String newPassword);
    List<Users> getAllUsers();
    void deleteUser(int id);
    void updateUser(int id, Users user);
}
