package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.enums.XTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Getter@Setter@NoArgsConstructor
public class XLocation {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String line1;
    private String line2;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String postal;
    private Integer capacity;
    @OneToMany(mappedBy = "location")
    private Set<XEventLocation> eventLocations = new HashSet<>();

    public XLocation(String line1, String line2, Country country, String postal, Integer capacity) {
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.postal = postal;
        this.capacity = capacity;
    }

    public XLocation(String name, String line1, String line2, Country country, String postal, Integer capacity) {
    }

    public void addEventLocation(XEventLocation el){
        this.eventLocations.add(el);
    }
}
