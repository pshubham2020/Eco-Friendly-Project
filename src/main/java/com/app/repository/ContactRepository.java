package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	/*
	 * @Query void saveContacts(Contact contact);
	 */
}
