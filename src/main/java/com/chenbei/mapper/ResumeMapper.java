package com.chenbei.mapper;

import com.chenbei.entity.Resume;
import com.chenbei.framework.mapper.IMyMapper;

/**
 * 简历
 *
 * @author James
 */
public interface ResumeMapper extends IMyMapper<Resume> {

  String COLUMN_LIST = "resume.id,title,introduction,resume.gmt_create AS gmtCreate,resume.gmt_modified AS gmtModified";
}