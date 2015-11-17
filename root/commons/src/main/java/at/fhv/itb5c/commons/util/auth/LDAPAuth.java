package at.fhv.itb5c.commons.util.auth;

import java.util.Properties;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPAuth implements ILogger {
	public static String ldapLogin(String username, String password) {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL,PropertyManager.getInstance().getProperty("at.fhv.itb5c.commons.util.auth.ldap.host") );

		DirContext ctx;
		try {
			ctx = new InitialDirContext(env);
			SearchControls ctls = new SearchControls();
			String filter = "(&(uid=" + username + ")(cn=*))";
			NamingEnumeration<SearchResult> res = ctx.search("ou=fhv, ou=People", filter, ctls);
			if (res.hasMore()) {
				SearchResult sr = res.next();
				if (!res.hasMore()) {
					env.put(Context.SECURITY_AUTHENTICATION, "simple");
					env.put(Context.SECURITY_PRINCIPAL, sr.getNameInNamespace());
					env.put(Context.SECURITY_CREDENTIALS, password);
					new InitialDirContext(env);
					return username;
				}
			}
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
