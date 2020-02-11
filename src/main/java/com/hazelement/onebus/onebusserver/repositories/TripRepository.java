package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
