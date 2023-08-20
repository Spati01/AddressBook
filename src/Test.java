import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;


class PersonInfo{

    String name;
    String address;
    String phoneNumber;

    PersonInfo(String n,String a,String p){
        name = n;
        address = a;
        phoneNumber = p;
    }
    //Display on GUI
    void display(){
        JOptionPane.showMessageDialog(null,"Name: " + name + "\nAddress: " + address + "\nPhoneNO: " + phoneNumber);
    }

}

class AddressBook {
    ArrayList persons;

    AddressBook(){

        persons = new ArrayList<>();
        loadPersons();
    }
    // add person object
    void addPerson(){

        String name = JOptionPane.showInputDialog("Enter Name: ");
        String add = JOptionPane.showInputDialog("Enter Address: ");
        String pNum = JOptionPane.showInputDialog("Enter Phone number: ");

        // creating a personInfo object
        PersonInfo p = new PersonInfo(name, add, pNum);

        persons.add(p);
    }
    //search a person
    void searchPerson(String n){
        for(int i=0; i<persons.size(); i++){
            PersonInfo p =(PersonInfo)  persons.get(i);
            if(n.equals(p.name)){
                p.display();
            }
        }
    }
    //deleting a person
    void deletePerson(String n){
        for(int i=0; i<persons.size(); i++){
            PersonInfo p = (PersonInfo) persons.get(i);
            if(n.equals(p.name)){
                persons.remove(i);
            }
        }
    }
    void savePerson(){
        try{
            PersonInfo p;
            String line;
            FileWriter fw = new FileWriter("persons.text");
            PrintWriter pw = new PrintWriter(fw);
            for(int i=0; i<persons.size(); i++){
                p = (PersonInfo) persons.get(i);
                line = p.name + "," + p.address + "," + p.phoneNumber;
                //write line to persons.text

                pw.println(line);
            }
            pw.flush();
            pw.close();
            fw.close();
        }catch (IOException ioEx){
            System.out.println(ioEx);
        }
    }
    //loading persons Record from .text file
    void loadPersons(){
        String tokens[] = null;
        String name;
        String add;
        String ph;
        try{
            FileReader fr = new FileReader("persons.text");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                tokens =  line.split(",");
                name = tokens[0];
                add=tokens[1];
                ph = tokens[2];
                PersonInfo p = new PersonInfo(name,add,ph);
                persons.add(p);
                line= br.readLine();

            }
            br.close();
            fr.close();
        }catch (IOException ioEX){
            System.out.println(ioEX);
        }
    }
}
public class Test{
    public static void main(String[] args) {
        AddressBook ab = new AddressBook();
        String input;
        String s;
        int ch;

        while(true){
            input = JOptionPane.showInputDialog("Enter 1 to add \n Enter 2 to Search\n Enter 3 to Delete\n Enter 4 to Exit");
            ch =Integer.parseInt(input);

            switch (ch){
                case 1:
                    ab.addPerson();
                    break;
                case 2:
                    s = JOptionPane.showInputDialog("Enter name to search:");
                    ab.searchPerson(s);

                case 3:
                    s=JOptionPane.showInputDialog("Enter name to delete:");
                    ab.deletePerson(s);
                    break;
                case 4:
                    ab.savePerson();
                    System.exit(0);
            }
        }


    }

}