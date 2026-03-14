package com.panelmember.panel_member.repository;

import com.panelmember.panel_member.model.Panel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanelServiceRepository extends JpaRepository<Panel,Long> {

    List<Panel> findByNameContainingIgnoreCaseOrId(String name, Long id);


}
