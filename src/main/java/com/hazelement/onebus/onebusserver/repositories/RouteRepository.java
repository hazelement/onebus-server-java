package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
