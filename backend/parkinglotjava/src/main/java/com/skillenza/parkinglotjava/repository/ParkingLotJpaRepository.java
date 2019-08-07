package com.skillenza.parkinglotjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.skillenza.parkinglotjava.ParkingLot;

@Component
public interface ParkingLotJpaRepository extends JpaRepository<ParkingLot,Long> {
	
	ParkingLot findByLot(int lot);

}
