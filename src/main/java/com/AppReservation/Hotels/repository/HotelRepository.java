package com.AppReservation.Hotels.repository;

import com.AppReservation.Hotels.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
