package com.linkedinlearning.reactivespring.controller;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping(ReservationResource.RESERVATION)
@CrossOrigin
public class ReservationResource {
    public static final String RESERVATION = "/room/v1/reservation";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getReservationById(@PathVariable String id){
        return reservationService.getReservation(id);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> getAllReservations(){
        return reservationService.listAllReservations();
    }

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation){
        return reservationService.createReservation(reservation);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> updatePrice(@PathVariable String id,
                                         @RequestBody Mono<Reservation> reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping(path="{id}")
    public Mono<Boolean> deleteReservation(String id){
        return reservationService.deleteReservation(id);
    }
}
