/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @FabiollaMCosta
 * >2019226
 * 
 */
public class Activities {

    Books item;


    public int Lines(File file){
        System.out.println("counting lines");
        int total=0;
        try {
            FileReader isr = new FileReader(file);
            BufferedReader bf = new BufferedReader(isr);
            String line;
            line=bf.readLine();
            int lines=0;
            while(true){
                line=bf.readLine();
                if(line==null)
                    break;
                lines++;
            }
            total=lines;
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("t"+total);
        return total;
    }

    public void findItens(Books[] itens){
        item = new Books();
        String bookName;
        Scanner kB = new Scanner(System.in);
        System.out.println("Enter with movie title or author last name");
        bookName = kB.nextLine();

        item.find(itens, bookName);

        for (Books item : itens) {
            //search for movie title
            if(item.getBook_title().toLowerCase().contains(bookName.toLowerCase())){
                System.out.println("Id......:"+item.getId());
                System.out.println("title..:"+item.getBook_title());
                System.out.println("Author firs name...:"+item.getAuthor_first_name());
                System.out.println("Author last name....:"+item.getAuthor_last_name());
                System.out.println("Genre...:"+item.getGenre());
                System.out.println("----------------");
                break;
            }
            //searchg for movie author last name
            if(item.getAuthor_last_name().toLowerCase().contains(bookName.toLowerCase())){
                System.out.println("Id......:"+item.getId());
                System.out.println("title..:"+item.getBook_title());
                System.out.println("Author firs name...:"+item.getAuthor_first_name());
                System.out.println("Author last name....:"+item.getAuthor_last_name());
                System.out.println("Genre...:"+item.getGenre());
                System.out.println("----------------");
                break;
            }

        }
    }

    //void findItens(Books[] itens) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

}
