package com.hazelement.onebus.onebusserver.repository;

import com.hazelement.onebus.onebusserver.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
