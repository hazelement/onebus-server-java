package com.hazelement.onebus.onebusserver.repository;

import com.hazelement.onebus.onebusserver.model.StopTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopTimeRepository extends JpaRepository<StopTime, Integer> {
}
