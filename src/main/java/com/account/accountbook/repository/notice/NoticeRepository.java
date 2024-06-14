package com.account.accountbook.repository.notice;

import com.account.accountbook.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
