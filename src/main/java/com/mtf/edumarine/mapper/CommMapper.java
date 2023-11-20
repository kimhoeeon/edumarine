package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.CommCodeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommMapper {
    List<CommCodeDTO> getCommCodeList(CommCodeDTO commCodeDTO);
}
