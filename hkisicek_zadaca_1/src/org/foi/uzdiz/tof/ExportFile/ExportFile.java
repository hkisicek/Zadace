/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.ExportFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author helena
 */
public class ExportFile {
    String content;
    
    public void WritetoFile(String content, String filename){
            try{
            // Create new file
            this.content=content;
            
            String path=getPath()+"/"+filename;
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(content);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String getPath(){
        
        Path currentRelativePath = Paths.get("");
       
        String s = currentRelativePath.toAbsolutePath().toString();
        return s;
    }
}
