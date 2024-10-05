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

			XAttendee attendee = new XAttendee("attendee", "lastname","username1",passwordEncoder.encode("1234"), XRoles.ATTENDEE);
			xAttendeeRepository.save(attendee);
			XOrganizer organizer = new XOrganizer("organizer", "lastname","username2", passwordEncoder.encode("1234"),XRoles.ORGANIZER) ;
			xOrganizerRepository.save(organizer);

			XEvent event = new XEvent("event", "description", organizer);
			xEventRepository.save(event);
			organizer.organizeEvent(event);

			XLocation location = new XLocation("line1", "line2", Country.ALBANIA, "pc1234", 30);
			xLocationRepository.save(location);

			XEventLocation eventLocation = new XEventLocation(event, location, LocalDate.now().plusDays(1), XTime.AFTERNOON);


			xEventLocationRepository.save(eventLocation);
			attendee.attendEvent(eventLocation);

			XPost post = new XPost("content", event, organizer);
			xPostRepository.save(post);

			XComment comment1 = new XComment("text",attendee, post);
			xCommentRepository.save(comment1);
			attendee.makeComment(comment1, post);

			xCommentRepository.save(comment1);
			xPostRepository.save(post);

			attendee.likePost(post);
			xAttendeeRepository.save(attendee);
			xPostRepository.save(post);

		};
	}
}
