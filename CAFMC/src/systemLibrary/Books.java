/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @FabiollaMCosta
 * >2019226
 * 
 */

//Contructor, get and setters. ID, Author name, Author last name, genere, Booktitle
public class Books {
    private String id;
    private String author_first_name;
    private String author_last_name;
    private String book_title;
    private String genre;

    public Books() {
        this.id = null;
        this.author_first_name = "";
        this.author_last_name = "";
        this.genre = "";
        this.book_title = "";
    }

    public Books(String id, String title, String autor, String company) {
        this.id = id;
        this.author_first_name = title;
        this.author_last_name = autor;
        this.genre = company;
        this.book_title = book_title;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_first_name() {
        return author_first_name;
    }

    public void setAuthor_first_name(String title) {
        this.author_first_name = title;
    }

    public String getAuthor_last_name() {
        return author_last_name;
    }

    public void setAuthor_last_name(String autor) {
        this.author_last_name = autor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String autorLastN) {
        this.book_title = autorLastN;
    }

    public Books[] readItens(String fileName){
        File file = new File(fileName);
        Books item;
        Books[] itens;
        Activities act = new Activities();
        itens = new Books[act.Lines(file)];
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);
            String line;
            line=bf.readLine();
            int lines=0;
            while(true){
                line=bf.readLine();
                if(line==null)
                    break;
                String[] dados=line.split(",");
                item=new Books();
                item.setId(dados[0]);
                item.setAuthor_first_name(dados[1]);
                item.setAuthor_last_name(dados[2]);
                item.setBook_title(dados[3]);
                item.setGenre(dados[4]);
                itens[lines]=item;
                lines++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return itens;
    }

    public int listItens(Books[] itens){
        int total=0;
        System.out.println("All the store books :");
        for (Books item : itens) {
            System.out.println("Id......:"+item.getId());
            System.out.println("Author First Name..:"+item.getAuthor_first_name());
            System.out.println("Author Laast Name...:"+item.getAuthor_last_name());
            System.out.println("Book Title...:"+item.getBook_title());
            System.out.println("Genre...:"+item.getGenre());
            System.out.println("----------------");
            total++;
        }
        return total;
    }
    
    public void sortedList(Books[] itens){
        Books itemA;
        String a1,a2;
        for (int i = 0; i < itens.length; i++) {
            for (int j = 0; j < itens.length-1-i; j++) {
                a1=itens[j].getBook_title().trim();
                a2=itens[j+1].getBook_title().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    itemA=itens[j];
                    itens[j]=itens[j+1];
                    itens[j+1]=itemA;
                      break;
                }
            }
        }
        int t = this.listItens(itens);
         
    }

    public void authorSortedList(Books[] itens){
        Books itemA;
        String a1,a2;
        for (int i = 0; i < itens.length; i++) {
            for (int j = 0; j < itens.length-1-i; j++) {
                a1=itens[j].getAuthor_last_name().trim();
                a2=itens[j+1].getAuthor_last_name().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    itemA=itens[j];
                    itens[j]=itens[j+1];
                    itens[j+1]=itemA;
                      break;
                }
            }
        }
        int t = this.listItens(itens);
    }

    public void find (Books[] itens, String name){
        for (Books item : itens) {
            //seach for the titles using input sistem.in/ buffreader
            if(item.getAuthor_first_name().toLowerCase().contains(name.toLowerCase())){
                System.out.println("Id......:"+item.getId());
                System.out.println("title..:"+item.getBook_title());
                System.out.println("Author firs name...:"+item.getAuthor_first_name());
                System.out.println("Author last name....:"+item.getAuthor_last_name());
                System.out.println("Genre...:"+item.getGenre());
                System.out.println("----------------");
                  break;
            }
            //seach for the author using input sistem.in/ buffreader
            if(item.getAuthor_last_name().toLowerCase().contains(name.toLowerCase())){
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

    public Books findByID(Books[] itens, int idBuscar){
        Books l = new Books();
        for (Books item : itens) {
              break;
        }
        return l;
          
    }

}
