package com.chenbei.service.impl;

import com.chenbei.entity.Tag;
import com.chenbei.entity.vo.TagView;
import com.chenbei.mapper.TagMapper;
import com.chenbei.service.api.ITagService;
import com.chenbei.service.base.BaseViewTransableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章标签相关业务实现类
 *
 * @author James
 */
@Service
public class TagServiceImpl extends BaseViewTransableService<Tag, TagView> implements ITagService {

  @Autowired
  TagMapper mTagMapper;

  @Override
  protected List<TagView> transEntityToView(List<Tag> entityList) {
    List<TagView> tagViews = new ArrayList<>();
    for (Tag tag : entityList) {
      TagView tagView = new TagView(tag);
      tagViews.add(tagView);
    }
    return tagViews;
  }

  @Override
  public List<TagView> getAllTagView() {
    return mTagMapper.selectAllTagView();
  }
}
