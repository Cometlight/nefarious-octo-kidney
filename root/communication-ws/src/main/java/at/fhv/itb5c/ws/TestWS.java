package at.fhv.itb5c.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class TestWS {

	@WebMethod
	public String sayHello(String name) {
		return "Hello " + name + "!";
	}
}
