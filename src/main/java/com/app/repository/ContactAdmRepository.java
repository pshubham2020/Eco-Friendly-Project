package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Contact;

public interface ContactAdmRepository extends JpaRepository<Contact, Long> 
{
	
}
