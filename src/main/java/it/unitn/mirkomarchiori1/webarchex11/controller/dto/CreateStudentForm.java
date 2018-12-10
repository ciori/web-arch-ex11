package it.unitn.mirkomarchiori1.webarchex11.controller.dto;

public class CreateStudentForm {

    private String studentName;
    private String studentSurname;
    private Long studentMatriculationNumber;

    public CreateStudentForm() {

    }

    public CreateStudentForm(String studentName, String studentSurname, Long studentMatriculationNumber) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentMatriculationNumber = studentMatriculationNumber;
    }

    public String getstudentName() {
        return studentName;
    }

    public void setstudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getstudentSurname() {
        return studentSurname;
    }

    public void setstudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public Long getstudentMatriculationNumber() {
        return studentMatriculationNumber;
    }

    public void setstudentMatriculationNumber(Long studentMatriculationNumber) {
        this.studentMatriculationNumber = studentMatriculationNumber;
    }

}
