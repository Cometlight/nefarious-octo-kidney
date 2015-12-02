package at.fhv.itb5c.ejbtest;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.fhv.itb5c.ejb.interfaces.LoginRemote;

public class EJBTestClient {

	public static void main(String[] args) {
		System.out.println("Hello there");
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
			props.setProperty("org.omg.CORBA.ORBInitialHost", "172.22.25.220");
			props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
			InitialContext ctx = new InitialContext(props);
			
			
			LoginRemote login = (LoginRemote) ctx.lookup("at.fhv.itb5c.ejb.interfaces.LoginRemote");
			System.out.println(login.loginLDAP("test", "password"));
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
