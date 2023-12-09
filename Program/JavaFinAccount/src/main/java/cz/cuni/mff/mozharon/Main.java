package cz.cuni.mff.mozharon;


import cz.cuni.mff.mozharon.financialaccounting.domain.DateAndTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        DateAndTime dateAndTime = new DateAndTime();

        dateAndTime.setDate("");

        System.out.println(dateAndTime.getYear());
        System.out.println(dateAndTime.getMonth());
        System.out.println(dateAndTime.getDay());
        System.out.println(dateAndTime.getHours());
        System.out.println(dateAndTime.getMinutes());
        System.out.println(dateAndTime.getSeconds());



    }
}

