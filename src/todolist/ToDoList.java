/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usama
 */
public class ToDoList {

    public static void main(String[] args) throws IOException {
       
     Controller ctrl = new Controller();
     ctrl.setBindings();
     ctrl.b.mainFrame.setVisible(true);

       
        
    }
    
}
