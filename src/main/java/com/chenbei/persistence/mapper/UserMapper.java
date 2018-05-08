package com.chenbei.persistence.mapper;

import com.chenbei.persistence.entity.User;
import com.chenbei.persistence.entity.dto.form.UserLoginForm;
import com.chenbei.persistence.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * @author James
 */
public interface UserMapper extends IMyMapper<User> {

    @Select({"SELECT * FROM admin_user WHERE username = #{username} AND password = #{password}"})
    @ResultType(User.class)
    User seleceUser(User user);
}