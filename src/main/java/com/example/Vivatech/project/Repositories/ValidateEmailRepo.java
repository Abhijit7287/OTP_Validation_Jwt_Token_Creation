package com.example.Vivatech.project.Repositories;

import com.example.Vivatech.project.Models.ValidatedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValidateEmailRepo extends JpaRepository<ValidatedEmail,Integer>{
    @Query(value = "select * from validated_email where email_id = :emailId", nativeQuery = true)
    ValidatedEmail findByEmail(@Param("emailId") String emailId);
}
