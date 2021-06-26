package main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import config.Config;
import crud.Crud;

public class Menu {

	public void menu() throws ClassNotFoundException, SQLException {
		Crud u = new Crud();
		Config con = new Config();
		while (true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("=================================");
				System.out.println("=== Student Management System ===");
				System.out.println("=================================");
				System.out.println("1. Add Student");
				System.out.println("2. Display Student List");
				System.out.println("3. Search Student");
				System.out.println("4. Update Student Record");
				System.out.println("5. Delete Student Record");
				System.out.println("6. Exit");
				System.out.println("---------------------------------------------------------");
				System.out.println("Press the respective menu number for respective operation");
				System.out.print("Enter your choice: ");
				int choice = scan.nextInt();

				switch (choice) {
					case 1:
						u.createRecord(con.connect());
						break;
					case 2:
						u.displayList(con.connect());
						break;
					case 3:
						u.searchRecord(con.connect());
						break;
					case 4:
						u.updateRecord(con.connect());
						break;
					case 5:
						u.deleteRecord(con.connect());
						break;
					case 6:
						System.out.println("Have a great day !!!");
						System.exit(0);
					default:
						defaultMsg();
						break;
				}
			} catch (InputMismatchException e) {
				defaultMsg();
			}
		}
	}

	static void defaultMsg() {
		System.out.println("Wrong choice !!!  Enter only 1/2/3/4/5/6/7/8");
		System.out.println("-------------------------------------------------");
	}
}