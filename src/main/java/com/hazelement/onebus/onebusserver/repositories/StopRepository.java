package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stop, Integer> {
}
