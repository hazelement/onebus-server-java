package com.hazelement.onebus.onebusserver.repository;

import com.hazelement.onebus.onebusserver.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stop, Integer> {
}
