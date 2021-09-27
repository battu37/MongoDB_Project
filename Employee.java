public class Employee {
	private int empId;
	private String employeeName;
	private String employeeDesignation;
	private double salary;	
	public Employee(int id,String name, String desi, int sal) {
		this.empId=id;
		this.employeeName=name;
		this.employeeDesignation=desi;
		this.salary=sal;
	}
	public String toString(){
		return String.format("%d - %s - %s - %f",empId, employeeName,employeeDesignation, salary);
	}
	public int getEmployeeId() {
		return empId;
	}
	public void setEmployeeId(int id) {
		this.empId=id;
	}
	public String getEmployeeName() { return employeeName; }

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}
	public String getEmployeeDesignation(){ 
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation){
		this.employeeDesignation = employeeDesignation;
	}
	public double getSalary() { return salary; }
	public void setSalary(double d) { this.salary = d; }
}
