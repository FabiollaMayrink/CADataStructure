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

/**
 * @FabiollaMCosta
 * >2019226
 * 
 */

public class Queue {

    private int id;
    private int idClient;
    private int idItem;

    public Queue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Queue[] readIdentification(String fileName){
        File file = new File(fileName);
        Queue identification;
        Queue[] identifications;
        Activities act = new Activities();
        identifications = new Queue[act.Lines(file)];
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
                identification=new Queue();
                identification.setId(Integer.parseInt(data[0]));
                identification.setIdClient(Integer.parseInt(data[1]));
                identification.setIdItem(Integer.parseInt(data[2]));
                identifications[lines]=identification;
                lines++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return identifications;
    }

    public void list(Queue[] roww){
        System.out.println("Waiting list");
        if(roww.length==0){
            System.out.println("Empty waiting list");
            return;
        }
        for (Queue identification : roww) {
            System.out.println("Id......:"+identification.getId());
            System.out.println("Reader..:"+identification.getIdClient());
            System.out.println("Book...:"+identification.getIdItem());
            System.out.println("----------------");
              break;
        }
    }

    public void makeRows(String filename, Queue identification){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file,true);
            id=identification.getId();
            idClient=identification.getIdClient();
            idItem=identification.getIdItem();
            fw.write("\n"+id+";"+idClient+";"+idItem);
            fw.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] checkWaitingList(Queue[] rows, int IdItem){
        int[] totalWaiting;
        int howMany=0;
        for (Queue row : rows) {
            if(row.getIdItem()==IdItem){
                howMany++;
            }
        }
        totalWaiting= new int[howMany];
        int i=0;
        for (Queue row : rows) {
            if(row.getIdItem()==IdItem){
                totalWaiting[i++]= row.getIdClient();
            }
        }
        return totalWaiting;
    }

    public void removeFromRow(String filename, Queue[] rows, int idItem, int idClient){
        int idClient_=0;
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            fw.write("id;idClient;idItem");
            for (int i = 0; i < rows.length; i++) {
                System.out.println(" idClient "+rows[i].getIdClient());
                System.out.println("id "+idClient);
                if(rows[i].getIdClient()!=idClient){
                    id=rows[i].getId();
                    idClient_=rows[i].getIdClient();
                    idItem=rows[i].getIdItem();
                    fw.write("\n"+id+";"+idClient_+";"+idItem);
                }
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setIdentification(String filatxt, Queue fi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void deleteFromRow(String filatxt, Queue[] identifications, int idItem, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Queue[] readIdentifications(String identificationtxt) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }
}
