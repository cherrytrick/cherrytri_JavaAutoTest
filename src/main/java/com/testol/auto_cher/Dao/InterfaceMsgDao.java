package com.testol.auto_cher.Dao;

import com.testol.auto_cher.Enity.InterfaceMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceMsgDao extends JpaRepository<InterfaceMsg, Long> {
}
