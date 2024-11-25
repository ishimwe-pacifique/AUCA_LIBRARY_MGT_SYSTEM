package model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "membership_types")
public class MembershipType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_type_id", nullable = false, unique = true)
    private UUID membershipTypeId;

    @Column(name = "max_books", nullable = false)
    private int maxBooks;

    @Column(name = "membership_name", nullable = false, length = 100)
    private String membershipName;

    @Column(name = "price_per_day", nullable = false)
    private int pricePerDay;

	public UUID getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(UUID membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	public int getMaxBooks() {
		return maxBooks;
	}

	public void setMaxBooks(int maxBooks) {
		this.maxBooks = maxBooks;
	}

	public String getMembershipName() {
		return membershipName;
	}

	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


}
