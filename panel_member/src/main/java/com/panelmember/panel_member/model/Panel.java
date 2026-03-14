package com.panelmember.panel_member.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Panel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
private String email;
@Enumerated(EnumType.STRING)
private PanelType panelType;
private String location;
}
