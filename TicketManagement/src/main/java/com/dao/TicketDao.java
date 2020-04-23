package com.dao;

import java.sql.SQLException;

public interface TicketDao {
	public boolean login(String unm,String pwd) throws ClassNotFoundException, SQLException;
	public void showTrain() throws ClassNotFoundException, SQLException;
	public void bookTicket(int trainNo) throws ClassNotFoundException, SQLException;
	public void cancelTicket(int ticketNo) throws ClassNotFoundException, SQLException;
}
