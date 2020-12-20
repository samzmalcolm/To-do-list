/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author usama
 */
public class completedTasks {
    
    
     
    JFrame cFrame;
    JButton button1;
    JLabel labell;
    JPanel cPanel; 
    JTable table1;
    
    ArrayList<String> completedTasks = new ArrayList<String>();
    
     DefaultTableModel allModel;
    
    public completedTasks() throws IOException
    {
        initCompletedTask();
    }
    
    public void cTable() throws FileNotFoundException, IOException
    {
        
         // clear array list's previous data 
         completedTasks.clear();
          
         
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("completedTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                completedTasks.add(Line);
            }
        }
        
        // getting the size of arraylist 
        int rowSize= completedTasks.size();
        
        
           //init 2d array for table data 
           String x[][]= new String[rowSize/3][3];
           
           
           // getting data from arraylist to 2d array
           int z=0, k=0,v=0;
           for(int i=0;i<rowSize/3;i++)
           {    
               x[k][z]=completedTasks.get(v);
               v++;
               x[k][z+1]=completedTasks.get(v);
               v++;
               x[k][z+2]=completedTasks.get(v);
               v++;
               k++;
           } 
          
           
           
           
           // table colomn names 
            String y[]={"Name","Description","Date"}; 
        
         allModel= new DefaultTableModel(x, y);
        
         // init table 
           table1=new JTable(allModel);  
          
           //table sorter 
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table1.getModel());
    table1.setRowSorter(sorter);
    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
     
    int columnIndexToSort = 0;
    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
     
    sorter.setSortKeys(sortKeys);
    sorter.sort();

           
           
           
          //setting colomn widths 
          TableColumnModel tcm = table1.getColumnModel();
          tcm.getColumn(0).setPreferredWidth(200);     //Name
          tcm.getColumn(1).setPreferredWidth(450);     //description
          tcm.getColumn(2).setPreferredWidth(150);    // date 

          // setting row size 
          table1.setRowHeight(25);
         JScrollPane tableContainer1 = new JScrollPane(table1);
          // adding table container in allpanel 
          cPanel.add(tableContainer1, BorderLayout.CENTER);
         
        cFrame.add(cPanel); 
    }
    
    
    public void initCompletedTask() throws FileNotFoundException, IOException
    {
        // init add task frame
        cFrame = new JFrame("Completed Tasks");
        
        // setting layout null 
        cFrame.setLayout(null);
        
        // init buttons and text fields
       
        button1 = new JButton("Home");
        
        
        // init Jlabels
        labell = new JLabel("Completed Tasks");
       
        
        // label and text field bounds
        labell.setBounds(260, 10, 140, 30);
       
        
        // adding text fileds and labels in atframe
        cFrame.add(labell);
       
        cPanel = new JPanel();
        cPanel.setLayout(new BorderLayout());
        cPanel.setSize(540, 360);
        cPanel.setLocation(20,40);
        
        
        cTable();
        
        // setting buttons 
        button1.setBounds(230, 415, 140, 30);
       
        
        
        
        
        // adding buttons to atFrame
        cFrame.add(button1);
       
        
    
        // main frame settings 
        cFrame.setLocation(80, 80);
        cFrame.setSize(600, 500);
        
        
        
        cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
    }
    
       public void cTables() throws FileNotFoundException, IOException
    {
        
        ArrayList<String> newcompletedTasks = new ArrayList<String>();
        
         // clear array list's previous data 
         newcompletedTasks.clear();
          
         
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("completedTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                newcompletedTasks.add(Line);
            }
        }
        
        // getting the size of arraylist 
        int completedSize= completedTasks.size();
        int newCompletedSize= newcompletedTasks.size();
        
         
        if( newCompletedSize-completedSize != 0)
        {
            int diff= newCompletedSize-completedSize;
            String [] d= new String[3];
          
            
             int k= newCompletedSize;
            for(int i=0; i<diff/3;i++)
            {
              
                String x=newcompletedTasks.get(k - 1);
                d[2]=x;
                
                String x1=newcompletedTasks.get(k - 2);
                d[1]=x1;
                
                String x2=newcompletedTasks.get(k - 3);
                d[0]=x2;
               
                allModel.addRow(d);
                table1.repaint();
                
                completedTasks.add(d[0]);
                completedTasks.add(d[1]);
                completedTasks.add(d[2]);
                
                k=k-3;
                
            }
            
        }
        
        
        
        
    }
    
}
