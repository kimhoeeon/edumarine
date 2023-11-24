package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Sipa Mng mapper.
 */
@Repository
public interface EduMarineMngMapper {

    AdminDTO login(AdminDTO customerDTO);

    List<NoticeDTO> selectNoticeList(SearchDTO searchDTO);

    NoticeDTO selectNoticeSingle(NoticeDTO noticeDTO);

    Integer deleteNotice(NoticeDTO noticeDTO);

    Integer updateNotice(NoticeDTO noticeDTO);

    String getNoticeSeq();

    Integer insertNotice(NoticeDTO noticeDTO);

    List<PressDTO> selectPressList(SearchDTO searchDTO);

    PressDTO selectPressSingle(PressDTO pressDTO);

    Integer deletePress(PressDTO pressDTO);

    Integer updatePress(PressDTO pressDTO);

    String getPressSeq();

    Integer insertPress(PressDTO pressDTO);

    List<FileDTO> selectFileUserIdList(FileDTO fileDTO);

    Integer updateFileUseN(FileDTO fileDTO);

    Integer updateFileUserId(FileDTO fileDTO);

    String getFileId();

    Integer insertFileInfo(FileDTO fileDTO);

}
