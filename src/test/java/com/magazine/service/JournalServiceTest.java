package com.magazine.service;

import com.magazine.MagazineApplication;
import com.magazine.domain.JournalEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class JournalServiceTest {

    @Autowired
    JournalService journalService;

    @Test
    public void queryByTitle() {
        String title = "1";
        JournalEntity journalEntity = journalService.queryByTitle(title);
        System.out.println(journalEntity);
    }
}