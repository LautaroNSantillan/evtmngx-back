package com.ls.eventmanager;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.*;
import com.ls.eventmanager.repositories.XAttendeeRepository;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.repositories.XOrganizerRepository;
import com.ls.eventmanager.repositories.XUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class EvtmngxApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvtmngxApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(XUserRepository xUserRepository, XAttendeeRepository xAttendeeRepository, XOrganizerRepository xOrganizerRepository, XEventRepository xEventRepository){
		return args ->{

			XAttendee attendee = new XAttendee("attendee", "lastname","username1", XRoles.ATTENDEE);
			xAttendeeRepository.save(attendee);
			XOrganizer organizer = new XOrganizer("organizer", "lastname","username2", XRoles.ORGANIZER) ;
			xOrganizerRepository.save(organizer);

			XEvent event = new XEvent("event", LocalDateTime.now(), "description", new EventLocation("line1", "line2", Country.AFGHANISTAN, "postal"));
			organizer.organizeEvent(event);
			attendee.attendEvent(event);
			xEventRepository.save(event);


			attendee.likeEvent(event);
			xAttendeeRepository.save(attendee);



		};
	}
}
