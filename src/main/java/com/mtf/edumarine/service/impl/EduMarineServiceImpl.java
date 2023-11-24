package com.mtf.edumarine.service.impl;

import com.mtf.edumarine.dto.StatisticsDTO;
import com.mtf.edumarine.mapper.EduMarineMapper;
import com.mtf.edumarine.service.EduMarineService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EduMarineServiceImpl implements EduMarineService {

    private static final String STR_RESULT_H = "%s - %s";

    //private final SqlSession sqlSession;

    @Setter(onMethod_ = {@Autowired})
    private EduMarineMapper eduMarineMapper;

    /*public EduMarineServiceImpl(SqlSession ss) {
        this.sqlSession = ss;
    }*/

    @Override
    public void logoutCheck(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public void processStatisticsAccessor() {
        System.out.println("EduMarineServiceImpl > processStatisticsAccessor : ======");

        try {
            String joinYear = String.valueOf(LocalDateTime.now().getYear());
            String inDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")); // yyyy-MM-dd HH:mm:ss
            StatisticsDTO reqDto = new StatisticsDTO();
            reqDto.setGbn("Accessor");
            reqDto.setJoinYear(joinYear);
            reqDto.setInDttm(inDttm);
            String seq = eduMarineMapper.checkStatisticsAccessor(reqDto);
            if(seq != null){ /* update */
                reqDto.setSeq(seq);
                eduMarineMapper.updateStatisticsAccessor(reqDto);
            }else{ /* insert */
                reqDto.setInCount("1");
                eduMarineMapper.insertStatisticsAccessor(reqDto);
            }

        }catch (Exception e){
            String eMessage = "[main] processStatisticsAccessor Error : ";
            System.out.println(e.getMessage() == null ? "" : e.getMessage());
        }

    }

}
