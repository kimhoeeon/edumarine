package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.ApplicationUnifiedDTO;
import com.mtf.edumarine.dto.SearchDTO;
import com.mtf.edumarine.dto.SmsSendDTO;
import com.mtf.edumarine.dto.TrainDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int updateUnifiedApplication(ApplicationUnifiedDTO dto);

    ApplicationUnifiedDTO selectApplicationUnifiedSingle(String seq);

    List<ApplicationUnifiedDTO> selectUnifiedList(SearchDTO searchDTO);

    Integer updateUnifiedApplyStatus(ApplicationUnifiedDTO info);

    Integer selectUnifiedPreCheck(ApplicationUnifiedDTO dto);

    List<TrainDTO> selectActiveUnifiedTrainList();

    List<ApplicationUnifiedDTO> selectExcelUnifiedApplicationList(SearchDTO searchDTO);

    Integer updateTrainApplyCntMinus(String seq);

    Integer updateTrainApplyCntPlus(String seq);

    /** 2026-10-30 G2: 관리자 과정별 문자 발송 대상 (통합 양식 신청자, 과정명 기준) */
    List<SmsSendDTO> selectSmsSendUnifiedList(String gbn);
}