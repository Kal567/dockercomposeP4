package com.pucmm.compose.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Encuesta {


    @Id
    @GeneratedValue
    private int idEncuesta;

    private int questionOne;
    private int questionTwo;
    private int questionThree;
    private String questionFour;

    public Encuesta(){
        super();
    }

    public Encuesta(int questionOne, int questionTwo,
                    int questionThree, String questionFour){
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
        this.questionFour = questionFour;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public int getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(int questionOne) {
        this.questionOne = questionOne;
    }

    public int getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(int questionTwo) {
        this.questionTwo = questionTwo;
    }

    public int getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(int questionThree) {
        this.questionThree = questionThree;
    }

    public String getQuestionFour() {
        return questionFour;
    }

    public void setQuestionFour(String questionFour) {
        this.questionFour = questionFour;
    }

}
