/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.FileHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author helena
 */
public interface ReadingAdapter {
    
    public ArrayList parseFile(String fileName);
    
}
