package com.chenbei.persistence.mapper;

import com.chenbei.persistence.entity.Resume;
import com.chenbei.persistence.framework.mapper.IMyMapper;

/**
 * 简历
 *
 * @author James
 */
public interface ResumeMapper extends IMyMapper<Resume> {

  String COLUMN_LIST = "resume.id,title,introduction,resume.gmt_create AS gmtCreate,resume.gmt_modified AS gmtModified";
}