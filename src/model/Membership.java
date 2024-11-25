package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_id", nullable = false, unique = true)
    private UUID membershipId;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "membership_code", nullable = false, length = 50, unique = true)
    private String membershipCode;

    @Enumerated(EnumType.STRING) // Ensures status is stored as a String in the database
    @Column(name = "status", nullable = false)
    private MembershipStatus status;

    @Column(name = "membership_type", nullable = false, length = 50)
    private String membershipType; // Stores membership type as a String (e.g., Gold, Silver, Striver)

    @Column(name = "reader_id", nullable = false, length = 50)
    private String readerId; // Stores the user/reader ID as a simple String

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    // Getters and Setters
    public UUID getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(UUID membershipId) {
        this.membershipId = membershipId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMembershipCode() {
        return membershipCode;
    }

    public void setMembershipCode(String membershipCode) {
        this.membershipCode = membershipCode;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
