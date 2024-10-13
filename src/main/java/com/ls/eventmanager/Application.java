package com.ls.eventmanager;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.enums.XTime;
import com.ls.eventmanager.models.*;
import com.ls.eventmanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//Evtmngx
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(XUserRepository xUserRepository,
									  XAttendeeRepository xAttendeeRepository,
									  XOrganizerRepository xOrganizerRepository,
									  XEventRepository xEventRepository,
									  XCommentRepository xCommentRepository,
									  XPostRepository xPostRepository,XLocationRepository xLocationRepository,
									  XEventLocationRepository xEventLocationRepository){
		return args ->{
			Set<XRoles> attendeeRoles = new HashSet<>();
			attendeeRoles.add(XRoles.ATTENDEE);

			Set<XRoles> organizerRoles = new HashSet<>();
			organizerRoles.add(XRoles.ATTENDEE);
			organizerRoles.add(XRoles.ORGANIZER);

			Set<XRoles> adminRoles = new HashSet<>();
			adminRoles.add(XRoles.ATTENDEE);
			adminRoles.add(XRoles.ORGANIZER);
			adminRoles.add(XRoles.ADMIN);

			XAttendee attendee = new XAttendee("attendee", "lastname","username1",passwordEncoder.encode("1234"), attendeeRoles);
			xAttendeeRepository.save(attendee);
			XAttendee attendee2 = new XAttendee("attendee", "lastname","username1",passwordEncoder.encode("1234"), adminRoles);
			xAttendeeRepository.save(attendee);
			XOrganizer organizer = new XOrganizer("organizer", "lastname","username2", passwordEncoder.encode("1234"),organizerRoles) ;
			xOrganizerRepository.save(organizer);
			XUser admin = new XUser("admin", "admin", "admin", passwordEncoder.encode("admin"), adminRoles);
			xUserRepository.save(admin);

			for (int i = 1; i <= 20; i++) {
				String eventName = "Event " + i;
				String eventDescription = "Description for event " + i;

				XEvent randomEvent = new XEvent(eventName, eventDescription, organizer);
				xEventRepository.save(randomEvent);
				organizer.organizeEvent(randomEvent);

				XLocation randomLocation = new XLocation("line1_" + i, "line2_" + i, getRandomCountry(), "pc" + (1234 + i), 30 + i);
				xLocationRepository.save(randomLocation);

				XEventLocation eventLocation = new XEventLocation(randomEvent, randomLocation, LocalDate.now().plusDays(i), getRandomTime());
				xEventLocationRepository.save(eventLocation);

				System.out.println("Created event: " + eventName + " at location: " + randomLocation.getLine1());
			}

			XEvent event = new XEvent("event", "description", organizer);
			xEventRepository.save(event);
			organizer.organizeEvent(event);

			XLocation location = new XLocation("line1", "line2", Country.ALBANIA, "pc1234", 30);
			xLocationRepository.save(location);

			XEventLocation eventLocation = new XEventLocation(event, location, LocalDate.now().plusDays(1), XTime.AFTERNOON);


			xEventLocationRepository.save(eventLocation);
			attendee.attendEvent(eventLocation);


			XComment comment1 = new XComment("text",attendee, event);
			xCommentRepository.save(comment1);
			attendee.makeComment(comment1, event);

			xCommentRepository.save(comment1);

			attendee.likeEvent(event);
			xAttendeeRepository.save(attendee);

		};

	}
	private Country getRandomCountry() {
		Country[] countries = Country.values();
		return countries[(int) (Math.random() * countries.length)];
	}

	private XTime getRandomTime() {
		XTime[] times = XTime.values();
		return times[(int) (Math.random() * times.length)];
	}
}
