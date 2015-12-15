package at.fhv.itb5c.viewjsf;
import javax.ejb.Stateless;

@Stateless
public class CalculatorImpl implements Calculator {

    public double add(double x, double y) {
        return x + y;
    }
}