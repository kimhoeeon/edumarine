package com.mtf.edumarine.service.impl;

import com.mtf.edumarine.mapper.EduMarineMapper;
import com.mtf.edumarine.service.EduMarineService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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


}
