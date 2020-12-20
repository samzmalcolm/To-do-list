/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usama
 */
public class buttonHandler implements ActionListener{
    addTask ref1;
    mainFrame ref2;
    completedTasks ref3;
    specificDate ref4;
    
    
    
    
    public buttonHandler() {
        
    }
    
    
    
    public void setRef(addTask aa,mainFrame bb, completedTasks cc, specificDate dd)
    {
        ref1 = aa;
        ref2 = bb;
        ref3 = cc;
        ref4=dd;
    }

    public void addTask(String a, String b, String c)
    {
         //writing the data in file using function 
            ref1.addTaskInAllPanel(a, b, c);
            
            // clearing all the textfields
            ref1.text1.setText("");
            ref1.text2.setText("");
            ref1.dateTextField.setText("");
            
            // adding data to all task table
            String [] x= new String [3];
            x[0]=a;
            x[1]=b;
            x[2]=c;
            
            ref2.allModel.addRow(x);
            
            // visibility change of frames
            ref1.atFrame.setVisible(false);
            ref2.mainFrame.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("Add"))
        {
            // visibility change of frames by add button at main frame
            ref2.mainFrame.setVisible(false);
            ref1.atFrame.setVisible(true);
        }
        
         if(e.getActionCommand().equalsIgnoreCase("Back"))
        {
            // visibility change of frames by Back button at main frame
            ref2.mainFrame.setVisible(true);
            ref1.atFrame.setVisible(false);
        }
         
         if(e.getActionCommand().equalsIgnoreCase("Sort"))
         {
             ref2.allTableSort();
             ref2.priorityTableSort();
         }
         if(e.getActionCommand().equalsIgnoreCase("Specific Date"))
         {
             ref2.mainFrame.setVisible(false);
             ref4.specFrame.setVisible(true);
         }
         
         if(e.getActionCommand().equalsIgnoreCase("Go Back"))
         {
          
             ref4.dateTextField.setText("");
             ref4.specFrame.setVisible(false);
            ref4.removeAllTasks();
            ref4.removePriorityTasks();
            
             
             ref2.mainFrame.setVisible(true);
         }
         
          if(e.getActionCommand().equalsIgnoreCase("Search"))
         {
             
                String c=ref4.dateTextField.getText();
                
                ref4.dateTextField.setText("");
                
                //validations
                if("".equals(c))
                {
                    JOptionPane.showMessageDialog(ref4.specFrame, "Date Field is Empty");
                    
                }
                
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                
                Date date;
                
            try {
                date = sdf.parse(c);
                
                 if(!sdf.format(date).equals(c))
                {
                    JOptionPane.showMessageDialog(ref4.specFrame, "Invalid Date Format");
                         ref4.dateTextField.setText("");
                }
                else
                {
                    String [] g= c.split("/");
                    int year= Integer.parseInt(g[2]);
                   if(year>=2020 && year<=2050)
                    {
                        
                        Date currentDate= new Date();
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        Date input=f.parse(c);
                        if( input.compareTo(currentDate)>0)
                        {
                            
                        
                        
                        
                         try {
                        
                        
                        
                        ref4.getData(c);
                    } catch (IOException ex) {
                       
                    }
                        }else
                        {
                             JOptionPane.showMessageDialog(ref4.specFrame, "Invalid Date From Past");
                        }
                       
                    }else{
                        JOptionPane.showMessageDialog(ref4.specFrame, "Invalid Year ");
                             ref4.dateTextField.setText("");
                   }
                    
                    
                   
                }
            } catch (ParseException ex) {
               
            }
                
                ref4.dateTextField.setText("");
                
               
             
         }
         
         
        if(e.getActionCommand().equalsIgnoreCase("Completed Tasks"))
        {
            try {
              
                ref3.cTables();
                
            } catch (IOException ex) {
               
            }
            ref2.mainFrame.setVisible(false);
            ref3.cFrame.setVisible(true);
        }   
        
        if(e.getActionCommand().equalsIgnoreCase("Home"))
        {
            ref3.cFrame.setVisible(false);
            ref2.mainFrame.setVisible(true);
            
        }   
        
        
        if(e.getActionCommand().equalsIgnoreCase("Continue"))
        {
            try {
                // getting data from text fields
                String a=ref1.text1.getText();
                String b=ref1.text2.getText();
                String c=ref1.dateTextField.getText();
                
                
                
                //validations
                if("".equals(a)||"".equals(b)|| "".equals(c))
                {
                    JOptionPane.showMessageDialog(ref1.atFrame, "Fill all the Fields ");
                    
                }
                
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                
                Date date;
                
                date = sdf.parse(c);
                
                
                
                if(!sdf.format(date).equals(c))
                {
                    JOptionPane.showMessageDialog(ref1.atFrame, "Invalid Date Format");
                    ref1.text1.setText("");
                    ref1.text2.setText("");
                    ref1.dateTextField.setText("");
                }
                else
                {
                    String [] h= c.split("/");
                    int year = Integer.parseInt(h[2]);
                    if(year>=2020 && year<=2050)
                    {
                         Date currentDate= new Date();
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        Date input=f.parse(c);
                        if( input.compareTo(currentDate)>0)
                        {
                        
                        addTask(a, b, c);
                        
                        }else
                        {
                            JOptionPane.showMessageDialog(ref1.atFrame, "Invalid Date from Past");
                        }
                    }else
                    {
                         JOptionPane.showMessageDialog(ref1.atFrame, "Enter Valid Year");
                         ref1.text1.setText("");
                    ref1.text2.setText("");
                    ref1.dateTextField.setText("");
                       
                    }
                    
                    
                    
                }
            } catch (ParseException ex) {
                
            }
            
             ref1.text1.setText("");
                    ref1.text2.setText("");
                    ref1.dateTextField.setText("");
            
           
        }
         
         
       
    }
    
}