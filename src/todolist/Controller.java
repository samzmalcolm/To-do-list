/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.io.IOException;

/**
 *
 * @author usama
 */
public class Controller {
    addTask a;
    mainFrame b;
    buttonHandler hnd;
    completedTasks c;
    specificDate d;
    
    public Controller() throws IOException
    {
        a= new addTask();
        b= new mainFrame();
        c= new completedTasks();
        d= new specificDate();
        hnd= new buttonHandler();
    }
    
    public void setBindings() throws IOException
    {
        a.button1.addActionListener(hnd);
        b.button1.addActionListener(hnd);
        a.button2.addActionListener(hnd);
        b.button3.addActionListener(hnd);
        c.button1.addActionListener(hnd);
        b.sort.addActionListener(hnd);
        b.button2.addActionListener(hnd);
        d.goBack.addActionListener(hnd);
        d.search.addActionListener(hnd);
        hnd.setRef(a, b,c,d);
       
    }
    
 
}
