package com.app.service;

import javax.mail.MessagingException;

import com.app.entity.Contact;

public interface ContactService {
	
	void saveContacts(Contact contact) throws MessagingException;

}
