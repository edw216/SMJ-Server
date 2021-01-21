package com.experiencers.server.smj.repository;

import com.experiencers.server.smj.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
}

