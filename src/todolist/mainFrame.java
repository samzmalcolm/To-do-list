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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public final class mainFrame {
    
    JFrame mainFrame;
    JPanel priorityPanel;
    JPanel allPanel;
    JButton button1;
    JButton button2;
    JButton button3;
    JLabel labell;
    JLabel label2;
    JLabel label3;
    JTable table;
    JTable table1;
    
    JButton sort;
    
    JLabel label4;
    JLabel label5;
    JLabel label6;
    
    ArrayList<String> allTasksData = new ArrayList<String>();
    ArrayList<String> priorityTasksData = new ArrayList<String>();
    
    buttonHandler btn;
    DefaultTableModel allModel;
    DefaultTableModel priorityModel;
    
    public mainFrame() throws IOException
    {
        initMainFrame();
    }
    
    public void allTasksTable() throws FileNotFoundException, IOException
    {
        // clear array list's previous data 
         allTasksData.clear();
          
         
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("allTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                allTasksData.add(Line);
            }
        }
        
        // getting the size of arraylist 
        int rowSize= allTasksData.size();
        
        
           //init 2d array for table data 
           String x[][]= new String[rowSize/3][3];
           
           
           // getting data from arraylist to 2d array
           int z=0, k=0,v=0;
           for(int i=0;i<rowSize/3;i++)
           {    
               x[k][z]=allTasksData.get(v);
               v++;
               x[k][z+1]=allTasksData.get(v);
               v++;
               x[k][z+2]=allTasksData.get(v);
               v++;
               k++;
           } 
          
           
           // table colomn names 
            String y[]={"Name","Description","Date"}; 
        
         allModel= new DefaultTableModel(x, y);
        
         // init table 
        table1=new JTable(allModel);  
          
          //setting colomn widths 
          TableColumnModel tcm = table1.getColumnModel();
          tcm.getColumn(0).setPreferredWidth(200);     //Name
          tcm.getColumn(1).setPreferredWidth(450);     //description
          tcm.getColumn(2).setPreferredWidth(150);    // date 

          // setting row size 
          table1.setRowHeight(25);
          
         // init popup menu 
         final JPopupMenu popupMenu = new JPopupMenu();
          JMenuItem priority = new JMenuItem("   Priority           ");
          
          // adding action listner 
          priority.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                // getting the selected row 
                int selectedRow = table1.getSelectedRow();
               
                //converting the row index
                selectedRow = table1.convertRowIndexToModel(selectedRow);
                
                // getting values of the selected row 
                String val1 = (String)table1.getModel().getValueAt(selectedRow, 0);
                String val2 = (String)table1.getModel().getValueAt(selectedRow, 1);
                String val3 = (String)table1.getModel().getValueAt(selectedRow, 2);
                
                
                // adding these selected row values to priorityTasks file
                addTaskInPriorityPanel(val1, val2, val3);
                
                // adding data to priority table panel 
             String [] d= new String [3];
             d[0]=val1;
             d[1]=val2;
             d[2]=val3;
                
                priorityModel.addRow(d);
                
                
                // remove the selected row from alltask table 
                allModel.removeRow(selectedRow);
                
                
                
                 selectedRow++;
               int r3= selectedRow*3;
               int r2=r3 - 1;
               int r1= r2 - 1;
               
               
               
                try {
                    removeLineFromAll(r3);
                    removeLineFromAll(r2);
                    removeLineFromAll(r1);
                    
                    
                } catch (IOException ex) {
                    
                }
                
                // popup dialog message 
                JOptionPane.showMessageDialog(allPanel, "Task Added to Priority List");
                
            }
        });
          
          JMenuItem remove = new JMenuItem("   Remove            ");
          remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                  // getting the selected row 
                int selectedRow = table1.getSelectedRow();
               
                //converting the row index
                selectedRow = table1.convertRowIndexToModel(selectedRow);
               
                // remove selected row 
                allModel.removeRow(selectedRow);
                
                
               selectedRow++;
               int r3= selectedRow*3;
               int r2=r3 - 1;
               int r1= r2 - 1;
               
               
               
                try {
                    removeLineFromAll(r3);
                    removeLineFromAll(r2);
                    removeLineFromAll(r1);
                    
                    
                } catch (IOException ex) {
                    
                }
              
                JOptionPane.showMessageDialog(allPanel, "Task Removed ");
               
            }
        });
          
          JMenuItem completed = new JMenuItem("   Completed           ");
          completed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                  // getting the selected row 
                int selectedRow = table1.getSelectedRow();
               
                //converting the row index
                selectedRow = table1.convertRowIndexToModel(selectedRow);
                
                // getting values of the selected row 
                String val1 = (String)table1.getModel().getValueAt(selectedRow, 0);
                String val2 = (String)table1.getModel().getValueAt(selectedRow, 1);
                String val3 = (String)table1.getModel().getValueAt(selectedRow, 2);
                
                
                // adding these selected row values to priorityTasks file
                addTaskInCompleted(val1, val2, val3);
                
             
                
                // remove the selected row from alltask table 
                allModel.removeRow(selectedRow);
                
                
                
                 selectedRow++;
               int r3= selectedRow*3;
               int r2=r3 - 1;
               int r1= r2 - 1;
               
               
               
                try {
                    removeLineFromAll(r3);
                    removeLineFromAll(r2);
                    removeLineFromAll(r1);
                    
                    
                } catch (IOException ex) {
                     
                }
                
                
                
                
                JOptionPane.showMessageDialog(allPanel, "Task Completed and Removed ");
               
                 }
        });
          
          
          popupMenu.add(priority);
          popupMenu.addSeparator();
          popupMenu.add(remove);
          popupMenu.addSeparator();
          popupMenu.add(completed);
          
          table1.setComponentPopupMenu(popupMenu);
        
          
         
    
    
    
        JScrollPane tableContainer1 = new JScrollPane(table1);
          // adding table container in allpanel 
          allPanel.add(tableContainer1, BorderLayout.CENTER);
           mainFrame.add(allPanel);
          
    }
    
    public void priorityTasksTable() throws FileNotFoundException, IOException
    {
        // clear array list's previous data 
         priorityTasksData.clear();
          
         
         // reading the file to get data of all tasks 
        try (BufferedReader reader = new BufferedReader(new FileReader("priorityTasks"))) {
            String Line;
            while((Line= reader.readLine())!=null)
            {
                priorityTasksData.add(Line);
            }
        }
        
        // getting the size of arraylist 
        int rowSize= priorityTasksData.size();
        
        
           //init 2d array for table data 
           String x[][]= new String[rowSize/3][3];
           
           
           // getting data from arraylist to 2d array
           int z=0, k=0,v=0;
           for(int i=0;i<rowSize/3;i++)
           {    
               x[k][z]=priorityTasksData.get(v);
               v++;
               x[k][z+1]=priorityTasksData.get(v);
               v++;
               x[k][z+2]=priorityTasksData.get(v);
               v++;
               k++;
           } 
          
           
           // table colomn names 
            String y[]={"Name","Description","Date"}; 
        
        
        priorityModel=new DefaultTableModel(x, y);
            
            
         // init table 
           table=new JTable(priorityModel);  
          
          //setting colomn widths 
          TableColumnModel tcm = table.getColumnModel();
          tcm.getColumn(0).setPreferredWidth(200);     //Name
          tcm.getColumn(1).setPreferredWidth(450);     //description
          tcm.getColumn(2).setPreferredWidth(150);    // date 

          // setting row size 
          table.setRowHeight(25);
          
          
          //change the background color of table 
          table.setBackground(Color.ORANGE);
          
          
          
          
         // init popup menu 
         final JPopupMenu popupMenu = new JPopupMenu();
          JMenuItem remove = new JMenuItem("   Remove            ");
          remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                   // getting the selected row 
                int selectedRow = table.getSelectedRow();
               
                //converting the row index
                selectedRow = table.convertRowIndexToModel(selectedRow);
                
                // remove selected row 
                priorityModel.removeRow(selectedRow);
                
                
                 selectedRow++;
               int r3= selectedRow*3;
               int r2=r3 - 1;
               int r1= r2 - 1;
               
               
                try {
                    removeLineFromPriority(r3);
                    removeLineFromPriority(r2);
                    removeLineFromPriority(r1);
                    
                } catch (IOException ex) {
                   
                }
                
                
                JOptionPane.showMessageDialog(priorityPanel, "Task Removed");
                 }
        });
        
          
            JMenuItem completed = new JMenuItem("   completed           ");
          completed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                 
                  // getting the selected row 
                int selectedRow = table.getSelectedRow();
               
                //converting the row index
                selectedRow = table.convertRowIndexToModel(selectedRow);
                
                // getting values of the selected row 
                String val1 = (String)table.getModel().getValueAt(selectedRow, 0);
                String val2 = (String)table.getModel().getValueAt(selectedRow, 1);
                String val3 = (String)table.getModel().getValueAt(selectedRow, 2);
                
                
                // adding these selected row values to priorityTasks file
                addTaskInCompleted(val1, val2, val3);
                
             
                
                // remove the selected row from alltask table 
               priorityModel.removeRow(selectedRow);
                
                
                
                 selectedRow++;
               int r3= selectedRow*3;
               int r2=r3 - 1;
               int r1= r2 - 1;
               
               
               
                try {
                    removeLineFromPriority(r3);
                    removeLineFromPriority(r2);
                    removeLineFromPriority(r1);
                   
                    
                    
                } catch (IOException ex) {
                    
                }
                
                JOptionPane.showMessageDialog(priorityPanel, "Task Completed and Removed");
                  }
        });
          
          
          
          popupMenu.add(remove);
          popupMenu.addSeparator();
          popupMenu.add(completed);
          
          table.setComponentPopupMenu(popupMenu);
        
          
        JScrollPane tableContainer1 = new JScrollPane(table);
          // adding table container in allpanel 
          priorityPanel.add(tableContainer1, BorderLayout.CENTER);
          
          
    }
    

    public void initMainFrame() throws IOException
    {
        // init main frame 
        mainFrame= new JFrame("To do List");
        
        
        
        // setting mainFrame layout 
        mainFrame.setLayout(null);
        
        
        // init panel 
        allPanel= new JPanel();
        priorityPanel= new JPanel();
 
        // panel layout 
        allPanel.setLayout(new BorderLayout());
        priorityPanel.setLayout(new BorderLayout());
        
        
     
       

       
        
        
        // this function reads and set data in all task table 
         allTasksTable();
          
          // this function reads and set data in Priority task table 
         priorityTasksTable();
          
          
          
        
        // all panel settings 
        priorityPanel.setSize(800, 200);
        priorityPanel.setLocation(20,40);
        priorityPanel.setBackground(Color.CYAN);
        
        
        
        // priority panel 
        allPanel.setSize(800, 300);
        allPanel.setLocation(20,290);
        allPanel.setBackground(Color.WHITE);
       
        
        // adding the panel in main frame
       
        mainFrame.add(priorityPanel);
        
        
        // init buttons 
        button1= new JButton("Add");
        button2= new JButton("Specific Date");
        button3= new JButton("Completed Tasks");
        sort = new JButton("Sort");
      
        
        
        // buttons settings 
        button1.setBounds(830, 100, 140, 30);
        button2.setBounds(830, 200, 140, 30);
        button3.setBounds(830, 300, 140, 30);
        sort.setBounds(830, 400, 140, 30);
        
        
        // Jlabels 
        labell = new JLabel("Add New Task");
        label2 = new JLabel("View By Specific Date");
        label3 = new JLabel("View Completed Tasks");
        label4 = new JLabel("Priority Tasks");
        label5 = new JLabel("All Tasks");
        JLabel sortLabel = new JLabel("Sort Tasks In Due Order");
       
        
        // JLebel Settings
        
        labell.setBounds(860, 70, 140, 30);
        mainFrame.add(labell);
        
         label2.setBounds(840, 170, 140, 30);
        mainFrame.add(label2);
        
         label3.setBounds(840, 270, 140, 30);
        mainFrame.add(label3);
        
        
        label4.setBounds(400, 10, 140, 30);
        mainFrame.add(label4);
        
        
        label5.setBounds(420, 250, 140, 30);
        mainFrame.add(label5);
        
        sortLabel.setBounds(830, 370, 140, 30);
        mainFrame.add(sortLabel);
        
        // adding buutons to  main frame
        mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        mainFrame.add(sort);
        
        
        // main frame settings 
        mainFrame.setLocation(50, 50);
        mainFrame.setSize(1000, 650);
        
        
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void addTaskInPriorityPanel(String name, String desc, String date)
    {
        
      
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("priorityTasks", true);
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
    
     public void addTaskInCompleted(String name, String desc, String date)
    {
        
      
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("completedTasks", true);
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
    
    
    
    
    
    
    public void removeLineFromPriority(int lineNo) throws IOException
{
   File file= new File("priorityTasks");
   File temp = new File("temp");
   LineNumberReader r = new LineNumberReader(new FileReader("priorityTasks"));
   PrintWriter out = new PrintWriter(new FileWriter(temp)); 
        
            while (true) {
            String line = r.readLine();
          
            if (line == null) 
            {
                break;
            }
            if (r.getLineNumber() != lineNo)
            {
               
                out.println(line);
                out.flush();
                
            }
        }
            
       
            out.close();
            r.close();
            file.delete();
            
           
            temp.renameTo(new File("priorityTasks"));
           
            
       }
        
    
     public void removeLineFromAll(int lineNo) throws IOException
{
   File file= new File("allTasks");
   File temp = new File("temp");
   LineNumberReader r = new LineNumberReader(new FileReader("allTasks"));
   PrintWriter out = new PrintWriter(new FileWriter(temp)); 
        
            while (true) {
            String line = r.readLine();
          
            if (line == null) {
                break;
            }
            if (r.getLineNumber() != lineNo) {
               
                out.println(line);
                out.flush();
                
            }
        }
            
       
            out.close();
            r.close();
            file.delete();
            
           
            temp.renameTo(new File("allTasks"));
           
            
       }
    
   public void allTableSort()
   {
        
          
          
          
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table1.getModel());
    table1.setRowSorter(sorter);
    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
     
    int columnIndexToSort = 2;
    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
     
    sorter.setSortKeys(sortKeys);
    sorter.sort();

          
    
    
   }
   
     public void priorityTableSort()
   {
        
          
          
          
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
    table.setRowSorter(sorter);
    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
     
    int columnIndexToSort = 2;
    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
     
    sorter.setSortKeys(sortKeys);
    sorter.sort();

          
    
    
   }
  
}

