package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.EvaRequest;
import com.dryyzx.backend.entity.Evaluate;
import com.dryyzx.backend.mapper.EvaMapper;
import com.dryyzx.backend.service.EvaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EvaServiceImpl implements EvaService {
    @Autowired
    EvaMapper evaMapper;

    @Override
    public PageInfo<Evaluate> selectWithIf(EvaRequest evaRequest) {
        PageHelper.startPage(evaRequest.getPageNum(), evaRequest.getPageSize());
        List<Evaluate> list = evaMapper.query(evaRequest);
        return new PageInfo<>(list);
    }

    @Override
    public Evaluate getDetail(String id) {
        return evaMapper.detail(id);
    }

    @Override
    public void cut(String id) {
        evaMapper.cut(id);
    }
/*
    Map<String, Object> dateEx(EvaRequest eva) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("evaId", eva.getEvaId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        if (eva.getServTime() != null && !eva.getServTime().equals("")) {
            map.put("servTime", dateFormat.parse(eva.getServTime()));
        } else {
            map.put("servTime", new Date(0));
        }
        map.put("userName", eva.getUserName());
        map.put("nurse", eva.getNurse());
        return map;
    }
 */
}
