package model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shelves")
public class Shelf {

    @Id
    @Column(name = "shelf_id", nullable = false, unique = true)
    private UUID shelfId;  // Changed from String to UUID

    @Column(name = "available_stock", nullable = false)
    private int availableStock;

    @Column(name = "book_category", nullable = false, length = 100)
    private String bookCategory;

    @Column(name = "borrowed_number", nullable = false)
    private int borrowedNumber;

    @Column(name = "initial_stock", nullable = false)
    private int initialStock;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false) // Foreign key column for room
    private Room room;

    public UUID getShelfId() {
        return shelfId;
    }

    public void setShelfId(UUID shelfId) {
        this.shelfId = shelfId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(int borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    public int getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @PrePersist
    public void prePersist() {
        // Check if the shelfId is null before generating a new UUID
        if (this.shelfId == null) {
            this.shelfId = UUID.randomUUID(); // Generating UUID instead of String
        }
    }
}