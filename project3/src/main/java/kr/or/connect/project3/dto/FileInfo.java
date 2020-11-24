package kr.or.connect.project3.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileInfo {
	
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private Date createDate;
	private Date modifyDate;
	

}
