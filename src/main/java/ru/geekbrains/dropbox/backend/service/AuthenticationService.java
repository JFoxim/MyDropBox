package ru.geekbrains.dropbox.backend.service;

public interface AuthenticationService {
    boolean login(String loginName, String pass);
    void logout();
}
