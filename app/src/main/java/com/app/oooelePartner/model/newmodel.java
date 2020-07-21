package com.app.oooelePartner.model;

public class newmodel {


    String name,skill,day,quotes,credit;

    public newmodel() {
    }

    public newmodel(String name, String skill, String day, String quotes, String credit) {
        this.name = name;
        this.skill = skill;
        this.day = day;
        this.quotes = quotes;
        this.credit = credit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
