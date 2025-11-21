package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.ApplicationUnifiedDTO;
import com.mtf.edumarine.dto.SearchDTO;
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

    List<ApplicationUnifiedDTO> selectUnifiedApplicationList(SearchDTO searchDTO);

    ApplicationUnifiedDTO selectUnifiedApplicationSingle(String seq);

    int updateUnifiedApplication(ApplicationUnifiedDTO dto);

}