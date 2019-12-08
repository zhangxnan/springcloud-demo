package com.keep.service;

import com.keep.domain.ClubUser;

/**
 * <p> 创建用户 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:35
 */
public interface CludUserService {

    /**
     *  创建一个用户
     * @param user 用户
     * @return
     */
    Long createUser(ClubUser user);

}
