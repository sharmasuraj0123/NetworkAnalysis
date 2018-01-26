/**
 * /**
 * Suraj Sharma
 * Id # 109606910
 * .
 */
public class Friend {

    Friend(String initname , double inittieStrength){
        name = initname;
        tieStrength = inittieStrength;
    }

    private String name;
    private double tieStrength;

    public double getTieStrength() {
        return tieStrength;
    }

    public void setTieStrength(double tieStrength) {
        this.tieStrength = tieStrength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
