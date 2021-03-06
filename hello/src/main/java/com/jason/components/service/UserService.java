package com.jason.components.service;

import com.jason.components.model.AccountDO;
import com.jason.components.model.UserDO;
import com.jason.components.model.dto.UserDTO;
import com.jason.dto.MessageDTO;

/**
 * Created by BNC on 2019/5/6.
 */
public interface UserService {

    /**
     * 补充/修改用户信息
     * @param userDTO
     * @return
     */
   MessageDTO saveUser(UserDTO userDTO);

    /**
     * 通过id获取用户基本信息
     * @param userId
     * @return
     */
   MessageDTO findUserInfo(String userId);

    /**
     * 通过ID获取用户所有信息
     * @param userId
     * @return
     */
   MessageDTO findUserDetail(String userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
   MessageDTO deleteUser(String userId);

}
