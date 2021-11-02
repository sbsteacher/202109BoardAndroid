package com.koreait.board;

public class BoardVO {
    private int iboard;
    private String title;
    private String ctnt;
    private String writer;
    private String rdt;

    public String getCtnt() {
        return ctnt;
    }

    public void setCtnt(String ctnt) {
        this.ctnt = ctnt;
    }

    public int getIboard() {
        return iboard;
    }

    public void setIboard(int iboard) {
        this.iboard = iboard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRdt() {
        return rdt;
    }

    public void setRdt(String rdt) {
        this.rdt = rdt;
    }
}
