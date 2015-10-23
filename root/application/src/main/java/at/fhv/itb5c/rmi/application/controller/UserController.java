package at.fhv.itb5c.rmi.application.controller;

import at.fhv.itb5c.rmi.dto.interfaces.IUser;
import at.fhv.itb5c.rmi.dto.interfaces.IUserController;

public class UserController implements IUserController {
	
	//TODO: change IUser to user when merged with model branch
	@Override
	public IUser createUser(IUser user) {
		return null;
	}

}