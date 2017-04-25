package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.common.TypeUtils;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.repository.BaseTeachTaskRepository;
import com.alienlab.niit.qm.service.BaseTeachTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/4/24.
 */
@Service
public class BaseTeachTaskServiceImpl implements BaseTeachTaskService {
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;

}
