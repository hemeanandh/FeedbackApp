package MyGradleProject.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="designation_id")
	private int designationId;
	
	@Column(name="supervisor_id")
	private int supervisorId;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="emp_password")
	private String empPassword;
	

}
