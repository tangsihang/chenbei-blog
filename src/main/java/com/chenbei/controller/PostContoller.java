package com.chenbei.controller;

import com.chenbei.support.consts.ViewConsts;
import com.chenbei.controller.base.BaseController;
import com.chenbei.persistence.entity.Article;
import com.chenbei.persistence.entity.dto.form.ArticleSearchForm;
import com.chenbei.persistence.entity.vo.PostView;
import com.chenbei.persistence.entity.vo.TagView;
import com.chenbei.persistence.service.api.IAdminBlogService;
import com.chenbei.persistence.service.api.IPostsService;
import com.chenbei.persistence.service.api.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 博客控制器
 *
 * @author James
 */
@Controller
public class PostContoller extends BaseController {

    @Autowired
    private IPostsService mPostService;

    @Autowired
    private ITagService mTagService;

    @Autowired
    private IAdminBlogService mBlogService;

    /**
     * 博客列表页
     * 视图模型：
     * 1.博客列表（文章标题/内容简介/发布时间）
     * 2.标签（tagId/文章总数）
     */
    @GetMapping("/post")
    public String pPostList(HttpServletRequest request, Model model, Integer tagId) throws Exception {
        List<PostView> postViewList = null;
        if (null != tagId) {
            postViewList = mPostService.getPostListByTagId(tagId);
        } else {
            postViewList = mPostService.getPostList();
        }
        List<TagView> tagViewList = mTagService.getAllTagView();
        addModelAtt(model, ViewConsts.VIEW_TAGLIST, tagViewList);
        addModelAtt(model, ViewConsts.VIEW_POSTLIST, postViewList);
        return "posts";
    }

    /**
     * 查看文章内容页
     *
     * @param id 文章 id
     */
    @GetMapping("/blog/{id}")
    public String pFrontBlogView(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
        Article article = mBlogService.blogSelectByPrimaryKey(id);
        PostView postView = new PostView(article);
        addModelAtt(model, ViewConsts.VIEW_ARTICLE, postView);
        addModelAtt(model, ViewConsts.VIEW_TITLE, article.getTitle());
        return "article";
    }

    /**
     * 文章搜索结果页
     * <p>
     * 根据指定条件查找文章
     * <p>
     * 视图模型：
     * 博客列表（文章标题/内容简介/发布时间）
     */
    @GetMapping("/postsearch")
    public String pPostSearch(HttpServletRequest request, Model model, ArticleSearchForm form) throws Exception {
        List<PostView> postViewList = mPostService.getPostListByArticleCondition(form);
        addModelAtt(model, ViewConsts.VIEW_POSTLIST, postViewList);
        List<TagView> tagViewList = mTagService.getAllTagView();
        addModelAtt(model, ViewConsts.VIEW_TAGLIST, tagViewList);
        return "posts";
    }
}
