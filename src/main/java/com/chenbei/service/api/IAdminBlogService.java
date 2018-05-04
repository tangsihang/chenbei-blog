package com.chenbei.service.api;

import com.chenbei.entity.Article;
import com.chenbei.entity.dto.form.BlogAddForm;
import com.chenbei.entity.dto.form.BlogModifyForm;
import com.chenbei.entity.dto.request.TableKeyModel;
import com.chenbei.entity.vo.ArticleDataGridView;

import java.util.List;

/**
 * 后台博客发布接口
 *
 * @author James
 */
public interface IAdminBlogService {

  /**
   * 添加 文章
   *
   * @param form 表单
   */
  void blogAdd(BlogAddForm form);

  /**
   * 根据 id 获取文章
   *
   * @param id id
   *
   * @return 文章实体
   */
  Article blogSelectByPrimaryKey(Integer id);

  /**
   * 获取所有文章列表
   *
   * @return 文章列表视图实体集合
   */
  List<ArticleDataGridView> getArticleList();

  /**
   * 根据 id 删除文章
   *
   * @param model 数据模型
   */
  void blogDelete(TableKeyModel model);

  /**
   * 修改已发布文章
   *
   * @param form 表单
   */
  void blogModify(BlogModifyForm form);
}
