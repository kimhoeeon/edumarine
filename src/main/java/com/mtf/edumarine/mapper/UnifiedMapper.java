package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.ApplicationUnifiedDTO;
import com.mtf.edumarine.dto.SearchDTO;
import com.mtf.edumarine.dto.TrainDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface EduMarine Mng mapper.
 */
@Repository
public interface UnifiedMapper {

    /**
     * 통합 신청 PK (AU0000001) 채번
     */
    String getUnifiedAppSeq();

    /**
     * 신규 통합 신청서 INSERT
     */
    int insertUnifiedApplication(ApplicationUnifiedDTO dto);

    /**
     * 신규 통합 신청서 상태 업데이트 (결제/취소 공용)
     */
    int updateUnifiedApplicationPayStatus(ApplicationUnifiedDTO dto);

    List<ApplicationUnifiedDTO> selectUnifiedApplicationList(SearchDTO searchDTO);

    int updateUnifiedApplication(ApplicationUnifiedDTO dto);

    ApplicationUnifiedDTO selectApplicationUnifiedSingle(String seq);

    List<ApplicationUnifiedDTO> selectUnifiedList(SearchDTO searchDTO);

    int selectUnifiedListCnt(Map<String, Object> paramMap);

    Integer updateUnifiedApplyStatus(ApplicationUnifiedDTO info);

    Integer selectUnifiedPreCheck(ApplicationUnifiedDTO dto);

    List<TrainDTO> selectActiveUnifiedTrainList();

    List<ApplicationUnifiedDTO> selectExcelUnifiedApplicationList(SearchDTO searchDTO);

    Integer updateTrainApplyCntMinus(String seq);

    Integer updateTrainApplyCntPlus(String seq);
}