package com.magazine.deconstruct;

public class VerbObject<A,B> {
    public A verb;
    public B object;

    public A getVerb() {
        return verb;
    }

    public void setVerb(A verb) {
        this.verb = verb;
    }

    public B getObject() {
        return object;
    }

    public void setObject(B object) {
        this.object = object;
    }

    public void getValue(){

    }


    public void set(A verb, B object){
        this.verb = verb;
        this.object = object;
    }


    @Override
    public String toString() {
        return "(" + verb + "," + object + ")";
    }
}
