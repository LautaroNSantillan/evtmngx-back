package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.Country;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@Getter@NoArgsConstructor
public class DTOLocation {
    private UUID id;
    private String line1;
    private String line2;
    private Country country;
    private String postal;
    private Integer capacity;
}
