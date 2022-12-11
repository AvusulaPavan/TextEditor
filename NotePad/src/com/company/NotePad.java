package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NotePad implements ActionListener {
       // 1-->frame for text editor

    JFrame frame;

    //2--> now initialisising the menubar to add in frame

    JMenuBar menubar;

    //2.1--> initilising the menues in menu bar

    JMenu file, edit, exit;

    //2.2--> initialising the menu items

    JMenuItem newFile,openFile, saveFile;

    JMenuItem cut, copy, paste, selectall;

    JMenuItem close;

    //3--> now initialising he textarea

    JTextArea textArea;

    // this is the constriuctor that will be triggered

    NotePad(){
        //1--> initisling the frame

        frame=new JFrame();

        //2--> initialising the menubar

        menubar =new JMenuBar();

        // 3--> now intitialising he textarea into jframe

        textArea=new JTextArea();

        frame.add(textArea);

        // 2.1-->initilising the menus in menubar

        file =new JMenu("File");

        edit=new JMenu("Edit");

        exit=new JMenu("Exit");

        //2.1-->adding the menues to menu bar

        menubar.add(file);
        menubar.add(edit);
        menubar.add(exit);

        // 2.2-->initilaising the menuitems in menu

        newFile=new JMenuItem("Newfile");
        openFile = new JMenuItem("Openfile");
        saveFile=new JMenuItem("Savefile");

        // adding actions to the filemenu

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // 2.2-->initilaising the menuitems in menu

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("SelectAll");

        // adding actions to the edit menu

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);

        // 2.2-->initilaising the menuitems in menu
        close=new JMenuItem("Close");

        // adding action to exit menu
        close.addActionListener(this);

        //2.2-->now adding the menu items to the menubar

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        exit.add(close);

        // 2-->setting menubar in the frame

        frame.setJMenuBar(menubar);

        //1--> setting the frame boundies

        frame.setBounds(100, 100, 800, 500);

        //1--> making then frame visible

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        // adding the functionalities to menu items

        // if source is cut

        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        // if source is copy

        if(actionEvent.getSource()==copy){
            textArea.copy();
        }

        // if source is paste

        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        // if source is select all
        if(actionEvent.getSource()==selectall){
            textArea.selectAll();
        }

        // if source is close

        if(actionEvent.getSource()==close){
            System.exit(0);
        }

        // if source is newfile

        if(actionEvent.getSource()==newFile){

            NotePad notePad=new NotePad();
        }

        // if source is openfile


        if(actionEvent.getSource()==openFile){

            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption= fileChooser.showOpenDialog(null);
            // chooseing the file to open

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filePath= file.getPath();
                try{
                    // creating bufferreader

                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));

                    // create string to store file

                    String intermediate="", out="";

                    // read content lineby line

                    while((intermediate=bufferedReader.readLine())!=null){
                        out=out+intermediate+"\n";
                    }

                    // set out to textarea

                    textArea.setText(out);

                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }

        //if source is savefile

        if(actionEvent.getSource()==saveFile){

            //save afile
            // choose a file chooser

            JFileChooser fileChooser=new JFileChooser("C:");

            // get choose option from the file

            int choosefile=fileChooser.showSaveDialog(null);

            // if choosen

            if(choosefile==JFileChooser.APPROVE_OPTION){
                // create file object with selected path
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //creating the buffered writer to write file

                    BufferedWriter outfile=new BufferedWriter(new FileWriter(file));
                    // adding the text forom text area to bufferwriter

                    textArea.write(outfile);
                    outfile.close();

                }catch (Exception exception){

                }
            }

        }
    }

    public static void main(String[] args) {
	// write your code here
        NotePad pad=new NotePad();
    }
}
