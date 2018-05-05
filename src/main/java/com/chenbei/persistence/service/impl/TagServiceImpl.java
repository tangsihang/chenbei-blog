package com.chenbei.persistence.service.impl;

import com.chenbei.persistence.entity.Tag;
import com.chenbei.persistence.entity.vo.TagView;
import com.chenbei.persistence.mapper.TagMapper;
import com.chenbei.persistence.service.api.ITagService;
import com.chenbei.persistence.service.base.BaseViewTransableService;
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
