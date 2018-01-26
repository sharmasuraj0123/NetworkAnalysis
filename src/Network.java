import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910
 * .
 */
public class Network {

    public Network() {
        people = new ArrayList<Person>();
    }

    private ArrayList<Person> people;
    private int numberofpeople;
    private Graph graph;


    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public int getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(int numberofpeople) {
        this.numberofpeople = numberofpeople;
    }


    public  void addLineToNetwork(String[] line ){

        String nameOfPerson = line[0];
        String nameOfFriend = line[1];
        //String tieStength = line[2];

        Person p =new Person(nameOfPerson);
        if(searchByname(nameOfPerson)== null)
            people.add(p);

        Person p2 =new Person(nameOfFriend);
        if(searchByname(nameOfFriend)== null)
            people.add(p2);

        p = searchByname(nameOfPerson);
        p2 = searchByname(nameOfFriend);


        p.getFriends().add(new Friend(nameOfFriend , 0.1));
        p2.getFriends().add(new Friend(nameOfPerson, 0.1));
    }

    public  ArrayList<String> netWorkOverlap() throws IOException {
        ArrayList<String> overlapValue = new ArrayList<String>();

        BufferedWriter bw = null;

            File file = new File("networkOverlap.txt");
            File file2 = new File("DegreeOfEachNode.txt");
            if (!file.exists()) {
            file.createNewFile();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        bw = new BufferedWriter(fw);

        FileWriter fw2 = new FileWriter(file2);
        BufferedWriter bw2 = new BufferedWriter(fw2);

        for(int i =0; i<people.size()-1;i++) {
            Person p1 = people.get(i);
            bw2.write(p1.toString());

            Set<String> U = new TreeSet<String>();


            for(int j=0; j< p1.getFriends().size();j++)
                U.add(p1.getFriends().get(j).getName());

            for(int k=0; k<p1.getFriends().size();k++){

                Set<String> V = new TreeSet<String>();
                Set<String> union = new TreeSet<String>();
                Set<String> intersection = new TreeSet<String>();

                Person p2 = searchByname(p1.getFriends().get(k).getName());

                while(i>people.indexOf(p2) && k<p1.getFriends().size()){
                    k++;
                    if(k==p1.getFriends().size())
                        break;
                    p2 = searchByname(p1.getFriends().get(k).getName());

                }
                if(k==p1.getFriends().size())
                    break;
                for(int j=0; j< p2.getFriends().size();j++)
                    V.add(p2.getFriends().get(j).getName());

                union.addAll(U);
                union.addAll(V);

                intersection.addAll(U);
                intersection.retainAll(V);

                overlapValue.add((double)intersection.size()/union.size() + "\n");
                bw.write("Overlap Degree of " + p1.getName() + " : " + p2.getName() + " "
                        + (double) intersection.size() / union.size() + "\n");
            }
        }
        bw.close();
        bw2.close();
        return overlapValue;
    }
    public double clusteringCoefficient(){
        double cc =0.0;
        double triangle = 0.0;

        int n = people.size();
        n= n*(n-1)*(n-2)/6;

        for(int i =0;i<people.size();i++){
            Person p1 = people.get(i);

            for(int j =0; j<p1.getFriends().size();j++){
                Person p2 = searchByname(p1.getFriends().get(j).getName());

                for(int k =0; k<p2.getFriends().size();k++){
                    Person p3 = searchByname(p2.getFriends().get(k).getName());
                    if(areFriends(p1,p3))
                        triangle++;
                }
            }
        }

        triangle = triangle/6;
        cc = triangle/n;
        System.out.println("Number of triangle in dataSet: "+triangle);
        System.out.println("Value of nC3: " + n);
        System.out.println("The Clustering Coefficient :" + cc);

        return cc;
    }


    public Person searchByname(String name){

    if(people== null)
        return null;

        for(int i =0; i<people.size();i++){
            if(people.get(i).getName().equals(name))
                return people.get(i);
        }
        return null;
    }

    public void printPeople(){
        System.out.println("The list of number of people in the Network:");
        for (int i =0; i<people.size();i++)
            System.out.print(i+1 + ". " + people.get(i).toString());
    }

    public void printFriends(int position){
        Person p = people.get(position);
        System.out.println(p.getName() + "'s Friends List");
        for (int i=0; i<p.getFriends().size();i++)
            System.out.println(i + 1 + ". " + p.getFriends().get(i).getName() + " "
                    + p.getFriends().get(i).getTieStrength());
    }

    public void makeGraph(){
        for(int i=0; i<people.size();i++)
            graph.add(people.get(i).getName());

    }

    public boolean areFriends(Person p1 , Person p2){
        for(int i =0; i<p1.getFriends().size();i++)
            if(p1.getFriends().get(i).getName().equals(p2.getName()))
                return true;

        return false;
    }

    public int [][] histogramList(){
        int[][] list = new int[2][people.size()];
        ArrayList<String> hist = new ArrayList<String>();

        for (int i =0;i<people.size();i++) {
            list[0][i] = i;
            list[1][people.get(i).getNumberOfFriends()] ++;
            hist.add(people.get(i).toString());
        }

        return list;
    }

    public void friendshipParadox() {
        ArrayList<Person> randomPeople = new ArrayList<Person>();
        ArrayList<Person> randomFriends = new ArrayList<Person>();

        /*Simulation Code*/
        int degreeRandom = 0;
        int degreeFriend = 0;
        System.out.println("The Result of the simulation is as follows:");
        System.out.println("For Selecting 100 People.");
        System.out.println();
        /*j== Number of simulation*/
        for (int j = 0; j < 10; j++) {
            /*i== number of people chosen at random*/
            for (int i = 0; i < 100; i++) {
                int randomNumber = (int) (Math.random() * people.size());
                Person randomguy = people.get(randomNumber);
                //System.out.println("Random guy: " + randomguy.toString());
                randomPeople.add(randomguy);
                degreeRandom += randomguy.getFriends().size();

                randomNumber = (int) (Math.random() * randomguy.getFriends().size());
                String name = randomguy.getFriends().get(randomNumber).getName();
                randomguy = searchByname(name);
                //System.out.println("Random Friend: " + randomguy.toString());
                randomFriends.add(randomguy);
                degreeFriend += randomguy.getFriends().size();
            }
            System.out.println("Random Degree : " + degreeRandom);
            System.out.println("Degree of Friends :" + degreeFriend);
            System.out.println();
        }
    }


}
