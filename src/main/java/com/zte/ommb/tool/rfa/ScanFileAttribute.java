package com.zte.ommb.tool.rfa;

import java.util.HashMap;

/**
 * 
 * <p>文件描述: RRU属性文档中的属性定义</p>
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
public class ScanFileAttribute
{
	private ScanFileAttribute()
	{
		
	}
	public static String RFA_RRU_RADIO             = "无线制式";
	
	public static String RFA_XML_SHEET             = "射频分析模块";
	
	public static String RFA_VSWR_FUNCTION         = "驻波比位置检测";
	
	public static String RFA_PIMD_FUNCTION         = "无源互调";
	
	public static String RFA_ALLFREQSCAN_FUNCTION  = "接收频谱分析";
	
	public static String RFA_CELLFREQSCAN_FUNCTION = "载频频谱分析";
	
	public static String RFA_ACSD_FUNCTION         = "天线线序检测";
	
	public static String RFA_TXFREQSCAN_FUNCTION   = "发射频谱分析";
	
	/**保存XLS中功能名称和OMMB客户端Clientview中各功能点的对应关系*/
	public static HashMap<String, String> map = new HashMap<String, String>();
	
	static
	{
		map.put(RFA_VSWR_FUNCTION,         "ID_VSWRPM_RFA");
		map.put(RFA_PIMD_FUNCTION,         "ID_ANTENNADCHECK_RFA");
		map.put(RFA_ALLFREQSCAN_FUNCTION,  "ID_WBFREQSCAN_RFA");
		map.put(RFA_CELLFREQSCAN_FUNCTION, "ID_FREQSCAN_RFA");
		map.put(RFA_ACSD_FUNCTION,         "ID_ACSD_RFA");
		map.put(RFA_TXFREQSCAN_FUNCTION,   "ID_TXFREQSCAN_RFA");
	}
}
