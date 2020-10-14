package com.whoiscan.resttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.whoiscan.resttest.entity.Developer;
@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Integer> {

}
