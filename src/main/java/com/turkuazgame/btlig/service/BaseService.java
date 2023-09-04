package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.BaseInfo;
import com.turkuazgame.btlig.entity.IEntity;
import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

public class BaseService implements IService {

    private JpaRepository<IEntity, Long> repository;

    private Class<? extends IEntity> classOfEntity;
    private Class<? extends IResponse> classOfResponse;

    public BaseService(JpaRepository repository,
                       Class<? extends IEntity> classOfEntity,
                       Class<? extends IResponse> classOfResponse) {
        try {
            this.repository = repository;
            this.classOfEntity = classOfEntity;
            this.classOfResponse = classOfResponse;
        }
        catch(Exception e) {
            this.repository = null;
        }
    }

    public List<IResponse> getAllEntities() {
        try {
            List<IResponse> responseList = new ArrayList<>();
            List<IEntity> entities = repository.findAll();
            for (IEntity entity : entities) {
                IResponse response = classOfResponse.getDeclaredConstructor(classOfEntity).newInstance(entity);
                responseList.add(response);
            }
            return responseList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IResponse getEntity(Long id) {
        try {
            IEntity entity = repository.findById(id).orElse(null);
            return classOfResponse.getDeclaredConstructor(classOfEntity).newInstance(entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IResponse createEntity(IRequest request) {
        try {
            IEntity entity = classOfEntity.getDeclaredConstructor().newInstance();
            entity.setBaseInfo(new BaseInfo());
            entity.setFromRequest(request);
            entity.getBaseInfo().setCreatedBy(entity.getBaseInfo().getUpdatedBy());

            IEntity newEntity = repository.save(entity);
            return classOfResponse.getDeclaredConstructor(classOfEntity).newInstance(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IResponse updateEntity(Long id, IRequest request) {
        try {
            request.setId(id);
            Optional<IEntity> foundEntity = repository.findById(id);
            if (foundEntity.isPresent()) {
                IEntity entity = foundEntity.get();
                entity.setFromRequest(request);
                IEntity savedEntity = repository.save(entity);
                return classOfResponse.getDeclaredConstructor(classOfEntity).newInstance(savedEntity);
            } else
                return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IResponse mergeEntity(Long id, Map<Object, Object> fields) {
        try {
            Optional<IEntity> foundEntity = repository.findById(id);
            if (foundEntity.isPresent()) {
                if (!fields.containsKey("updateDate")) {
                    Timestamp updateDate = new Timestamp((new Date()).getTime());
                    fields.put("updateDate", updateDate);
                }
                for (Map.Entry<Object, Object> entry : fields.entrySet()) {
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    try {
                        Field fieldOfEntity = ReflectionUtils.findField(classOfEntity, key);
                        if (fieldOfEntity != null) {
                            fieldOfEntity.setAccessible(true);
                            ReflectionUtils.setField(fieldOfEntity, foundEntity.get(), value);
                        } else {
                            Field fieldOfBase = ReflectionUtils.findField(BaseInfo.class, key);
                            if (fieldOfBase != null) {
                                fieldOfBase.setAccessible(true);
                                ReflectionUtils.setField(fieldOfBase, foundEntity.get().getBaseInfo(), value);
                            } else {
                                System.out.println("Undefined Class Field : " + key);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Undefined Class Field : " + key);
                    }
                }
                IEntity savedEntity = repository.save(foundEntity.get());
                return classOfResponse.getDeclaredConstructor(classOfEntity).newInstance(savedEntity);
            } else
                return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteEntity(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
