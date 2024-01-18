package com.netwizsoft.carrentalservice.services;

import org.springframework.stereotype.Service;

import com.netwizsoft.carrentalservice.model.Vehicle;

@Service
public interface VehicleService {
	
	/**
	 * 
	 * @param vehicle
	 * @return Vehicle
	 */
	Vehicle createVehicle(Vehicle vehicle);
	
	/**
	 * 
	 * @param vehicleId
	 */
	void validateVehicle(String vehicleId);
	
	/**
	 * 
	 * @param vehicleId
	 * @param userId
	 */
	void associate(String vehicleId, String userId);
	
	/**
	 * 
	 * @param vehicleId
	 * @param userId
	 */
	void deleteVehicle(String vehicleId, String userId);
}
