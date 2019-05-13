package com.magazine.service;

public interface RangingService {

    void rankAdd(String uid, Integer score);

    void increSocre(String uid, Integer score);

    Long rankNum(String uid);

    Long score(String uid);
}
