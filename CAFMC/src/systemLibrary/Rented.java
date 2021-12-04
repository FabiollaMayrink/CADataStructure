/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @FabiollaMCosta
 * >2019226
 * 
 */

public class Rented {

    private int id;
    private int idClient;
    private int idItem;
    private int status;//1=rented 0=return

    public Rented() {
        this.id = 0;
        this.idClient = 0;
        this.idItem = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidClient() {
        return idClient;
    }

    public void setidClient(int idClient) {
        this.idClient = idClient;
    }

    public int getidItem() {
        return idItem;
    }

    public void setidItem(int idItem) {
        this.idItem = idItem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int list(Rented[] borrowed){
        int total=0;
        System.out.println("Listando os Empréstimos");
        for (Rented BorrowedBook : borrowed) {
            total++;
            //if(emprestimo.getStatus()==1){
            System.out.println("Id......:"+BorrowedBook.getId());
            System.out.println("Leitor..:"+BorrowedBook.getidClient());
            System.out.println("Livro...:"+BorrowedBook.getidItem());
            System.out.println("Status...:"+BorrowedBook.getStatus());
            System.out.println("----------------");
            //}
        }
        return total;
    }

    public Rented findBorrowedBook(Rented[] borrowed, int idItem){
        Rented BorrowedBook=new Rented();
        for (Rented e : borrowed) {
            if(e.getStatus()==1){
                if(e.getidItem()==idItem){
                    BorrowedBook.setId(e.getId());
                    BorrowedBook.setidClient(e.getidClient());
                    BorrowedBook.setidItem(e.getidItem());

                }
            }

        }

        return BorrowedBook;

    }

    public Rented[] readBorrowedBooks(String fileName){
        File file = new File(fileName);
        Rented BorrowedBook;
        Rented[] BorrowedBooks;
        Activities act = new Activities();
        BorrowedBooks = new Rented[act.Lines(file)];
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
                String[] data=line.split(";");
                BorrowedBook=new Rented();
                BorrowedBook.setId(Integer.parseInt(data[0]));
                BorrowedBook.setidClient(Integer.parseInt(data[1]));
                BorrowedBook.setidItem(Integer.parseInt(data[2]));
                BorrowedBook.setStatus(Integer.parseInt(data[3]));
                BorrowedBooks[lines]=BorrowedBook;
                lines++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return BorrowedBooks;
    }

    public Rented findBorrowedBookByItemId(Rented[] BorrowedBooks, int findId){
        Rented borrowed = new Rented();
        for (Rented BorrowedBook : BorrowedBooks) {
            if(BorrowedBook.getStatus()==1){//this is for show only the rented ones
                if(BorrowedBook.getidItem()==findId){ //here is if the book is rented
                    id = BorrowedBook.getId();
                    idClient = BorrowedBook.getidClient();
                    idItem = BorrowedBook.getidItem();
                    status = BorrowedBook.getStatus();
                    borrowed.setId(id);
                    borrowed.setidClient(idClient);
                    borrowed.setidItem(idItem);
                    borrowed.setStatus(status);
                    break;
                }
            }
        }
        return borrowed;
    }

    public Rented[] addBorrowedBook(Rented[] borrows, Rented borrow){
        Rented[] borrowedbooks = new Rented[borrows.length+1];
        for (int i = 0; i < borrows.length; i++) {
            borrowedbooks[i]=borrows[i];
            System.out.println("ee "+borrowedbooks[i].getId());
        }
        borrowedbooks[borrows.length]=borrow;
        //this is for make a record on emprestimos.txt
        this.insertBorrowedBooks("emprestimos.txt", borrow);//all in the array .txt
        return borrowedbooks;//all in the array.
    }

    public void insertBorrowedBooks(String filename, Rented borrowedBook){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file,true);
            id=borrowedBook.getId();
            idClient=borrowedBook.getidClient();
            idItem=borrowedBook.getidItem();
            status=borrowedBook.getStatus();
            fw.write("\n"+id+";"+idClient+";"+idItem+";"+status);
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBorrowedBooks(String filename, Rented[] borrowedBook, Rented borrowed){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            fw.write("id;idClient;idItem;status");
            for (int i = 0; i < borrowedBook.length; i++) {
                id=borrowedBook[i].getId();
                idClient=borrowedBook[i].getidClient();
                idItem=borrowedBook[i].getidItem();
                if(borrowedBook[i].getId()!=borrowed.getId()){
                    status=borrowedBook[i].getStatus();
                }else{
                    status=0;
                }
                fw.write("\n"+id+";"+idClient+";"+idItem+";"+status);
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getItem(Rented[] borrowedBook, systemLibrary.Books[] itens, systemLibrary.Reader[] clients, systemLibrary.Queue[] identifications){
        int idClient,idItem;

        Scanner kB = new Scanner(System.in);
        System.out.println("Enter with book id");
        idItem = kB.nextInt();
        System.out.println("Enter with reader id");
        idClient = kB.nextInt();

        //verefy if the book exist in the systems
        systemLibrary.Books item = new systemLibrary.Books();
        item = item.findByID(itens, idItem);

        //verefy if the custumer exist in the systems
        systemLibrary.Reader client = new systemLibrary.Reader();
        client = client.findByidClient(clients, idClient);

        if(item.getId()==null){
            System.out.println("Book is not in the list");
            return;
        }
        if(client.getId()==0){
            System.out.println("Reader is not in the system");
            return;
        }

        ////verefy if the book is avaliable
        Rented checkAvailable;
        checkAvailable = new Rented();
        checkAvailable = checkAvailable.findBorrowedBook(borrowedBook, idItem);

        if(checkAvailable.getidClient()!=0){
            System.out.println("Rented Book");
            System.out.println("Are you wish to get in the queue for this book? (s/n)");

            //this is in case the book is already rented, you can go to the queue for get it later
            //then
            //add tho the queue
            kB = new Scanner(System.in);
            String confirma;
            confirma = kB.nextLine();
            if(confirma.equals("s")){
                systemLibrary.Queue fi = new systemLibrary.Queue();
                fi.setId(identifications.length+1);
                fi.setIdClient(idClient);
                fi.setIdItem(idItem);
                fi.setIdentification("fila.txt", fi);
            }


        }else{
            //kB.close();
            System.out.println("Confirm rented? (s/n)");
            kB = new Scanner(System.in);
            String confirm;
            confirm = kB.nextLine();
            if(confirm.equals("s")){
                //add tho the emprestimo lists
                System.out.println("Book rented! Have fun!");
                Rented emp = new Rented();
                emp.setId(borrowedBook.length+1);
                emp.setidClient(idClient);
                emp.setidItem(idItem);
                emp.setStatus(1);
                //adicionaEmprestimo(emprestimos,emp);
                setborrowedBook("emprestimos.txt", emp);//vamos ter tudo no arquivo
            }
            //kB.close();
        }

    }

    public void returnItem(Rented[] borrowedBooks, systemLibrary.Books[] itens, systemLibrary.Reader[] clients, systemLibrary.Queue[] identifications){
        int idClient,idItem;
        String answer;
        Scanner kB = new Scanner(System.in);
        System.out.println("Enter with book id");
        idItem = kB.nextInt();

        //verefy if the book is avaliable
        Rented borrowedBook = new Rented();
        borrowedBook = findBorrowedBook(borrowedBooks,idItem);

        if(borrowedBook.getidItem()==0){
            System.out.println("Book is not rented");
            return;//sai do método devolverLivro
        }
        kB = new Scanner(System.in);

        System.out.println("Confirm return? (s/n)");
        kB = new Scanner(System.in);
        String confirma;
        confirma = kB.nextLine();
        if(confirma.equals("s")){
            //set status 0 no empréstimo
            updateBorrowedBooks("emprestimos.txt", borrowedBooks, borrowedBook);
            System.out.println("Thanks for return the book");
        }

        //basic alert if the book is in the queue list
        systemLibrary.Queue row= new systemLibrary.Queue();
        int[] tem = row.checkWaitingList(identifications,idItem);
        if(tem.length>0){
            System.out.println("Temos "+tem.length+" na fila");
            for (int i = 0; i < tem.length; i++) {
                systemLibrary.Reader l=new systemLibrary.Reader();
                l=l.findByID(clients, tem[i]);
                System.out.println("O leitor "+l.getName()+" está na fila");
            }
            //remove from the queue list
            row.deleteFromRow("fila.txt",identifications,idItem,tem[0]);

            //rented book if  idItem e idClient
            borrowedBook.getItem(borrowedBooks,itens,clients,identifications);
            borrowedBooks=borrowedBook.readBorrowedBooks("emprestimos.txt");
            //filas=fila.carregaFila("fila.txt");
        }

    }

    private void setborrowedBook(String emprestimostxt, Rented emp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Rented[] readBorrowedClients(String allborrowedtxt) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    void getBorrowedBook(Rented[] borrowedBooks, Books[] itens, Reader[] clients, Queue identification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Rented[] readborrowedBooks(String allborrowedtxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Rented[] readAllBorrowed(String allborrowedtxt) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

}
