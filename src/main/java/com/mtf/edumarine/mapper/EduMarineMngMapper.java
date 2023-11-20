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

    List<FileDTO> selectFileUserIdList(FileDTO fileDTO);

    Integer updateFileUseN(FileDTO fileDTO);

    Integer deleteNotice(NoticeDTO noticeDTO);

    Integer updateFileUserId(FileDTO fileDTO);

    Integer updateNotice(NoticeDTO noticeDTO);

    String getNoticeSeq();

    Integer insertNotice(NoticeDTO noticeDTO);

    String getFileId();

    Integer insertFileInfo(FileDTO fileDTO);

    List<SipaNewsDTO> selectSipaNewsList(SearchDTO searchDTO);

    SipaNewsDTO selectSipaNewsSingle(SipaNewsDTO sipaNewsDTO);

    Integer deleteSipaNews(SipaNewsDTO sipaNewsDTO);

    Integer updateSipaNews(SipaNewsDTO sipaNewsDTO);

    String getSipaNewsSeq();

    Integer insertSipaNews(SipaNewsDTO sipaNewsDTO);

    List<EventDTO> selectEventList(SearchDTO searchDTO);

    EventDTO selectEventSingle(EventDTO eventDTO);

    Integer deleteEvent(EventDTO eventDTO);

    Integer updateEvent(EventDTO eventDTO);

    String getEventSeq();

    Integer insertEvent(EventDTO eventDTO);

    List<FaqDTO> selectFaqList(SearchDTO searchDTO);

    FaqDTO selectFaqSingle(FaqDTO faqDTO);

    Integer deleteFaq(FaqDTO faqDTO);

    Integer updateFaq(FaqDTO faqDTO);

    String getFaqSeq();

    Integer insertFaq(FaqDTO faqDTO);

    List<AscdirectorsDTO> selectAscdirectorsList(SearchDTO searchDTO);

    AscdirectorsDTO selectAscdirectorsSingle(AscdirectorsDTO ascdirectorsDTO);

    Integer deleteAscdirectors(AscdirectorsDTO ascdirectorsDTO);

    Integer updateAscdirectors(AscdirectorsDTO ascdirectorsDTO);

    String getAscdirectorsSeq();

    Integer insertAscdirectors(AscdirectorsDTO ascdirectorsDTO);

    List<AdviserDTO> selectAdviserList(SearchDTO searchDTO);

    AdviserDTO selectAdviserSingle(AdviserDTO adviserDTO);

    Integer deleteAdviser(AdviserDTO adviserDTO);

    Integer updateAdviser(AdviserDTO adviserDTO);

    String getAdviserSeq();

    Integer insertAdviser(AdviserDTO adviserDTO);

    List<ConsultationDTO> selectConsultationList(SearchDTO searchDTO);

    ConsultationDTO selectConsultationSingle(ConsultationDTO consultationDTO);

    Integer deleteConsultation(ConsultationDTO consultationDTO);

    Integer updateConsultation(ConsultationDTO consultationDTO);

    String getConsultationSeq();

    Integer insertConsultation(ConsultationDTO consultationDTO);

    List<CompanyDTO> selectCompanyList(SearchDTO searchDTO);

    CompanyDTO selectCompanySingle(CompanyDTO companyDTO);

    Integer deleteCompany(CompanyDTO companyDTO);

    Integer updateCompany(CompanyDTO companyDTO);

    String getCompanySeq();

    Integer insertCompany(CompanyDTO companyDTO);

    List<PartnershipDTO> selectPartnershipList(SearchDTO searchDTO);

    PartnershipDTO selectPartnershipSingle(PartnershipDTO partnershipDTO);

    Integer deletePartnership(PartnershipDTO partnershipDTO);

    Integer updatePartnership(PartnershipDTO partnershipDTO);

    String getPartnershipSeq();

    Integer insertPartnership(PartnershipDTO partnershipDTO);
}
