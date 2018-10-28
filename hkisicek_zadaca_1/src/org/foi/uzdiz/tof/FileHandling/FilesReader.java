/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.FileHandling;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /*
 * @author helena
 */
public class FilesReader {
    
    public boolean checkType(String filename){
        
        String syntax = "([\\a-zA-Z0-9_/.]*.txt)";
        Pattern pattern = Pattern.compile(syntax);
        Matcher m = pattern.matcher(filename);
        boolean status = m.matches();
        return status;
    }
}
