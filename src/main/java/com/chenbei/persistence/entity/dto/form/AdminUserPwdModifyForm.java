package com.chenbei.persistence.entity.dto.form;

import com.chenbei.support.consts.DataConsts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 后台用户密码修改
 *
 * @author James
 * @date 18-1-25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserPwdModifyForm {

    @NotEmpty
    @Length(max = DataConsts.PASSWORD_MAX_LENGTH)
    private String oriPwd;
    @NotEmpty
    @Length(max = DataConsts.PASSWORD_MAX_LENGTH)
    private String newPwd;
    @NotEmpty
    @Length(max = DataConsts.PASSWORD_MAX_LENGTH)
    private String confirmPwd;
}
