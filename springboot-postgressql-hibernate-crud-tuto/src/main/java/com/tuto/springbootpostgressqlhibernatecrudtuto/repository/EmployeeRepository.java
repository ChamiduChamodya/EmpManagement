package com.tuto.springbootpostgressqlhibernatecrudtuto.repository;

import com.tuto.springbootpostgressqlhibernatecrudtuto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
