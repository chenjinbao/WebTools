package com.zte.gu.webtools.excel;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.NONE)
public class Action {
	
	@XmlAttribute (name = "type")
	private String type;
	
	@XmlElement (name = "Board")
	private List<Board> boards = new ArrayList<Board>();
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	
}
