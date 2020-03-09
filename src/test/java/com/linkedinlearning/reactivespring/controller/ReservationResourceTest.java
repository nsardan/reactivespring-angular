package com.linkedinlearning.reactivespring.controller;

import com.linkedinlearning.reactivespring.model.Reservation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.linkedinlearning.reactivespring.controller.ReservationResource.RESERVATION;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {

    @Autowired
    private ApplicationContext applicationContext;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(this.applicationContext).build();

        reservation = new Reservation(123l,
                LocalDate.now(), LocalDate.now().plus(10, ChronoUnit.DAYS),
                150);
    }

    @Test
    public void getAllReservations() {
        webTestClient.get()
                .uri(RESERVATION).exchange().expectStatus().isOk().expectBodyList(Reservation.class);
    }

    @Test
    public void createReservation() {
        webTestClient.post()
                .uri(RESERVATION)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(Reservation.class);
    }
}