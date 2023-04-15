package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    Map<String,Student> studentMap=new HashMap<>();
    Map<String,Teacher>teacherMap=new HashMap<>();
    Map<Teacher,List<Student>>teacherListMap=new HashMap<>();
    Set<Student>studentset=new HashSet<>();
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
        studentset.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        Teacher teacher1=teacherMap.get(teacher);
        Student student1=studentMap.get(student);

        if(teacherListMap.containsKey(teacher1)){
            List<Student>students=teacherListMap.get(teacher1);
            students.add(student1);
            teacherListMap.put(teacher1,students);
        }
        else{
            teacherListMap.put(teacher1,new ArrayList<>());
            List<Student>students=teacherListMap.get(teacher1);
            students.add(student1);
            teacherListMap.put(teacher1,students);
        }
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher) {
        Teacher teacher1=teacherMap.get(teacher);

        List<Student>students=teacherListMap.get(teacher1);

        List<String>required=new ArrayList<>();

        for(Student student:students){
            required.add(student.getName());
        }
        return required;
    }

    public List<String> getAllStudents() {
        List<String>required=new ArrayList<>();
        for(Student student:studentset){
            required.add(student.getName());
        }
        return required;
    }

    public void deleteTeacherByName(String teacher) {
        Teacher teacher1=teacherMap.get(teacher);
        List<Student>studentList=teacherListMap.get(teacher1);
        teacherListMap.remove(teacher1);
        teacherMap.remove(teacher);

        for(Student student:studentList){
            studentMap.remove(student.getName());
            studentset.remove(student);
        }

    }

    public void deleteAllTeachers() {
        studentMap.clear();
        teacherMap.clear();
        teacherListMap.clear();
        studentset.clear();
    }
}
