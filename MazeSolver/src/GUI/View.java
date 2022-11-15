/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GraphAlgorithms.DFS;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Rhythin
 */
public class View extends JFrame implements ActionListener, MouseListener{

    private int [][] maze={    
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private int target[]={8,11};
    private List<Integer> path= new ArrayList<>();

    JButton submitButton;
    JButton clearButton;
    JButton exitButton;
    
    public View(){
        this.setTitle("MazeSolver");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        submitButton=new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(120, 400, 80, 30);
        
        clearButton=new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(200, 400, 80, 30);
        
        exitButton=new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setBounds(280, 400, 80, 30);
        
        this.add(clearButton);
        this.add(submitButton);
        this.add(exitButton);
        
        this.addMouseListener(this);

       
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        for(int row=0; row<maze.length; row++){
            for(int col=0; col<maze[0].length; col++){
                Color color;
                switch (maze[row][col]) {
                    case 1:color=Color.BLACK;break;
                    case 9:color=Color.RED; break;
                    default:
                        color=Color.WHITE;
                }
                
                g.setColor(color);
                g.fillRect(40*col, 40*row, 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(40*col, 40*row, 40, 40);
            }
        }
        for(int i=0;i<path.size();i+=2){
            int pathx=path.get(i);
            int pathy=path.get(i+1);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathy, 40*pathx, 40, 40);
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submitButton){
            try{
                boolean result=DFS.searchPath(maze, 1, 1, path);
                System.out.println(result);
                this.repaint();
            }
            catch(Exception excp){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        if(e.getSource()==exitButton){
            int response=JOptionPane.showConfirmDialog(null, "are you sure you want to exit", 
                    "Submit", JOptionPane.YES_NO_OPTION);
            if(response==0){
                System.exit(0);
            }
        }
        if(e.getSource()==clearButton){
            for(int row=0; row<maze.length; row++){
                for(int col=0; col<maze[0].length; col++){
                    if(maze[row][col]==2)maze[row][col]=0;
                }
            }
            path.clear();
            this.repaint();            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getX()>=0 && e.getX()<maze[0].length*40  && e.getY()>=0 && e.getY()<maze.length*40 ){
            int x=e.getY()/40;
            int y=e.getX()/40;
            
            if(maze[x][y]==1){
                return;
            }
            
            Graphics g=getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(40*target[1], 40*target[0], 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(40*target[1], 40*target[0], 40, 40);
            
            g.setColor(Color.RED);
            g.fillRect(40*y, 40*x, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(40*y, 40*x, 40, 40);
            
            
            maze[target[0]][target[1]]=0;
            maze[x][y]=9;
            target[0]=x;
            target[1]=y;
            
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        
    }
    
    public static void main(String args[]){
        View view=new View();
        view.setVisible(true);
    }
}
