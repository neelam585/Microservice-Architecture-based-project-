package com.panelmember.panel_member.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanelRequest {
    private String name;
    private String email;
    private String panelType;
    private String location;
}
