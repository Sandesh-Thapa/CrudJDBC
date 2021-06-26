package student;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;

public class Student{
	private int roll;
	private String firstName;
	private String lastName;
	private String address;
	private long contactNo;
	private Connection con;

	public Student(Connection con, int roll, String firstName, String lastName, String address, long contactNo) {
		this.con = con;
		this.roll = roll;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
	}
	
	public void insertRecord() throws SQLException {
		String sql = "INSERT INTO student (roll, first_name, last_name, address, contact) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = (PreparedStatement) this.con.prepareStatement(sql);
        
        st.setInt(1, this.roll);
        st.setString(2, this.firstName);
        st.setString(3, this.lastName);
        st.setString(4, this.address);
        st.setLong(5, this.contactNo);
        
        int i = st.executeUpdate();
        
        if (i > 0) {
        	System.out.println("Record Added Successfully !!!");
        }else {
        	System.out.println("Something went wrong !!!");
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();        
	}
	
	public void updateStudentRecord() throws SQLException {
		String sql = "UPDATE student SET first_name=?, last_name=?, address=?, contact=? WHERE roll=?";
        PreparedStatement st = (PreparedStatement) this.con.prepareStatement(sql);
        
        st.setString(1, this.firstName);
        st.setString(2, this.lastName);
        st.setString(3, this.address);
        st.setLong(4,this.contactNo);
        st.setInt(5, this.roll);
        
        int i = st.executeUpdate();
        
        if (i > 0) {
        	System.out.println("Record Updated Successfully !!!");
        }else {
        	System.out.println("Something went wrong !!!");
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();        
	}
	

}
