package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount,Long> {

}
