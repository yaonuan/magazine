package com.magazine.thread.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static void main(String[] args) {


        AtomicReference<Student> studentAtomicReference = new AtomicReference<>();

        Student student = new Student(1L, "xiaomo");
        Student student1 = new Student(2L, "xiaoxuan");
        studentAtomicReference.set(student);
        studentAtomicReference.compareAndSet(student, student1);
        Student student2 = studentAtomicReference.get();
        System.out.println(student.getName());
        System.out.println(student2.getName());

    }

    @Data
    static class Student {
        private long id;
        private String name;

        public Student(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
