package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.StatisticsDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface EduMarineMapper {

    String checkStatisticsAccessor(StatisticsDTO statisticsDTO);

    Integer updateStatisticsAccessor(StatisticsDTO reqDto);

    Integer insertStatisticsAccessor(StatisticsDTO reqDto);

}
