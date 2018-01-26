import java.util.ArrayList;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910
 * .
 */
public class Person {
    private String name;
    private ArrayList<Friend> friends;
    private int numberOfFriends;

    Person(String initname){
        name = initname;
        friends = new ArrayList<Friend>();
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public int getNumberOfFriends() {
        return friends.size();
    }

    public void setNumberOfFriends(int numberOfFriends) {
        this.numberOfFriends = numberOfFriends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name +" has "+friends.size()+" friends\n";
    }

}
