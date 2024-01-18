package com.netwizsoft.carrentalservice.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netwizsoft.carrentalservice.model.Status;
import com.netwizsoft.carrentalservice.model.Vehicle;
import com.netwizsoft.carrentalservice.repository.VehicleRepository;


@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		vehicle.setStatus(Status.AVAILABLE);
		vehicle.setOwner(null);
		vehicle.setAssociationDate(null);
		
		return vehicleRepository.save(vehicle);
	}

	@Override
	public void validateVehicle(String vehicleId) {
		// TODO Auto-generated method stub
		vehicleRepository.findById(Long.valueOf(vehicleId)).orElseThrow();
	}

	@Override
	public void associate(String vehicleId, String userId) {
		// TODO Auto-generated method stub
		var vehicle = vehicleRepository.findById(Long.valueOf(vehicleId)).filter(v -> v.getStatus() == Status.AVAILABLE).orElseThrow();
		
		vehicle.setOwner(userId);
		vehicle.setAssociationDate(new Date());
		vehicle.setStatus(Status.ASSOCIATED);
		
		vehicleRepository.save(vehicle);
	}

	@Override
	public void deleteVehicle(String vehicleId, String userId) {
		// TODO Auto-generated method stub
		var vehicle = vehicleRepository.findById(Long.valueOf(vehicleId))
				.filter(v -> v.getStatus() == Status.ASSOCIATED)
				.filter(v -> userId.equals(v.getOwner()))
				.orElseThrow();
		
		vehicle.setOwner(null);
		vehicle.setAssociationDate(null);
		vehicle.setStatus(Status.AVAILABLE);
		
		vehicleRepository.save(vehicle);
	}

}
