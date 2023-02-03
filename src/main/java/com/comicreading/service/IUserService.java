package com.comicreading.service;

import com.comicreading.domain.User;
import com.comicreading.security.UserAlreadyExistException;
import com.comicreading.security.UserDto;

public interface IUserService {
    
    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException ;
}
