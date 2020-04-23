package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aspect.MyAspect;
import com.utility.ConnectionProvider;

public class TicketDaoImpl implements TicketDao{
	
	

	public boolean login(String unm, String pwd) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM Admin_Login where unm='"+unm+"' AND pwd='"+pwd+"'");
		ResultSet rs = ps.executeQuery();
		if(rs.next()==false) {
			new MyAspect().showError();
			return false;
		}else {
			showTrain();
			new MyAspect().showAtLast();
			return true;
		}
	}

	public void showTrain() throws ClassNotFoundException, SQLException {
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM Train_Details");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("Train Number: "+rs.getInt(1)+" Name: "+rs.getString(2)+" Source: "+rs.getString(3)+" Destination: "+rs.getString(4)+" Fare: "+rs.getInt(5) );
		}
	}
	
//	(tNo number primary key,name varchar2(20),source varchar2(20),destination varchar2(20),fare number(7,2));
	//insert 

	public void bookTicket(int trainNo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int ticketId=0;
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement ps1 = con.prepareStatement("SELECT * FROM Train_Details WHERE tNo='"+trainNo+"'");
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		System.out.println("Press 1 for sleeper");
		System.out.println("Press 2 for AC");
		int c = new Scanner(System.in).nextInt();
		System.out.println("Enter number of passengers");
		int n = new Scanner(System.in).nextInt();
		PreparedStatement ps2 = con.prepareStatement("SELECT MAX(ticketid) FROM Ticket_Details");
		PreparedStatement ps3 = con.prepareStatement("Insert into ticket_details values(?,?,?,?,?,?,?)");
		if(c==1) {
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			ticketId=rs2.getInt(1);
			ps3.setInt(1,++ticketId);
			ps3.setString(2, "Sleeper");
			ps3.setString(3, rs1.getString(3));
			ps3.setString(4, rs1.getString(4));
			ps3.setInt(5,n);
			ps3.setInt(6,rs1.getInt(5)*n);
			ps3.setInt(7,trainNo);
			ps3.executeUpdate();
		}
		if(c==2) {
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			ticketId=rs2.getInt(1);
			ps3.setInt(1,++ticketId);
			ps3.setString(2, "AC");
			ps3.setString(3, rs1.getString(3));
			ps3.setString(4, rs1.getString(4));
			ps3.setInt(5,n);
			ps3.setInt(6,(rs1.getInt(5)+200)*n);
			ps3.setInt(7,trainNo);
			ps3.executeUpdate();
		}
		PreparedStatement ps4 = con.prepareStatement("SELECT * FROM Ticket_Details WHERE ticketId='"+ticketId+"'");
		ResultSet rs4 = ps4.executeQuery();
		rs4.next();
		System.out.println("*******Ticket Details  : ");
		System.out.println("Ticket Id : "+rs4.getInt(1)+"\nClass : "+rs4.getString(2)+"\nSource : "+rs4.getString(3)+"\nDestination : "+rs4.getString(4)+"\nNumber Of Passengers : "+rs4.getInt(5)+"\nFare : "+rs4.getInt(6)+"\nTrain Number : "+rs4.getInt(7));
		System.out.println("**************************");
		
	}

	public void cancelTicket(int ticketNo) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionProvider.getConnection();
		PreparedStatement ps1 = con.prepareStatement("SELECT * FROM Ticket_Details WHERE ticketid='"+ticketNo+"'");
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		if(rs1.getString(2).equals("AC")) {
			System.out.println("Refunded amount is "+ (rs1.getInt(6)-rs1.getInt(5)*100) );
		}
		else {
			System.out.println("Refunded amount is "+ (rs1.getInt(6)-rs1.getInt(5)*50) );
		}
		PreparedStatement ps2 = con.prepareStatement("DELETE FROM Ticket_Details WHERE ticketid='"+ticketNo+"'");
		ps2.executeUpdate();
		System.out.println("The cancelation charge is applicable as below\r\n" + 
				"⦁	For SL class 50 rupee per passenger\r\n" + 
				"⦁	For AC class100 rupee per passenger\r\n" + 
				"");
		
	}

}
