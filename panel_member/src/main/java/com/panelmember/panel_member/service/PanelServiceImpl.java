package com.panelmember.panel_member.service;

import com.panelmember.panel_member.model.Panel;
import com.panelmember.panel_member.model.PanelType;
import com.panelmember.panel_member.payload.PanelRequest;
import com.panelmember.panel_member.payload.PanelResponse;
import com.panelmember.panel_member.repository.PanelServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanelServiceImpl implements PanelService{
    @Autowired
    private PanelServiceRepository panelServiceRepository;

    @Override
    public Panel createPanel(PanelRequest panelRequest){
        Panel panel = new Panel();
        panel.setName(panelRequest.getName());
        panel.setPanelType(PanelType.valueOf(panelRequest.getPanelType()));
        panel.setLocation(panelRequest.getLocation());
        panel.setEmail(panelRequest.getEmail());
        return panelServiceRepository.save(panel);
    }
    @Override
    public List<PanelResponse> getAllPanel(){
        List<Panel> panels = panelServiceRepository.findAll();
        return panels.stream().map(panel -> {
            PanelResponse panelResponse = new PanelResponse();
            panelResponse.setName(panel.getName());
            panelResponse.setPanelType(String.valueOf(panel.getPanelType()));
            panelResponse.setEmail(panel.getEmail());
            panelResponse.setLocation(panel.getLocation());
            return panelResponse;
        }).collect(Collectors.toList());
    }
    @Override
    public PanelResponse getPanelById(Long id){
    Panel panel = panelServiceRepository.findById(id).orElseThrow();
    PanelResponse panelResponse = new PanelResponse();
    panelResponse.setName(panel.getName());
    panelResponse.setEmail(panel.getEmail());
    panelResponse.setPanelType(String.valueOf(panel.getPanelType()));
    return panelResponse;
    }

    @Override
    public List<Panel> searchPanel(String name, Long id) {
        return panelServiceRepository.findByNameContainingIgnoreCaseOrId(name, id);
    }

}
