package com.zte.ommb.tool.rfa;

/**
 * <p>文件描述: 支持RRU对应的信息</p>
 * <p>版权所有: 版权所有(C)2010-2014</p>
 * <p>公   司: 深圳市中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>修改记录1: </p>
 * <pre>
 *    版 本 号：
 *    修 改 人：刘赛
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 */
public class SupportRruInfo
{
	/**单板对应的板类型*/
	private String productNumber;

	/**单板对应的名称*/
	private String productName;

	public SupportRruInfo(String productNumber, String productName)
	{
		this.productNumber = productNumber;
		this.productName = productName;
	}
	
	public String getProductNumber()
	{
		return productNumber;
	}

	public void setProductNumber(String productNumber)
	{
		this.productNumber = productNumber;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
}
