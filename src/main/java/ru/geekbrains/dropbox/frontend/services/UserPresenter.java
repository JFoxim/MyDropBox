package ru.geekbrains.dropbox.frontend.services;

import java.util.ArrayList;

import ru.geekbrains.dropbox.backend.model.User;

public class UserPresenter {

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		for (int i=1; i<=20; i++)
		{
		   users.add(new User("user"+i, "user"+i+"@mail.ru", "12"+i));
		}		
		return users;
	}
}
