package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.AbInfoRequest;
import com.dryyzx.backend.entity.AbInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AbInfoMapper {
    List<AbInfo> list();

    void insert(AbInfo abInfo);

    void audit(String id);

    //不知道显示详细信息是否能使用 modelAndView 完成   答案是不能
    AbInfo getDetails(String id);

    List<AbInfo> selectWithWhere(AbInfoRequest abInfoRequest);
}
