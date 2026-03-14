package com.panelmember.panel_member.controller;

import com.panelmember.panel_member.model.Panel;
import com.panelmember.panel_member.payload.PanelRequest;
import com.panelmember.panel_member.payload.PanelResponse;
import com.panelmember.panel_member.service.PanelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private PanelService panelService;

    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanelResponse> getPanelById(@PathVariable Long id){

        Optional<PanelResponse> panelResponse = Optional.ofNullable(panelService.getPanelById(id));
        return panelResponse.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("panels")
    public ResponseEntity<Panel> createPanel(@Valid @RequestBody PanelRequest panelRequest){
        return new ResponseEntity<Panel>(panelService.createPanel(panelRequest), HttpStatus.CREATED);
    }

    @GetMapping("/panels")
    public ResponseEntity<List<PanelResponse>> getAllPanel(){
        return ResponseEntity.ok(panelService.getAllPanel());
    }


    @GetMapping("/search")
    public ResponseEntity<List<Panel>> searchPanel(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id) {

        List<Panel> result = panelService.searchPanel(name, id);
        return ResponseEntity.ok(result);
    }



}
