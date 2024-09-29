package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XComment;
import com.ls.eventmanager.models.XPost;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter@Setter
@NoArgsConstructor
public class DTOAttendee extends DTOUser {

}
