package it.unitn.mirkomarchiori1.webarchex11.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentForm {

    private String studentName;
    private String studentSurname;
    private long studentMatriculationNumber;

}
