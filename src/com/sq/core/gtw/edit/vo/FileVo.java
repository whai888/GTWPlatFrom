package com.sq.core.gtw.edit.vo;


/**
 *@作者  whai 
 *@创建日期 2013-8-23
 *@版本 V1.0
 *@文件名 FileVo.java
 */
public class FileVo implements Comparable<FileVo>{
	
	private String image = "" ;
	
	private String fileName ;
	
	private String path ;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int compareTo(FileVo o) {
		// TODO Auto-generated method stub
		if(this.getFileName().compareTo(o.getFileName()) > 0 ){
			return 1 ;
		}
		return 0;
	}

}
