package com.panelmember.panel_member.service;

import com.panelmember.panel_member.model.Panel;
import com.panelmember.panel_member.payload.PanelRequest;
import com.panelmember.panel_member.payload.PanelResponse;

import java.util.List;

public interface PanelService {
    Panel createPanel(PanelRequest panelRequest);
    List<PanelResponse> getAllPanel();
    PanelResponse getPanelById(Long id);
    List<Panel> searchPanel(String name, Long id);
}
