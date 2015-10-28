package at.fhv.itb5c.rmi.server;

import java.io.Serializable;

import at.fhv.itb5c.application.controller.UserFactoryImpl;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;

public class UserFactoryImplRMI extends UserFactoryImpl implements IUserFactoryRMI, Serializable {
	private static final long serialVersionUID = 1L;
}