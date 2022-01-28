package com.example.demo.repository;

import com.example.demo.model.Gives.Gives;
import com.example.demo.model.Reservation.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

    @Query(value="select * from Reservations ",nativeQuery = true)
    Optional<Reservations> findAllReservations();

    @Query(value = "select * from reservation where projection_id=?", nativeQuery = true)
    public List<Reservations> findByProjectionId(Integer projection_id);

    @Query(value= "select * from reservation",nativeQuery = true)
    public List<Reservations> findAllRes();



}