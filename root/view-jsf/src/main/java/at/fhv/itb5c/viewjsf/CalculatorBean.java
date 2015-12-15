package at.fhv.itb5c.viewjsf;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CalculatorBean implements Serializable {
	private static final long serialVersionUID = -8234502621015491296L;

	@EJB
    Calculator calculator;
    
    private double x;
    private double y;
    private double result;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String add() {
        result = calculator.add(x, y);
        return "success";
    }
    
    public List<String> getAll(){
    	return Arrays.asList("Test", "String", "Ping", "Pong", "Ding", "Dong");
    }
}