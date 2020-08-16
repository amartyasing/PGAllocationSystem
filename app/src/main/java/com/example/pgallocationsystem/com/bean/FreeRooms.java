package com.example.pgallocationsystem.com.bean;

public class FreeRooms {
    String roomno,roomid;

    @Override
    public String toString() {
        return "Roomid-"+roomid+"     "+"Roomno-"+roomno;
    }

    public FreeRooms() {
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public FreeRooms(String roomno, String roomid) {
        this.roomno = roomno;
        this.roomid = roomid;
    }
}
