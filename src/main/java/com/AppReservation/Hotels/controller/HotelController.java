package com.AppReservation.Hotels.controller;

import com.AppReservation.Hotels.model.Hotel;
import com.AppReservation.Hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    public List<Hotel> getHotels(){return hotelService.getHotels();}

    @RequestMapping(method = RequestMethod.GET, value = "/hotel/{id}")
    public Hotel getHotel(@PathVariable long id){return hotelService.getHotel(id); }

    @RequestMapping(method =  RequestMethod.POST, value = "/hotel/{id}")
    public void addHotel(@RequestBody Hotel hotel){hotelService.addHotel(hotel);}

    @RequestMapping(method =  RequestMethod.PUT, value = "/hotel/{id}")
    public void updateHotel(@RequestBody Hotel hotel, @PathVariable long id){
        hotelService.updateHotel(hotel, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel/{id}")
    public void deleteHotel(@PathVariable long id){hotelService.deleteHotel(id);}

}

