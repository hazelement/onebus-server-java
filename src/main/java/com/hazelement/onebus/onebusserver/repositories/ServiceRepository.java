package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Service findByServiceId(String serviceId);
}
