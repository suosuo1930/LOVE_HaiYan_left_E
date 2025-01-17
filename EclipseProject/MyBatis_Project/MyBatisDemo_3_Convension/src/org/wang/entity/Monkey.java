package org.wang.entity;

import java.io.Serializable;

// 实现了 序列化， 实现了 一个接口 。
public class Monkey  implements Serializable{
	private int stuNo;
    private String stuName;
    private int cardId;
    
    //联系 另一张表  (相当于 数据库中的 外键 )
    private MonkeyCard card;
    

    
    public MonkeyCard getBusiness() {
		return card;
	}


	public void setBusiness(MonkeyCard business) {
		this.card = business;
	}


	public Monkey() {
		// TODO Auto-generated constructor stub
	}


	public int getStuNo() {
		return stuNo;
	}


	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public int getCardId() {
		return cardId;
	}


	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
    
    
	@Override
	public String toString() {
		return  "stuId =" + this.stuNo + "\tName=" + this.stuName + "\tCardId= " + 
	this.card.getCardId() + "\tInfo= " + this.card.getCardInfo();
	}
	
	

}
