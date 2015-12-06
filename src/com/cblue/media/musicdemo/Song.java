package com.cblue.media.musicdemo;

public class Song {

	
	private int pic;
	private String name;
	private int path;
	public Song() {
		super();
	}
	public Song(int pic, String name,int path) {
		super();
		this.pic = pic;
		this.name = name;
		this.path = path;
	}
	public int getPath() {
		return path;
	}
	public void setPath(int path) {
		this.path = path;
	}
	public int getPic() {
		return pic;
	}
	public void setPic(int pic) {
		this.pic = pic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
