package com.basepackage.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.basepackage.Entities.FeedbackAttr;


public interface FeedbackAttrRepository extends JpaRepository<FeedbackAttr, Long> {

}
