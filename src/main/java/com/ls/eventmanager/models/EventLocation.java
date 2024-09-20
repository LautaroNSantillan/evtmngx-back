package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.Country;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Embeddable
public class EventLocation {
    private String line1;
    private String line2;
    private Country country;
    private String postal;
}
