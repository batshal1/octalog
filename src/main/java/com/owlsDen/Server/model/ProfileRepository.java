package com.owlsden.Server.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Profile findByUsername(String username);
	Profile findByEmail(String email);
	Profile findByPhonenumber(String phonenumber);
}
