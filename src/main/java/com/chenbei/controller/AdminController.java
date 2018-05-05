package com.chenbei.controller;

import com.chenbei.support.consts.ViewConsts;
import com.chenbei.controller.base.BaseController;
import com.chenbei.persistence.entity.Article;
import com.chenbei.persistence.entity.Resume;
import com.chenbei.persistence.entity.dto.form.AdminUserPwdModifyForm;
import com.chenbei.persistence.entity.dto.form.BlogAddForm;
import com.chenbei.persistence.entity.dto.form.BlogModifyForm;
import com.chenbei.persistence.entity.dto.form.UserLoginForm;
import com.chenbei.persistence.entity.dto.request.TableKeyModel;
import com.chenbei.persistence.entity.vo.BlogModifyModel;
import com.chenbei.persistence.entity.vo.ResumeModifyModel;
import com.chenbei.persistence.service.api.IAdminBlogService;
import com.chenbei.persistence.service.api.IAdminUserService;
import com.chenbei.persistence.service.api.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 后台控制器
 *
 * @author James
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    IAdminBlogService mBlogService;
    @Autowired
    IAdminUserService mAdminUserService;
    @Autowired
    IResumeService mResumeService;

    /**
     * 后台首页
     */
    @GetMapping("")
    public String pAdminIndex(HttpServletRequest request, Model model) {
        return "admin/blog_manage";
    }

    /**
     * 后台首页
     */
    @GetMapping("/index")
    public String pAdminIndex2(HttpServletRequest request, Model model) {
        return "admin/blog_manage";
    }

    /**
     * 后台用户登录页面
     */
    @GetMapping("/login")
    public String pAdminLogin(HttpServletRequest request, Model model) {
        return "admin/userlogin";
    }

    /**
     * 写博客页面
     */
    @GetMapping("/blogadd")
    public String pAdminBlogAdd(HttpServletRequest request, Model model) {
        return "admin/blogadd";
    }

    /**
     * 编辑博客页面
     */
    @GetMapping("/blogmodify/{id}")
    public String pAdminBlogModify(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
        Article article = mBlogService.blogSelectByPrimaryKey(id);
        BlogModifyModel modifyModel = new BlogModifyModel(article);
        addModelAtt(model, ViewConsts.VIEW_ARTICLE, modifyModel);
        return "admin/blog_modify";
    }

    /**
     * 博客管理页面
     */
    @GetMapping("/blogmanage")
    public String pAdminBlogManage(HttpServletRequest request, Model model) {
        return "admin/blog_manage";
    }

    /**
     * 后台用户登录验证
     */
    @PostMapping("/login.f")
    public String fAdminLogin(UserLoginForm userLoginForm) {
        return "redirect:/admin/blog_manage";
    }

    /**
     * 发布文章
     */
    @PostMapping("/blogadd.f")
    public String fAdminBlogAdd(BlogAddForm blogAddForm) {
        // TODO: 17-12-4 返回 json ，成功则重定向到博客列表
        mBlogService.blogAdd(blogAddForm);
        return "redirect:/admin/index";
    }

    /**
     * 修改文章
     */
    @PostMapping("blog_modify.f")
    public String fAdminBlogModify(BlogModifyForm modifyForm) {
        // TODO: 17-12-4 返回 json ，成功则重定向到博客列表
        mBlogService.blogModify(modifyForm);
        return "redirect:/admin/index";
    }


    /**
     * 获取博客详情列表
     */
    @GetMapping("/blog_list.j")
    @ResponseBody
    public Object jAdminBlogList() {
        return mBlogService.getArticleList();
    }

    /**
     * 批量删除博客
     */
    @DeleteMapping("/blog_delete.j")
    @ResponseBody
    public Object jAdminBlogDelete(@RequestBody TableKeyModel model) {
        mBlogService.blogDelete(model);
        return responseSimpleOK();
    }

    /**
     * 后台用户管理页面
     */
    @GetMapping("/admin_user_manage")
    public String pAdminUserManage() {
        return "admin/admin_user_manage";
    }

    /**
     * 后台用户管理 json
     */
    @GetMapping("/admin_user.j")
    @ResponseBody
    public Object jAdminUserList() {
        return mAdminUserService.getAdminUserJson();
    }

    /**
     * 后台用户批量删除
     */
    @DeleteMapping("/admin_use_delete.j")
    @ResponseBody
    public Object jAdminUserDelete(@RequestBody TableKeyModel model) {
        mAdminUserService.deleteAdminUser(model);
        return responseSimpleOK();
    }

    /**
     * 后台用户密码修改页面
     */
    @GetMapping("/admin_user_pwd_modify")
    public String pAdminUserPwdModify() {
        return "admin/admin_user_pwd_modify";
    }

    /**
     * 后台用户密码修改
     */
    @PostMapping("/admin_user_pwd_modify.f")
    @ResponseBody
    public Object fAdminUserPwdModify(@Valid AdminUserPwdModifyForm form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return responseSimpleError();
        }
        IAdminUserService.ModifyPwdResult result1;
        try {
            result1 = mAdminUserService.modifyUserPwd(form, request);
        } catch (Exception e) {
            e.printStackTrace();
            return responseSimpleError();
        }
        if (result1 == IAdminUserService.ModifyPwdResult.SUCCESS) {
            return responseSimpleOK();
        } else {
            return responseSimpleError();
        }
    }

    @GetMapping("/resume_modify")
    public String pUpdateResume(Model model) throws Exception {
        Resume article = mResumeService.getResume();
        ResumeModifyModel modifyModel = new ResumeModifyModel(article);
        addModelAtt(model, ViewConsts.VIEW_ARTICLE, modifyModel);
        return "admin/resume_modify";
    }
}
