package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.models.XLocation;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@Getter@Setter@NoArgsConstructor
public class DTOLocation {
    private UUID id;
    private String line1;
    private String line2;
    private Country country;
    private String postal;
    private Integer capacity;

    public DTOLocation(XLocation location) {
        this.id = location.getId();
        this.line1 = location.getLine1();
        this.line2 = location.getLine2();
        this.country = location.getCountry();
        this.postal = location.getPostal();
        this.capacity = location.getCapacity();
    }

}
