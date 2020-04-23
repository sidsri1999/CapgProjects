package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.dao.TicketDaoImpl;

public class MainApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		TicketDaoImpl tdo = new TicketDaoImpl();
		while(true) {
			System.out.println("********* Admin Login *********");
			System.out.println("Enter UserName");
			String unm=sc.next();
			System.out.println("Enter Password");
			String pwd=sc.next();
			if(tdo.login(unm, pwd)) {
				while(true) {
					System.out.println("***************************");
					System.out.println("Press 1 for ticket booking");
					System.out.println("Press 2 for ticket Cancellation");
					int c = sc.nextInt();
					if(c==1) {
						System.out.println("Enter Train Number");
						int tno = sc.nextInt();
						tdo.bookTicket(tno);
					}
					if(c==2) {
						System.out.println("Enter Ticket Id");
						int tno = sc.nextInt();
						tdo.cancelTicket(tno);
					}
				}
			}
		}
	}

}
