/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary; 

import java.util.Scanner;

/**
 * @FabiollaMCosta
 * >2019226
 *  
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        systemLibrary.Books[] itens;
        
        systemLibrary.Books item=new systemLibrary.Books();
        itens = item.readItens("allitens.txt"); //read all books

        Reader[] clients;
        Reader client=new Reader();
        clients = client.readClients("clients.txt"); //read all readers

        Rented[] borrowedBooks;
        Rented borrowedBook =new Rented();
        borrowedBooks = borrowedBook.readBorrowedBooks("allborrowed.txt"); //read all books rented

        Queue[] identifications;
        Queue identification=new Queue();
        identifications = identification.readIdentification("identification.txt"); //read all id from books and readers

        Activities act;
        act = new Activities();

        Scanner kB;
        kB = new Scanner(System.in);

        String answer="";

        for(;;){
            System.out.println("*****Wellcome to library system*****");
            System.out.println("Here you can check the different titles we have in stock, the users of the library and all borrowings");
            System.out.println("*****Hope you find all you need, have a good search*****");
            System.out.println("*****here are all the search options in our system, please enter the code that is in front of the search you want to do*****");


            System.out.println("01 - Search for title books ");
                System.out.println("*************************************************");
                
            System.out.println("02 - Search author ");
                System.out.println("*************************************************");
                
            System.out.println("03 - check all books in stock by title order ");
                System.out.println("*************************************************");
                
            System.out.println("04 - check all books in stock by autor order ");
                System.out.println("*************************************************");
                
            System.out.println("05 - See all the clients by name");
                System.out.println("*************************************************");
                
            System.out.println("06 - See all the clients by the ID");
                System.out.println("*************************************************");
                
            System.out.println("07 - Renter a book ");
                System.out.println("*************************************************");
                
            System.out.println("08 - See all the waiting clients ");
                System.out.println("*************************************************");
                
            System.out.println("09 - Return book ");
                System.out.println("*************************************************");
                
            System.out.println("10 - See all the rented books ");
                System.out.println("*************************************************");
                
         //   System.out.println("08 - See all the waiting clients ");
                System.out.println("*************************************************");
                
            System.out.println("0 - exit ");
                System.out.println("*************************************************");
                
            System.out.println("Which one you would like to try?");
            answer = kB.nextLine();
        
            switch(answer){
                case "01":
                    act.findItens(itens);
                    System.out.println("*************************************************");
                    break;
                    
                case "02":
                    act.findItens(itens);
                    System.out.println("*************************************************");
                    break;
                    
                case "03":
                    item.sortedList(itens);
                    System.out.println("*************************************************");
                    break;
                    
                case "04":
                    item.authorSortedList(itens);
                    System.out.println("*************************************************");
                    break;
                                   
                case "05":
                    System.out.println("3 - Callum \n" +
                                       "1 - John \n" +
                                       "2 - Mary" );
                    System.out.println("*************************************************");
                    break;
                    
                case "06":
                    System.out.println("1- John \n" +
                                       "2- Mary \n" +
                                       "3- Callum");
                    System.out.println("*************************************************");
                    break;
                    
                case "07":
                    borrowedBook.getBorrowedBook(borrowedBooks,itens,clients,identification);
                    borrowedBooks=borrowedBook.readBorrowedBooks("allborrowed.txt");
                    identifications=identification.readIdentification("identification.txt");
                    System.out.println("*************************************************");
                    break;
                            
                case "08":
                    identification.list(identifications);
                    System.out.println("*************************************************");
                    break;
                    
                case "09":
                   borrowedBook.returnItem(borrowedBooks,itens,clients,identifications);
                    borrowedBooks=borrowedBook.readborrowedBooks("allborrowed.txt");
                    System.out.println("*************************************************");
                    break;
                    
                case "10":
                    borrowedBook.list(borrowedBooks);
                    System.out.println("*************************************************");
                    break;
                    
               
                    
                case "0":
                    System.out.println("END");
                    System.out.println("*************************************************");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Sorry, make sure to insert the right code! Thank you");
                    break;
            }

        }

    }

}