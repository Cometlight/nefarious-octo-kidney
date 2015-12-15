package at.fhv.itb5c.viewjsf;
import javax.ejb.Remote;

@Remote
public interface Calculator {
    public double add(double x, double y);
}