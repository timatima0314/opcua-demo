package com.takagi.opcuademo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.takagi.opcuademo.entity.TagConfig;

public interface TagConfigRepository extends JpaRepository<TagConfig, Long> {

    List<TagConfig> findByEnabledTrue();

}