package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmStuSurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface QmStuSurveyRepository extends JpaRepository<QmStuSurveyEntity,Long> {
    QmStuSurveyEntity findByStuNoAndTermNoAndSurveyName(String stuNo,String termNo,String surveyName);
}
