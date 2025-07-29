package com.service.lestplanit.utils;

import com.service.lestplanit.models.Person;
import com.service.lestplanit.models.Event;
import com.service.lestplanit.models.Subscription;
import com.service.lestplanit.security.models.UserEntity;
import com.service.lestplanit.security.services.UserService;
import com.service.lestplanit.services.EventService;
import com.service.lestplanit.services.PersonService;
import com.service.lestplanit.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class InitialDataCreator {
    @Autowired
    UserService userService;
    @Autowired
    PersonService personService;

    @Autowired
    EventService eventService;
    @Autowired
    SubscriptionService subscriptionService;

    public void loadInitialData() {
//        Create the System user.
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("admin@user.com");
        userEntity.setUsername("admin");
        userEntity.setPassword("password1234");
        userService.createUser(Set.of("ADMIN"), userEntity);

//        Create Persons on the system
        Person john = new Person();
        john.setId(8822948L);
        john.setName("John");
        john.setLastname("Doe");
        john.setEmail("John@user.com");
        personService.createPerson(john);
//        Create Events on the system
        Event conference = new Event();
        conference.setName("Event 1");
        conference.setStartDate(LocalDateTime.now());
        conference.setEndDate(LocalDateTime.now());
        conference.setLocation("Location 1");
        conference.setCost(200);
        eventService.createEvent(conference);
        Event conference2 = new Event();
        conference2.setName("Event 2");
        conference2.setStartDate(LocalDateTime.now());
        conference2.setEndDate(LocalDateTime.now());
        conference2.setLocation("Location 2");
        conference2.setCost(200);
        eventService.createEvent(conference2);
////        Create subscriptions
//        Subscription subscriptionEntity = new Subscription();
//        subscriptionService.createSubscription(subscriptionEntity, john.getId(), conference.getId());
    }
}
