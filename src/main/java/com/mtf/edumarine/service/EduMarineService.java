package com.mtf.edumarine.service;

import javax.servlet.http.HttpSession;

public interface EduMarineService {

    void logoutCheck(HttpSession session);

    void processStatisticsAccessor();

}
