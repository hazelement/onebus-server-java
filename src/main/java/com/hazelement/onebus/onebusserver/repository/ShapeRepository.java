package com.hazelement.onebus.onebusserver.repository;

import com.hazelement.onebus.onebusserver.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<Shape, Integer> {
}
