package com.experiencers.server.smj.alarm;

import com.experiencers.server.smj.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
}

