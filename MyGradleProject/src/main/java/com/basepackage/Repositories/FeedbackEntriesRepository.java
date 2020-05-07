package com.basepackage.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basepackage.Entities.FeedbackEntries;

public interface FeedbackEntriesRepository extends JpaRepository<FeedbackEntries, Long> {

}
