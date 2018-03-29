package ru.geekbrains.dropbox.backend.service;

import java.util.ArrayList;
import ru.geekbrains.dropbox.backend.model.*;

import org.springframework.stereotype.Service;

@Service
public class Authentication implements AuthenticationService {
    @Override
    public boolean login(String loginName, String pass) {
    	UserPresenter userPresenter = new UserPresenter();
    	ArrayList<User> users = userPresenter.getUsers();
    	for (User user : users)
    	{
	    	if (user.getName().equals(loginName) && user.getPassword().equals(pass))
	    	{	    		
	    	  return true;
	    	}
    	}    	
        return false;
    }

    @Override
    public void logout() {

    }
}
