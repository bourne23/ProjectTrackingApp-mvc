package com.foxborn.dto;

import com.foxborn.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;



}
