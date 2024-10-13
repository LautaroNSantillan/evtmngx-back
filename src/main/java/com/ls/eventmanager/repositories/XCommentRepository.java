package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XCommentRepository extends JpaRepository<XComment, Long> {
}
