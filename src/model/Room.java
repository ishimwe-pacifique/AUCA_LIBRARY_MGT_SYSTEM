package model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @Column(name = "room_id", nullable = false, unique = true)
    private UUID roomId;  // No need for @GeneratedValue, UUID is generated manually.

    @Column(name = "room_code", nullable = false, length = 50, unique = true)
    private String roomCode;

    @OneToMany(mappedBy = "room")
    private List<Shelf> shelves;

    // Getters and Setters
    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    @PrePersist
    public void prePersist() {
        if (this.roomId == null) {
            this.roomId = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomCode='" + roomCode + '\'' +
                '}';
    }
}