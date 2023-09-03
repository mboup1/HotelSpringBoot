package com.AppReservation.Hotels.service;

import com.AppReservation.Hotels.model.Hotel;
import com.AppReservation.Hotels.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {this.hotelRepository = hotelRepository;}

    public List<Hotel> getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotelRepository.findAll().forEach(hotel -> {
            hotels.add(hotel);
        });
        return hotels;
    }

    public Hotel getHotel(long id) { return hotelRepository.findById(id).orElse(null); }


    public void addHotel(Hotel hotel) {hotelRepository.save(hotel);}
    public void updateHotel(Hotel hotel, long id) { hotelRepository.save(hotel); }
    public void deleteHotel(long id) { hotelRepository.deleteById(id);  }



}
