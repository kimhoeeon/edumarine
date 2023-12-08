package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.PopupDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface EduMarineService {

    void logoutCheck(HttpSession session);

    void processStatisticsAccessor();

    List<PopupDTO> processSelectPopupList(PopupDTO popupDTO);
}
