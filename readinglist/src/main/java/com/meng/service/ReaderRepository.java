
package com.meng.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meng.entity.Reader;


/**
 * 持久化接口
 */

public interface ReaderRepository extends JpaRepository<Reader, String> {

}

