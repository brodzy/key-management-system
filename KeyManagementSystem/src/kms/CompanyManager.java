package kms;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CompanyManager {
	//Lists
	ArrayList<Building> buildings = new ArrayList<Building>();
	ArrayList<Suite> suites = new ArrayList<Suite>();
	ArrayList<Room> rooms = new ArrayList<Room>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	ArrayList<String> accessAttempts = new ArrayList<String>();
	
	//Constructor
	public CompanyManager() {
	}
	
	//Adders
	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	public void addSuite(Suite s) {
		suites.add(s);
	}
	
	public void addRoom(Room r) {
		rooms.add(r);
	}
	
	public void addEmployee(Employee e) {
		employees.add(e);
	}
	
	//Removers
	public void remBuilding(Building b) {
		buildings.remove(b);
	}
	
	public void remSuite(Suite s) {
		suites.remove(s);
	}
	
	public void remRoom(Room r) {
		rooms.remove(r);
	}
	
	public void remEmployee(Employee e) {
		employees.remove(e);
	}
	
	//Getters
	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public ArrayList<Suite> getSuites() {
		return suites;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public ArrayList<String> getAccessAttempts() {
		return accessAttempts;
	}
	
	public Building getBuilding(int i) {
		if(i>=0 && i<buildings.size()) {
			return buildings.get(i);
		}
		return null;
	}
	
	public Suite getSuite(int i) {
		if(i>=0 && i<suites.size()) {
			return suites.get(i);
		}
		return null;
	}
	
	public Room getRoom(int i) {
		if(i>=0 && i<rooms.size()) {
			return rooms.get(i);
		}
		return null;
	}
	
	public Employee getEmployee(int i) {
		if(i>=0 && i<employees.size()) {
			return employees.get(i);
		}
		return null;
	}
	
	public int getNumBuildings() {
		return buildings.size();
	}
	
	public int getNumSuites() {
		return suites.size();
	}
	
	public int getNumRooms() {
		return rooms.size();
	}
	
	public int getNumEmployees() {
		return employees.size();
	}
	
	public int getEmployeeById(String id) {
        Employee dummy = new Employee("temp", "-000");
		for(Employee e: employees) {
            if(e.getId().equals(id)) {dummy = e;}
        }
        return employees.indexOf(dummy);
    }
	
	//Clear and Contains
	public void clear() {
		buildings.clear();
		suites.clear();
		rooms.clear();
		employees.clear();
	}
	
	public boolean containsBuilding(String name, String buildingCode) {
		return buildings.contains(new Building(name, buildingCode));
	}
	
	public boolean containsSuite(String name, String buildingCode, String suiteCode) {
		return suites.contains(new Suite(name, buildingCode, suiteCode));
	}
	
	public boolean containsRoom(String buildingCode, String suiteCode, String roomNumber) {
		return rooms.contains(new Room(buildingCode, suiteCode, roomNumber));
	}
	
	public boolean containsEmployee(String name, String id) {
		return employees.contains(new Employee(name, id));
	}
	
	//test employee access
	public String testAccess(String eId, String roomNum) {
		//date time set up
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		
		//testing access
		String result =  "";
		if(getEmployeeById(eId) == -1)
			result = "Security Alert: employee ID not recognized";
		else {
			Employee e = employees.get(getEmployeeById(eId));
			ArrayList<Room> employeeAccess = e.getFullAccess();
			for(int i = 0; i < employeeAccess.size(); i++) {
				if(employeeAccess.get(i).getRoomNumber().equals(roomNum))
					result = "Success";
				else result = "Failure";
			}
		}
		
		//recording attempt and returning result
		accessAttempts.add("Employee: #" + eId + " attempted to access room: #" + roomNum + " at: " + formatter.format(date) 
			+ " attempt result: " + result);
		return result;
	}
}
