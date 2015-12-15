package com.cblue.interaction.httpdemo;

public class Lunch {

	@Override
	public String toString() {
		return "Lunch [cateid=" + cateid + ", cateurl=" + cateurl
				+ ", catetitle=" + catetitle + ", catecontent=" + catecontent
				+ ", cateprice=" + cateprice + "]";
	}
	private int cateid;
	private String cateurl;
	public Lunch() {
		super();
	}
	public Lunch(int cateid, String cateurl, String catetitle,
			String catecontent, String cateprice) {
		super();
		this.cateid = cateid;
		this.cateurl = cateurl;
		this.catetitle = catetitle;
		this.catecontent = catecontent;
		this.cateprice = cateprice;
	}
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCateurl() {
		return cateurl;
	}
	public void setCateurl(String cateurl) {
		this.cateurl = cateurl;
	}
	public String getCatetitle() {
		return catetitle;
	}
	public void setCatetitle(String catetitle) {
		this.catetitle = catetitle;
	}
	public String getCatecontent() {
		return catecontent;
	}
	public void setCatecontent(String catecontent) {
		this.catecontent = catecontent;
	}
	public String getCateprice() {
		return cateprice;
	}
	public void setCateprice(String cateprice) {
		this.cateprice = cateprice;
	}
	private String catetitle;
	private String catecontent;
	private String cateprice;
}
