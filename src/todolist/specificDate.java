/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author usama
 */
public class specificDate {
    
    
     JFrame specFrame;
    JPanel pPanel;
    JPanel aPanel;
    JButton search;
    JButton goBack;
   
    JLabel format;
    JLabel label;
    JLabel label2;
    JLabel label3;
    
    JTable pTable;
    JTable aTable;
    
   JFormattedTextField dateTextField;
    
    
    
    ArrayList<String> allTasksData = new ArrayList<String>();
    ArrayList<String> priorityTasksData = new ArrayList<String>();
    
   
    DefaultTableModel allModel;
    DefaultTableModel priorityModel;
    
    
    
    public specificDate()
    {
        initSpecDate();
    }
    
    
    public void initSpecDate()
    {
        
        // init main frame 
        specFrame= new JFrame("Search Tasks");
        
        
        
        // setting mainFrame layout 
        specFrame.setLayout(null);
        
        
        // init panel 
        aPanel= new JPanel();
        pPanel= new JPanel();
 
        // panel layout 
        aPanel.setLayout(new BorderLayout());
        pPanel.setLayout(new BorderLayout());
        
        
     
       

       
        
        
        initPriorityTable();
          
          
        initAllTable();
        
        
        
        
        // all panel settings 
        pPanel.setSize(760, 200);
        pPanel.setLocation(10,190);
        pPanel.setBackground(Color.white);
        
        
        
        // priority panel 
        aPanel.setSize(760, 200);
        aPanel.setLocation(10,440);
        aPanel.setBackground(Color.white);
       
        
        // adding the panel in main frame
       
        specFrame.add(pPanel);
        specFrame.add(aPanel);
        
        
        // init buttons 
        search= new JButton("Search");
        goBack= new JButton("Go Back");
       
      
        
        
        // buttons settings 
        search.setBounds(400, 100, 140, 30);
        goBack.setBounds(250, 100, 140, 30);
        
        JLabel labelx= new JLabel("Due Date");
        labelx.setBounds(200, 50, 140, 30);
        specFrame.add(labelx);
        
        DateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
      dateTextField = new JFormattedTextField(formate);
      dateTextField.setBounds(300, 50, 280, 30);
      specFrame.add(dateTextField);
        
        // Jlabels 
        label = new JLabel("* Must Follow the Format (dd/mm/yyyy) OR (d/m/yyyy)");
        label3 = new JLabel("All Tasks");
        label2 = new JLabel("Priority Tasks");
       
       
        
        // JLebel Settings
        
        label.setBounds(300, 25, 300, 30);
        specFrame.add(label);
        
         label2.setBounds(350, 150, 140, 30);
        specFrame.add(label2);
        
         label3.setBounds(350, 400, 140, 30);
       specFrame.add(label3);
        
        
        
        
      
        // adding buutons to  main frame
        specFrame.add(search);
        specFrame.add(goBack);
      
        
        
        // main frame settings 
      specFrame.setLocation(50, 20);
      specFrame.setSize(800, 700);
        
        
        
        specFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initPriorityTable()
    {
        
           //init 2d array for table data 
           String x[][] = new String [0][3];
         
           
         
           
           // table colomn names 
            String y[]={"Name","Description","Date"}; 
        
        
        priorityModel=new DefaultTableModel(x, y);
            
            
         // init table 
           pTable=new JTable(priorityModel);  
          
          //setting colomn widths 
          TableColumnModel tcm = pTable.getColumnModel();
          tcm.getColumn(0).setPreferredWidth(200);     //Name
          tcm.getColumn(1).setPreferredWidth(450);     //description
          tcm.getColumn(2).setPreferredWidth(150);    // date 

          // setting row size 
          pTable.setRowHeight(25);
          
          
          //change the background color of table 
          pTable.setBackground(Color.ORANGE);
          
          
         
          
        JScrollPane tableContainer1 = new JScrollPane(pTable);
          // adding table container in allpanel 
          pPanel.add(tableContainer1, BorderLayout.CENTER);
          
          
        
    }
    
      public void initAllTable()
    {
        
           //init 2d array for table data 
           String x[][] = new String [0][3];
         
           
         
           
           // table colomn names 
            String y[]={"Name","Description","Date"}; 
        
        
        allModel=new DefaultTableModel(x, y);
            
            
         // init table 
           aTable=new JTable(allModel);  
          
          //setting colomn widths 
          TableColumnModel tcm = aTable.getColumnModel();
          tcm.getColumn(0).setPreferredWidth(200);     //Name
          tcm.getColumn(1).setPreferredWidth(450);     //description
          tcm.getColumn(2).setPreferredWidth(150);    // date 

          // setting row size 
          aTable.setRowHeight(25);
          
          
          //change the background color of table 
          aTable.setBackground(Color.ORANGE);
          
          
         
          
        JScrollPane tableContainer1 = new JScrollPane(aTable);
          // adding table container in allpanel 
          aPanel.add(tableContainer1, BorderLayout.CENTER);
          
          
        
    }
    
    
    
    
     public void getData(String date) throws FileNotFoundException, IOException
    {
        // clear array list's previous data 
         priorityTasksData.clear();
         allTasksData.clear();
         
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("priorityTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                priorityTasksData.add(Line);
            }
        }
        
        
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("allTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                allTasksData.add(Line);
            }
        }
        
        
        removeAllTasks();
        removePriorityTasks();
       
        
        
        
        
        // getting the size of arraylist 
        int pSize= priorityTasksData.size();
        int aSize= allTasksData.size();
        int pcounter=0;
        int acounter=0;
        
        
        
        String [] d= new String [3];
        
        for (int i=2;i< pSize;i+=3)
        {
            if(date.equals(priorityTasksData.get(i)))
            {
                d[2]=priorityTasksData.get(i);
                d[1]=priorityTasksData.get(i-1);
                d[0]=priorityTasksData.get(i-2);
                pcounter++;
                priorityModel.addRow(d);
                pTable.repaint();
            }
        }
        
        
         String [] e= new String [3];
        
        for (int i=2;i< aSize;i+=3)
        {
            if(date.equals(allTasksData.get(i)))
            {
                e[2]=allTasksData.get(i);               
                e[1]=allTasksData.get(i-1);
                e[0]=allTasksData.get(i-2);
                acounter++;
                allModel.addRow(e);
                aTable.repaint();
            }
        }
          
     if(acounter==0 && pcounter==0)
     {
          JOptionPane.showMessageDialog(specFrame, "No Task Found");
     }
        
        
    }
    
    public void removeAllTasks()
    {
         while(allModel.getRowCount()>0)
        {
            allModel.removeRow(0);
        }
    }
    
    public void removePriorityTasks()
    {
         while(priorityModel.getRowCount()>0)
        {
            priorityModel.removeRow(0);
        }
    }
}
