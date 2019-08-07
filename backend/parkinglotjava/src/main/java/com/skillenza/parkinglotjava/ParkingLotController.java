package com.skillenza.parkinglotjava;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillenza.parkinglotjava.repository.ParkingLotJpaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class ParkingLotController {
	
	@Autowired
	ParkingLotJpaRepository parkingLotjpaRepository;
	
	@GetMapping("/parkings")
	public List<ParkingLot> GetParkingDetails(){
		 
		return parkingLotjpaRepository.findAll();
	}
	
	@PostMapping("/parkings")
	public JSONObject SetParking(@RequestBody ParkingLot parkedvehicledata ){
		ParkingLot pvdata=null;
		JSONObject obj=new JSONObject();
		pvdata=parkingLotjpaRepository.findByLot(parkedvehicledata.getLot());
		String rData=new String();
		if(pvdata==null){
			parkingLotjpaRepository.save(parkedvehicledata);
			obj.put("data", "Success");
		}
		else{
			obj.put("data", "alreday data exits");
		}
		
		
		return obj;
	}
	
	

}
