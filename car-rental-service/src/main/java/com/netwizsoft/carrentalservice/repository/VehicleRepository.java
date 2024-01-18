package com.netwizsoft.carrentalservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netwizsoft.carrentalservice.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
