package com.keep.mapper;

import com.keep.domain.ClubUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p> 用户 </p>
 *
 * @author zhangnan
 * @date 2019-12-08 15:36
 */
public interface CuldUserMapper {
    /**
     * 保存一个用户
     * @param user 用户信息
     */
    void saveUser(ClubUser user);

    /**
     * 查询用户userId是否已经存在
     * @param dingId ID
     * @return 数量
     */
    Long getUserCountByDingId(@Param("dingId") String dingId);
}
