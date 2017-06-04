package com.mingyuans.reflect;


/**
 * Created by yanxq on 17/6/4.
 */
public class Person {
    private static String TAG = "Human";

    private final String mFirstName;
    private final String mSecondName;

    private int mPersonAge = 0;
    private String mNationality = "China";

    public Person(String firstName,String secondName) {
        mFirstName = firstName;
        mSecondName = secondName;
    }

    public void setAge(int age) {
        if (age < 0 || age >= 200) {
            throw new IllegalArgumentException("The age is wrong!");
        }
        mPersonAge = age;
    }

    public void setNation(String nation) {
        mNationality = nation;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getSecondName() {
        return mSecondName;
    }

    public int getPersonAge() {
        return mPersonAge;
    }

    public String getNation() {
        return mNationality;
    }

    public static Person create(String firstName,String secondName) {
        return new Person(firstName, secondName);
    }

    public static String getTAG() {
        return TAG;
    }
}
