package main.university;

import main.dao.Identified;

public class Room implements Identified<Integer>{
	
	private Integer id;
	private Integer capacity;
	
	private String address;

	public Room(){
	}
			
	public Room(Integer id, Integer capacity, String address) {
		this.id = id;
		this.capacity = capacity;
		this.address = address;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Room" + id + " [capacity=" + capacity + ", address="
				+ address + "]";
	}		
}
