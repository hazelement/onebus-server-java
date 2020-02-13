package com.hazelement.onebus.onebusserver.repositories;

import com.hazelement.onebus.onebusserver.models.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShapeRepository extends JpaRepository<Shape, Integer> {
    List<Shape> findByShapeId(String shapeId);
}
