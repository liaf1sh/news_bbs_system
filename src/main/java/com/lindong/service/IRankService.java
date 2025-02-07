package com.lindong.service;

import com.lindong.domain.Plate;

import java.util.List;
import java.util.Map;

public interface IRankService extends IBaseService {
    List<Object> experienceByuserId(Map<String, Object> param);

    List<Object> replyByplateId(Map<String, Object> param);

    List<Object>browseByplateId(Map<String, Object> param);

    List<Object>shareByplateId(Map<String, Object> param);

    List<Object>collectByplateId(Map<String, Object> param);

    List<Object>heatByplateId(Map<String, Object> param);

    List<Object>themeByplateId(Map<String, Object> param);

    List<Object>postsByplateId(Map<String, Object> param);

    List<Object>postsByuserId(Map<String, Object> param);

    List<Object>logsByuserId(Map<String, Object> param);

    List<Plate>timeByuserId();

}
