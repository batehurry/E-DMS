/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Loggers;
import java.util.List;

/**
 *
 * @author eren
 */
public interface LoggersDaoInterface {
    void addLoggers(Loggers loggers);
    List<Loggers> getAllLoggers();
}
