package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.jdbc.PreparedStatement;

import student.Student;

public class Crud {
	Scanner scan = new Scanner(System.in);

	public void createRecord(Connection con) throws SQLException {
		System.out.println();
		System.out.println("####### Add Student #######");
		System.out.println();

		// Roll Number
		System.out.print("Enter Roll No: ");

		while (!scan.hasNextInt()) {
			System.out.println("Invalid Roll No !!!");
			System.out.print("Enter Roll No again: ");
			scan.next();
		}
		int roll = scan.nextInt();

		String sql = "SELECT * FROM student WHERE roll = ?";
		PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
		st.setInt(1, roll);
		ResultSet rs = st.executeQuery();
		boolean check = rs.next();

		while (check) {
			System.out.println("Roll no " + roll + " already exists !!");
			System.out.print("Enter Roll no again: ");
			while (!scan.hasNextInt()) {
				System.out.println("Invalid Roll No !!!");
				System.out.print("Enter Roll No again: ");
				scan.next();
			}
			roll = scan.nextInt();
			sql = "SELECT * FROM student WHERE roll = ?";
			st = (PreparedStatement) con.prepareStatement(sql);
			st.setInt(1, roll);
			rs = st.executeQuery();
			check = rs.next();
		}

		// First Name
		System.out.println();
		System.out.print("Enter First Name: ");
		String fName = scan.next();

		while (fName.length() < 3 || fName.length() > 12) {
			System.out.println("First name should be of 2-12 character long !!!");
			System.out.print("Enter First Name: ");
			fName = scan.next();
		}

		// Last Name
		System.out.println();
		System.out.print("Enter Last Name: ");
		String lName = scan.next();

		while (lName.length() < 3 || lName.length() > 12) {
			System.out.println("Last name should be of 2-12 character long !!!");
			System.out.print("Enter Last Name: ");
			lName = scan.next();
		}

		// Address
		System.out.println();
		System.out.print("Enter Address: ");
		String address = scan.next();

		while (address.length() < 3 || address.length() > 25) {
			System.out.println("Address should be of 2-12 character long !!!");
			System.out.print("Enter Address: ");
			address = scan.next();
		}

		// contact no
		System.out.println();
		System.out.print("Enter Contact No. (Mobile Only): ");
		long contactNo = scan.nextLong();
		int contactLen = String.valueOf(contactNo).length();

		while (contactLen != 10) {
			System.out.println("Invalid Mobile Number !!!");
			System.out.print("Enter Contact No. (Mobile Only): ");
			contactNo = scan.nextLong();
			contactLen = String.valueOf(contactNo).length();
		}
		Student newRecord = new Student(con, roll, fName, lName, address, contactNo);
		newRecord.insertRecord();
	}

	public void displayList(Connection con) throws SQLException {
		String sql = "SELECT * FROM student ORDER BY roll ASC";
		Statement st = (Statement) con.createStatement();
		ResultSet resultSet = st.executeQuery(sql);

		System.out.println();
		System.out.println();

		if (resultSet.next()) {
			System.out.println("=================================");
			System.out.println("========= Student Lists =========");
			System.out.println("=================================");
			System.out.println();
			System.out.println("Roll no.    Name                      Address            	  Contact No.");
			System.out.println(
					"-------------------------------------------------------------------------------------------");

			while (resultSet.next()) {
				String fullname = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
				System.out.printf("%-10d%-26s%-30s%-12d\n", resultSet.getInt("roll"), fullname,
						resultSet.getString("address"), resultSet.getLong("contact"));
			}

		} else {
			System.out.println("No Records to Display !!!!!!");
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
		System.out.println();
	}

	public void searchRecord(Connection con) throws SQLException {
		System.out.println();
		System.out.println("####### Search Student #######");
		System.out.println();
		System.out.print("Enter Roll number of student: ");
		while (!scan.hasNextInt()) {
			System.out.println("Invalid Roll No !!!");
			System.out.print("Enter Roll No again: ");
			scan.next();
		}
		int roll = scan.nextInt();

		String sql = "SELECT * FROM student WHERE roll = ?";
		PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
		st.setInt(1, roll);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			String fullname = rs.getString("first_name") + " " + rs.getString("last_name");
			System.out.println("Record Found !!");
			System.out.println("Roll no: " + rs.getInt("roll"));
			System.out.println("Name: " + fullname);
			System.out.println("Address: " + rs.getString("address"));
			System.out.println("Contact: " + rs.getLong("contact"));
			System.out.println("------------------------------------------");
			System.out.println();
		} else {
			System.out.println("Record Not Found !!");
			System.out.println("------------------------------------------");
			System.out.println();
		}

	}

	public void deleteRecord(Connection con) throws SQLException {
		System.out.println();
		System.out.println("####### Delete Student #######");
		System.out.println();
		System.out.print("Enter Roll number of student to delete: ");
		while (!scan.hasNextInt()) {
			System.out.println("Invalid Roll No !!!");
			System.out.print("Enter Roll No again: ");
			scan.next();
		}
		int roll = scan.nextInt();

		String sql = "SELECT * FROM student WHERE roll = ?";
		PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
		st.setInt(1, roll);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			String deleteQuery = "DELETE FROM student WHERE roll=?";
			PreparedStatement del = (PreparedStatement) con.prepareStatement(deleteQuery);
			del.setInt(1, roll);

			int i = del.executeUpdate();

			if (i > 0) {
				System.out.println("Record Deleted Successfully ...");
				System.out.println("------------------------------------------");
			} else {
				System.out.println("Something went wrong !!");
				System.out.println("------------------------------------------");
			}

		} else {
			System.out.println("Record Not Found !!");
			System.out.println("------------------------------------------");
			System.out.println();
		}

	}

	public void updateRecord(Connection con) throws SQLException {
		System.out.println();
		System.out.println("####### Update Record #######");
		System.out.println();
		System.out.print("Enter Roll number of student: ");
		while (!scan.hasNextInt()) {
			System.out.println("Invalid Roll No !!!");
			System.out.print("Enter Roll No again: ");
			scan.next();
		}
		int roll = scan.nextInt();

		String sql = "SELECT * FROM student WHERE roll = ?";
		PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
		st.setInt(1, roll);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			String fullname = rs.getString("first_name") + " " + rs.getString("last_name");
			System.out.println("Record Found !!");
			System.out.println("Roll no: " + rs.getInt("roll"));
			System.out.println("Name: " + fullname);
			System.out.println("Address: " + rs.getString("address"));
			System.out.println("Contact: " + rs.getLong("contact"));
			System.out.println("------------------------------------------");
			System.out.println();

			// First Name
			System.out.println();
			System.out.print("Enter New First Name: ");
			String fName = scan.next();

			while (fName.length() < 3 || fName.length() > 12) {
				System.out.println("First name should be of 2-12 character long !!!");
				System.out.print("Enter First Name: ");
				fName = scan.next();
			}

			// Last Name
			System.out.println();
			System.out.print("Enter New Last Name: ");
			String lName = scan.next();

			while (lName.length() < 3 || lName.length() > 12) {
				System.out.println("Last name should be of 2-12 character long !!!");
				System.out.print("Enter Last Name: ");
				lName = scan.next();
			}

			// Address
			System.out.println();
			System.out.print("Enter New Address: ");
			String address = scan.next();

			while (address.length() < 3 || address.length() > 25) {
				System.out.println("Address should be of 2-12 character long !!!");
				System.out.print("Enter New Address : ");
				address = scan.next();
			}

			// contact no
			System.out.println();
			System.out.print("Enter Contact No. (Mobile Only): ");
			long contactNo = scan.nextLong();
			int contactLen = String.valueOf(contactNo).length();

			while (contactLen != 10) {
				System.out.println("Invalid Mobile Number !!!");
				System.out.print("Enter Contact No. (Mobile Only): ");
				contactNo = scan.nextLong();
				contactLen = String.valueOf(contactNo).length();
			}

			Student updateRecord = new Student(con, roll, fName, lName, address, contactNo);
			updateRecord.updateStudentRecord();

		} else {
			System.out.println("Record Not Found !!");
			System.out.println("------------------------------------------");
			System.out.println();
		}

	}
}
