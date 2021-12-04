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
public class Reader {

    private int id;
    private String name;

    public Reader() {
        this.id = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reader[] readClients(String fileName){
        File file = new File(fileName);
        Reader client=new Reader();
        Reader[] clients;
        Activities act;
        act=new Activities();
        clients = new Reader[act.Lines(file)];
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
                client=new Reader();
                client.setId(Integer.parseInt(data[0]));
                client.setName(data[1]);
                clients[lines]=client;
                lines++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Reader findbyId(Reader[] clients, int idFind){
        Reader l = new Reader();
        for (Reader client : clients) {
            if(client.getId()==idFind){
                id = client.getId();
                name = client.getName();
                System.out.println("Id......:"+id);
                System.out.println("Nome...:"+name);
                System.out.println("----------------");
                l.setId(id);
                l.setName(name);
            }
        }
        return l;
    }

    Reader findByidClient(Reader[] clients, int idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Reader findByID(Reader[] clients, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
