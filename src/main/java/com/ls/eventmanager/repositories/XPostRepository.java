package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XPostRepository extends JpaRepository<XPost, UUID> {

}
