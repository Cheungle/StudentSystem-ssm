package org.entity;

import lombok.Data;

@Data
public class courseSelectionPermission {
    private String academicYear;
    private String semester;
    private boolean permission;
}
