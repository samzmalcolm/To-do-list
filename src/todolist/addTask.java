/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author usama
 */
public final class addTask {
    
    JFrame atFrame;
    JButton button1;
    JButton button2;
    JLabel labell;
    JLabel label2;
    JLabel label3;
    JTextField text1; 
    JTextField text2; 
    JFormattedTextField dateTextField;
    
    public addTask()
    {
        initAddTask();
    }
    
    public void initAddTask()
    {
        // init add task frame
        atFrame = new JFrame("Add a Task");
        
        // setting layout null 
        atFrame.setLayout(null);
        
        // init buttons and text fields
        button2 = new JButton("Continue");
        button1 = new JButton("Back");
        
        text1= new JTextField();
        text2= new JTextField();
        
        
        // init Jlabels
        labell = new JLabel("Name");
        label2 = new JLabel("Description");
        label3 = new JLabel("DeadLine Date");
        JLabel label4= new JLabel("* Must Follow the Format (dd/mm/yyyy) OR (d/m/yyyy)");
        
        
        // label and text field bounds
        labell.setBounds(100, 100, 140, 30);
        label2.setBounds(100, 200, 140, 30);
        label3.setBounds(100, 300, 140, 30);
        
        text1.setBounds(200, 100, 300, 30);
        text2.setBounds(200, 200, 300, 30);
    
        
        // adding text fileds and labels in atframe
        atFrame.add(labell);
        atFrame.add(label2);
        atFrame.add(label3);
        atFrame.add(text1);
        atFrame.add(text2);
        
        
          
        
        // setting buttons 
        button1.setBounds(150, 400, 140, 30);
        button2.setBounds(320, 400, 140, 30);
        
        
        
        
        // adding buttons to atFrame
        atFrame.add(button1);
        atFrame.add(button2);
        
       //checking date 
      DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
      dateTextField = new JFormattedTextField(format);
      
      dateTextField.setBounds(200, 300, 300, 30);
      label4.setBounds(200, 270, 300, 30);
      atFrame.add(label4);
      atFrame.add(dateTextField);
       
       
       
        
        
        // main frame settings 
        atFrame.setLocation(80, 80);
        atFrame.setSize(600, 500);
        
        
        
        atFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
    }
    
    public void addTaskInAllPanel(String name, String desc, String date)
    {
        
      
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("allTasks", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(name);
            pw.println(desc);
            pw.println(date);

           
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException ex) {
         
        

       
        }
     
    }
    

}
