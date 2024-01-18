package com.netwizsoft.carrentalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netwizsoft.carrentalservice.model.Vehicle;
import com.netwizsoft.carrentalservice.services.VehicleService;

@RestController
@RequestMapping("v1")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/vehicle")
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.createVehicle(vehicle);
	}
	
	@PostMapping("/vehicle/{vehicleId}/users/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void associate(@PathVariable(name = "vehicleId") String vehicleId, @PathVariable(name = "userId") String userId) {
		vehicleService.validateVehicle(vehicleId);
		vehicleService.associate(vehicleId, userId);
	}
	
	@DeleteMapping("/vehicle/{vehicleId}/user/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVehicle(@PathVariable(name = "vehicleId") String vehicleId, @PathVariable(name = "userId") String userId) {
		vehicleService.validateVehicle(vehicleId);
		vehicleService.deleteVehicle(vehicleId, userId);
	}
}
