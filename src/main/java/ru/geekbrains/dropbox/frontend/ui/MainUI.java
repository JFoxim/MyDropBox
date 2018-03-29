package ru.geekbrains.dropbox.frontend.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import ru.geekbrains.dropbox.frontend.model.SavedFile;
import ru.geekbrains.dropbox.backend.service.*;


@SpringUI
public class MainUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Authentication authentication = new Authentication();
	
    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        Grid<SavedFile> gridFiles = new Grid<>();
        gridFiles.setSizeFull();

        Panel pnlAutheticate = new Panel("Введите логин и пароль");
        pnlAutheticate.setSizeUndefined();

        Panel pnlActions = new Panel();
        pnlActions.setSizeUndefined();
        TextField loginTextField = new TextField();        
        PasswordField passwordField = new PasswordField();

        Upload uploadFile = new Upload();
        uploadFile.setButtonCaption("Загрузить");
        Button btnDelete = new Button("Удалить");
        Button btnDownload = new Button("Скачать");
        Button btnLogin = new Button("Войти");
        Button btnLogOut = new Button("Выйти");
        
        uploadFile.setVisible(false);
        btnDelete.setVisible(false);
        btnDownload.setVisible(false);
        btnLogOut.setVisible(false);
        gridFiles.setVisible(false);        
       
        btnLogin.addClickListener(ClickEvent -> {
        	Boolean isLoginSuccessful = authentication.login(loginTextField.getValue().toString(),
    	    		passwordField.getValue().toString());
            if (isLoginSuccessful)
            {
            	loginTextField.setVisible(false);	 
            	passwordField.setVisible(false);
            	btnLogin.setVisible(false);
            	btnDelete.setVisible(true);
            	btnDownload.setVisible(true);
                uploadFile.setVisible(true);
                btnLogOut.setVisible(true);
                pnlAutheticate.setCaption("Вы вошли как "+loginTextField.getValue().toString());
                loginTextField.setValue("");
                passwordField.setValue("");	 
                gridFiles.setVisible(true);
            }
            else {
            	pnlAutheticate.setCaption("Не верный логин или пароль");
            }	
        });     
     

     btnLogOut.addClickListener(ClickEvent -> {
        	authentication.logout();
         	btnDelete.setVisible(false);
        	btnDownload.setVisible(false);
            uploadFile.setVisible(false);
            btnLogin.setVisible(true);
            btnLogOut.setVisible(false);
        	loginTextField.setVisible(true);	 
        	passwordField.setVisible(true);
            gridFiles.setVisible(false);
        	pnlAutheticate.setCaption("Введите логин и пароль");
        });
        
        
        HorizontalLayout layoutActions = new HorizontalLayout();
        layoutActions.setSizeUndefined();       

        
        pnlActions.setContent(layoutActions);
        layoutActions.addComponents(loginTextField, passwordField, btnLogin, 
        		btnLogOut, uploadFile, btnDelete, btnDownload);
        layoutSource.addComponents(gridFiles, pnlAutheticate, pnlActions);
        this.setContent(layoutSource);
    }       
}

