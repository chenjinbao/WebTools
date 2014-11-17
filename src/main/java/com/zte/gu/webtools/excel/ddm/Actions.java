package com.zte.gu.webtools.excel.ddm;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "actions")
@XmlAccessorType (XmlAccessType.NONE)
public class Actions {
	
	@XmlElement (name = "action")
	private List<Action> actions = new ArrayList<Action>();

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	} 
}
