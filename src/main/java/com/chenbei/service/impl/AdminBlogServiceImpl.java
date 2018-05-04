package com.chenbei.service.impl;

import com.chenbei.entity.Article;
import com.chenbei.entity.Tag;
import com.chenbei.entity.TagArticle;
import com.chenbei.entity.dto.form.BlogAddForm;
import com.chenbei.entity.dto.form.BlogModifyForm;
import com.chenbei.entity.dto.request.TableKeyModel;
import com.chenbei.entity.vo.ArticleDataGridView;
import com.chenbei.mapper.ArticleMapper;
import com.chenbei.mapper.TagArticleMapper;
import com.chenbei.mapper.TagMapper;
import com.chenbei.service.api.IAdminBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台博客服务实现类
 *
 * @author James
 */
@Service
public class AdminBlogServiceImpl implements IAdminBlogService {

  @Autowired
  private ArticleMapper mArticleMapper;
  @Autowired
  private TagMapper mTagMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void blogAdd(BlogAddForm form) {
    Article article = new Article();
    article.setTitle(form.getTitle());
    article.setMdMaterial(form.getMdMaterial());
    article.setHtmlMaterial(form.getHtmlMaterial());
    article.setIntroduction(form.getDescription());
    // 处理 article
    mArticleMapper.insertSelective(article);
    Integer articleId = article.getId();
    // 处理 tags
    String[] tags = form.getRawTags().split(",");
    for (String item : tags) {
      Tag expected = mTagMapper.selectTagByName(item);
      if (null != expected) {
        Integer id = expected.getId();
        TagArticle tagArticle = new TagArticle();
        tagArticle.setTagId(id);
        tagArticle.setArticleId(articleId);
        mTagArticleMapper.insertSelective(tagArticle);
      } else {
        Tag tag = new Tag();
        tag.setName(item);
        mTagMapper.insertSelective(tag);
        Integer tagId = tag.getId();
        TagArticle tagArticle = new TagArticle();
        tagArticle.setTagId(tagId);
        tagArticle.setArticleId(articleId);
        mTagArticleMapper.insertSelective(tagArticle);
      }
    }
  }

  @Autowired
  private TagArticleMapper mTagArticleMapper;

  @Override
  public Article blogSelectByPrimaryKey(Integer id) {
    return mArticleMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<ArticleDataGridView> getArticleList() {
    List<Article> articleList = mArticleMapper.selectAll();
    List<ArticleDataGridView> viewList = new ArrayList<>();
    for (Article article : articleList) {
      ArticleDataGridView view = new ArticleDataGridView(article);
      viewList.add(view);
    }
    return viewList;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void blogDelete(TableKeyModel model) {
    List<Integer> idList = model.getIds();
    for (Integer id : idList) {
      mArticleMapper.deleteByPrimaryKey(id);
    }
  }

  @Override
  public void blogModify(BlogModifyForm form) {
    Article article = new Article();
    article.setId(form.getId());
    article.setIntroduction(form.getDescription());
    article.setHtmlMaterial(form.getHtmlMaterial());
    article.setMdMaterial(form.getMdMaterial());
    article.setTitle(form.getTitle());
    // 更新数据库中的信息
    mArticleMapper.updateByPrimaryKeySelective(article);
  }
}