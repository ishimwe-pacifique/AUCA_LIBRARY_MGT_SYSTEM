package model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends Person {
    @Id
    @Column(name = "user_id")
    private UUID userId;
    
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name = "user_name")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)  // Lazy fetching to avoid recursive loading
    @JoinColumn(name = "village_id")
    private Location village;

    // Add the phoneNumber column
    @Column(name = "phone_number")  // Optional: Specify column name if different from the field name
    private String phoneNumber;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Location getVillage() {
        return village;
    }

    public void setVillage(Location village) {
        this.village = village;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
