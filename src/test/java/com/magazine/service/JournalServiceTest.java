package com.magazine.service;

import com.magazine.MagazineApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class JournalServiceTest {

    @Autowired
    JournalService journalService;

    @Test
    public void queryByTitle() {
        String title = "1";
    }


    public Integer foo(Integer n) {
        if (n < 2) return n;
        return foo(n - 1) + foo(n - 2);
    }

    @Test
    public void test() {
        Integer lx = new Integer(Integer.MAX_VALUE);
        String s="xxx";
        System.out.println(lx);
//       int nb = lx;
        t1(lx,s);
        System.out.println(lx);
        System.out.println(s);
    }

    private void t1(Integer i,String s) {
        i++;
        ++i;
        s="ppp";
    }

    public class A
    {
        String str;
    }
    class B
    {
        void change(int x,A a)
        {
            x = 20;
            a.str = "h";
        }
        public  void main(String [] args)
        {
            A n = new A();
            n.str = "q";
            int x = 10;
            System.out.println("对象的引用改变前：" + n.str + " ,int型引用改变前" + x);
            change(x,n);
            System.out.println("对象的引用改变后：" + n.str + " ,int型引用改变后" + x);
        }
    }



}