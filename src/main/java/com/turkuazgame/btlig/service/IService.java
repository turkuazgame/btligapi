package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.IEntity;
import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IService  {

    public List<IResponse> getAllEntities();
    public IResponse getEntity(Long id);
    public IResponse createEntity(IRequest request);
    public IResponse updateEntity(Long id, IRequest request);
    public IResponse mergeEntity(Long id, Map<Object, Object> fields);
    public void deleteEntity(Long id);
}