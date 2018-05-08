package com.chenbei.persistence.service.impl;

import com.chenbei.support.consts.SessionConstants;
import com.chenbei.persistence.entity.User;
import com.chenbei.persistence.entity.dto.form.UserLoginForm;
import com.chenbei.persistence.entity.dto.form.UserRegisterForm;
import com.chenbei.persistence.mapper.UserMapper;
import com.chenbei.persistence.service.api.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息操作业务实现类
 *
 * @author James
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mMapper;

    @Override
    public User loginAuthentication(UserLoginForm loginForm) {
        User param = new User();
        param.setUsername(loginForm.getUsername());
        param.setPassword(DigestUtils.md5Hex(loginForm.getPassword()));
        User user = mMapper.seleceUser(param);
        if (Objects.isNull(user)){
            return null;
        }else {
            return user;
        }
    }

    @Override
    public boolean registerUsernameCheckExist(UserRegisterForm registerForm) {
        return mMapper.select(new User().setUsername(registerForm.getUsername())).size() > 0;
    }

    @Override
    public void insertUser(User user) {
        String pwdStr = user.getPassword();
        user.setPassword(DigestUtils.md5Hex(pwdStr));
        mMapper.insertSelective(user);
    }

    @Override
    public void joinSession(HttpServletRequest request, User user) {
        HttpSession requestSession = request.getSession(true);
        requestSession.setAttribute(SessionConstants.SESSION_CURRENT_USER, user);
    }

    @Override
    public void destroySession(HttpServletRequest request) {
        HttpSession requestSession = request.getSession(true);
        requestSession.removeAttribute(SessionConstants.SESSION_CURRENT_USER);
    }
}
