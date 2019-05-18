package com.magazine.deconstruct;

import java.util.HashMap;
import java.util.List;


public class MixData {
    public List<HashMap> child_dict;
    public List<String> words;
    public List<String> postags;

//    public MixData(List<HashMap> child_dict, List<String> words, List<String> postags) {
//        this.child_dict = child_dict;
//        this.words = words;
//        this.postags = postags;
//    }

    public List<HashMap> getChild_dict() {
        return this.child_dict;
    }

    public void setChild_dict(List<HashMap> child_dict) {
        this.child_dict = child_dict;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getPostags() {
        return postags;
    }

    public void setPostags(List<String> postags) {
        this.postags = postags;
    }

    public int size(){
        return this.words.size();
    }

    public String subString(int start,int end){
        return this.getWords().subList(start, end).stream().reduce((x,y)->x+y).get();
    }


    @Override
    public String toString() {
        return "MixData{" +
                "child_dict=" + child_dict +
                ", words=" + words +
                ", postags=" + postags +
                '}';
    }

}
