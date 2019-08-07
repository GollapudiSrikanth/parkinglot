package com.skillenza.parkinglotjava;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="parkinglots")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"created_at","updated_at"},allowGetters=true)
public class ParkingLot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private int lot;
	
	@Column(nullable=false)
    private int parking_amount;
    
    @Column(nullable=false)
    private int parking_duration;
    
    @Column(unique=true,nullable=false)
    private int vehicle_number;

    private OffsetDateTime created_at;
    
    private OffsetDateTime updated_at;
    
    
    public OffsetDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(OffsetDateTime created_at) {
		this.created_at = created_at;
	}
	public OffsetDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(OffsetDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
     public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getLot() {
		return lot;
	}
	public void setLot(int lot) {
		this.lot = lot;
	}
	public int getParking_amount() {
		return parking_amount;
	}
	public void setParking_amount(int parking_amount) {
		this.parking_amount = parking_amount;
	}
	public int getParking_duration() {
		return parking_duration;
	}
	public void setParking_duration(int parking_duration) {
		this.parking_duration = parking_duration;
	}
	public int getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(int vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	

	

}
