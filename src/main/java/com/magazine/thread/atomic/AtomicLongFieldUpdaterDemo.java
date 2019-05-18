package com.magazine.thread.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicLongFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicLongFieldUpdater<Student> longFieldUpdater = AtomicLongFieldUpdater.newUpdater(Student.class, "id");

        Student student = new Student(1L, "xiaomo");
        longFieldUpdater.compareAndSet(student,1L,100L);
        System.out.println(student.getId());
        AtomicReferenceFieldUpdater<Student, String> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        referenceFieldUpdater.compareAndSet(student,"xiaomo","xiaoxuan");
        System.out.println(student.getName());
    }

    @Data
    static class Student{
        volatile long id;
        volatile String name;
        public Student(Long id,String name){
            this.id = id;
            this.name = name;
        }

        //concurrentModificationException
    }



}
