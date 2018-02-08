/**
 * Created by Aldres on 08.02.2018.
 */
public class RateObject {

    private String name;
    private double rate;

    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public RateObject(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
