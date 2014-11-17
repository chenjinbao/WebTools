package com.zte.ommb.tool.rfa;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * <p>文件描述: 扫描工具文件选择过滤器</p>
 * <p>版权所有: 版权所有(C)2010-2014</p>
 * <p>公   司: 深圳市中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>修改记录1: </p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：刘赛
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 */
public class XmlFileFilter extends FileFilter
{

	@Override
	public boolean accept(File file)
	{
		boolean isXls = true;
		String name = file.getName().trim().toLowerCase();
		if (file.isFile()) {
			isXls = name.endsWith("xls");
		}
		return isXls;
	}

	@Override
	public String getDescription()
	{
		return "xls文件";
	}

}
