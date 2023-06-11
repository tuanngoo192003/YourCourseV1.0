package com.project.CourseSystem.service;

import com.project.CourseSystem.dto.UserInfoDTO;
import com.project.CourseSystem.entity.UserInfo;

public interface UserService {
    public UserInfo findUser(int accountID);

    public void saveUser(UserInfo userInfo);

    public void updateUser(UserInfo userInfo);
}
