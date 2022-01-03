package com.app.ProjectManagement.dao;

import com.app.ProjectManagement.Entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {

}
