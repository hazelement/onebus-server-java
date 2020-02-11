package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.StopTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopTimeRepository extends JpaRepository<StopTime, Integer> {
}
