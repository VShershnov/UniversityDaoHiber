package main.java.schedule;

import main.java.university.Room;

public class RoomSchedule extends Schedule {

	private Room room;
	
	public RoomSchedule(Integer id, Room room) {
		super(id);
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "RoomSchedule"+ getId()+ "[room=" + room.getAddress() 
				+ ", ScheduleSlots=" + getScheduleSlots().size() + "]";
	}
}
