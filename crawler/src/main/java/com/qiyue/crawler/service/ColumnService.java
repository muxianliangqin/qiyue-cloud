package com.qiyue.crawler.service;

import com.qiyue.base.model.response.Response;
import com.qiyue.crawler.entity.ColumnEntity;
import com.qiyue.crawler.entity.WebEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColumnService {

    Response<List<ColumnEntity>> findByWebId(Long webId);

    Response<String> delete(Long columnId);

    Response<String> add(ColumnEntity columnEntity);

    Response<String> modify(ColumnEntity columnEntity);

}
