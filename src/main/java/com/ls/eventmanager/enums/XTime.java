package com.ls.eventmanager.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public enum XTime {
    MORNING(8, 12),
    AFTERNOON(12, 17),
    EVENING(17, 19),
    NIGHT(19, 22);

    private final int startHour;
    private final int endHour;

    XTime(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
