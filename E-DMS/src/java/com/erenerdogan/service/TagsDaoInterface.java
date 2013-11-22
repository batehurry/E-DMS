/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;

/**
 *
 * @author eren
 */
public interface TagsDaoInterface {
    void addTags(Files file, String tag);
}
