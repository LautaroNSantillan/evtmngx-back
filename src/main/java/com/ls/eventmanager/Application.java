package com.ls.eventmanager;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.enums.XTime;
import com.ls.eventmanager.models.*;
import com.ls.eventmanager.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
//Evtmngx
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(XUserRepository xUserRepository,
									  XAttendeeRepository xAttendeeRepository,
									  XOrganizerRepository xOrganizerRepository,
									  XEventRepository xEventRepository,
									  XCommentRepository xCommentRepository,
									  XPostRepository xPostRepository){
		return args ->{

			XAttendee attendee = new XAttendee("attendee", "lastname","username1", XRoles.ATTENDEE);
			xAttendeeRepository.save(attendee);
			XOrganizer organizer = new XOrganizer("organizer", "lastname","username2", XRoles.ORGANIZER) ;
			xOrganizerRepository.save(organizer);

			XEvent event = new XEvent("event", LocalDateTime.now(), "description", new XEventLocation("line1", "line2", Country.AFGHANISTAN, "postal", 30, XTime.MORNING));
			organizer.organizeEvent(event);
			attendee.attendEvent(event);
			xEventRepository.save(event);

			XPost post = new XPost("content", event, organizer);
			xPostRepository.save(post);

			XComment comment1 = new XComment("text",attendee, post);
			xCommentRepository.save(comment1);
			attendee.makeComment(comment1, post);

			xCommentRepository.save(comment1);
			xPostRepository.save(post);

			attendee.likePost(post);
			xAttendeeRepository.save(attendee);



		};
	}
}
