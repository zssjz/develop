package com.jason.components.service.impl;

import com.jason.components.model.AccountDO;
import com.jason.components.model.UserDO;
import com.jason.components.model.dto.UserDTO;
import com.jason.components.repository.UserRepository;
import com.jason.components.service.UserService;
import com.jason.dto.MessageDTO;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by BNC on 2019/5/6.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageDTO saveUser(UserDTO userDTO) {
        try {
            AccountDO accountDO = new AccountDO();
            accountDO.setUsername(userDTO.getUsername());
            accountDO.setPassword(userDTO.getPassword());
            UserDO userDO = new UserDO();
            userDO.setNickname(userDTO.getNickname());
            accountDO.setUserDO(userDO);
            userDO.setAccountDO(accountDO);
            UserDO user = userRepository.save(userDO);
            return new MessageDTO(1, HttpStatus.OK, "OK", user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageDTO(0, HttpStatus.INTERNAL_SERVER_ERROR, "ERROR");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MessageDTO findUserInfo(String userId) {
        try {
            if (userRepository.existsById(userId)) {
                Optional<UserDO> optional = userRepository.findById(userId);
                return new MessageDTO(1, HttpStatus.OK, "OK", optional);
            } else {
                return new MessageDTO(0, HttpStatus.NOT_FOUND, "NOT FOUND");
            }
        } catch (Exception e) {
            return new MessageDTO(0, HttpStatus.INTERNAL_SERVER_ERROR, "ERROR");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MessageDTO findUserDetail(String userId) {
        try {
            if (userRepository.existsById(userId)) {
                UserDO userDO = userRepository.getByUserId(userId);
                return new MessageDTO(1, HttpStatus.OK, "OK", userDO);
            } else {
                return new MessageDTO(0, HttpStatus.NOT_FOUND, "NOT FOUND");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageDTO(0, HttpStatus.INTERNAL_SERVER_ERROR, "ERROR");
        }
    }

    @Transactional
    @Override
    public MessageDTO deleteUser(String userId) {
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return new MessageDTO(1, HttpStatus.OK, "OK");
            } else {
                return new MessageDTO(0, HttpStatus.NOT_FOUND, "NOT FOUND");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageDTO(0, HttpStatus.INTERNAL_SERVER_ERROR, "ERROR");
        }
    }
}
