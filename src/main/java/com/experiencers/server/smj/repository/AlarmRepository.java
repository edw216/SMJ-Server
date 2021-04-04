package com.experiencers.server.smj.repository;

import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
    List<Alarm> findAllByMemberEqualsAndStartDate(Member member, String startDate);
    List<Alarm> findAllByStartDate(String startDate);
}

