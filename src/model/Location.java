package model;

import java.util.UUID;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    
    @Id
    @Column(name = "location_id", nullable = false, unique = true)
    private UUID locationId = UUID.randomUUID();

    @Column(name = "location_code", nullable = false, length = 20, unique = true)
    private String locationCode;

    @Column(name = "location_name", nullable = false, length = 100)
    private String locationName;
    
    @Enumerated(EnumType.STRING) // Ensures locationType is stored as a String in the database
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "parent_id") // Foreign key column for parent location
    private Location parent;
    
    @OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
    private List<Location> children;
    
    @OneToMany(mappedBy= "village")
    private List<User> users;

   

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Location getParentLocation() {
        return parent;
    }

    public void setParentLocation(Location parentLocation) {
        this.parent = parentLocation;
    }

  
}
