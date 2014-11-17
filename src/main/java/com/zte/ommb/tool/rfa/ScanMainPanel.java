package com.zte.ommb.tool.rfa;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * <p>文件描述:射频分析扫描工具 </p>
 * <p>版权所有: 版权所有(C)2010-2014</p>
 * <p>公   司: 深圳市中兴通讯股份有限公司</p>
 * <p>内容摘要: 输入RRU属性文档，输出属性文件</p>
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
public class ScanMainPanel extends JDialog
{
	private static final long serialVersionUID = -3402596946118317832L;
	
	private JLabel jlSelectScanRadio = new JLabel("选择扫描制式:");
	private JComboBox jcbScanRadio = new JComboBox();
	private JTextField jtInputPath = new JTextField();
	private JTextField jtOutputPath = new JTextField();
	private JButton selectInputPath = new JButton("选择路径");
	private JButton selectOutputPath = new JButton("选择路径");
	private JButton startupScanFile = new JButton("启动扫描");
	
	private String scanRadios = "";
	

	public ScanMainPanel(JFrame pare)
	{
		super(pare, "射频分析扫描工具", true);
		setLayout(new BorderLayout());
		loadConfig();
		initDialog();
		centerScreen();
	}

	private void loadConfig()
	{
		Properties prop = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream("scanradioconfig.properties");
			prop.load(fis);
			scanRadios = prop.getProperty("scanradio");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				fis = null;
			}
		}

	}

	/**
	 * 初始化窗体
	 */
	private void initDialog()
	{
		setSize(600, 180);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(creatCenterPanel());

		addAction();
	}
	
	public void centerScreen()
	{
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}

	/**
	 * 创建扫描工具面板
	 * 
	 * @return
	 */
	private Component creatCenterPanel()
	{
		JPanel scanPanel = new JPanel();
		SpringLayout myLayout = new SpringLayout();
		scanPanel.setLayout(myLayout);

		jlSelectScanRadio.setPreferredSize(new Dimension(100, 25));
		jcbScanRadio.setPreferredSize(new Dimension(490, 25));	
		jcbScanRadio.removeAll();
		
		String[] radioList = getScanRadioList();
		for(String radio : radioList)
		{
			jcbScanRadio.addItem(radio);
		}		
		
		JLabel jlInputPath = new JLabel("输入文件路径：");
		JLabel jlOutputPath = new JLabel("输出文件路径：");

		jlInputPath.setPreferredSize(new Dimension(100, 25));
		jtInputPath.setPreferredSize(new Dimension(380, 25));
		selectInputPath.setPreferredSize(new Dimension(90, 25));

		jlOutputPath.setPreferredSize(new Dimension(100, 25));
		jtOutputPath.setPreferredSize(new Dimension(380, 25));
		selectOutputPath.setPreferredSize(new Dimension(90, 25));
		startupScanFile.setPreferredSize(new Dimension(120, 25));

		scanPanel.add(jlSelectScanRadio);
		scanPanel.add(jcbScanRadio);
		scanPanel.add(jlInputPath);
		scanPanel.add(jtInputPath);
		scanPanel.add(selectInputPath);
		scanPanel.add(jlOutputPath);
		scanPanel.add(jtOutputPath);
		scanPanel.add(selectOutputPath);
		scanPanel.add(startupScanFile);
		
		myLayout.putConstraint(SpringLayout.WEST, jlSelectScanRadio, 5, SpringLayout.WEST, scanPanel);
		myLayout.putConstraint(SpringLayout.NORTH, jlSelectScanRadio, 10, SpringLayout.NORTH, scanPanel);
		myLayout.putConstraint(SpringLayout.WEST, jcbScanRadio, 5, SpringLayout.EAST, jlInputPath);
		myLayout.putConstraint(SpringLayout.NORTH, jcbScanRadio, 5, SpringLayout.NORTH, scanPanel);
		
		myLayout.putConstraint(SpringLayout.WEST, jlInputPath, 5, SpringLayout.WEST, scanPanel);
		myLayout.putConstraint(SpringLayout.NORTH, jlInputPath, 10, SpringLayout.SOUTH, jcbScanRadio);
		myLayout.putConstraint(SpringLayout.WEST, jtInputPath, 5, SpringLayout.EAST, jlInputPath);
		myLayout.putConstraint(SpringLayout.NORTH, jtInputPath, 10, SpringLayout.SOUTH, jcbScanRadio);
		myLayout.putConstraint(SpringLayout.WEST, selectInputPath, 5, SpringLayout.EAST, jtInputPath);
		myLayout.putConstraint(SpringLayout.NORTH, selectInputPath, 10, SpringLayout.SOUTH, jcbScanRadio);

		myLayout.putConstraint(SpringLayout.WEST, jlOutputPath, 5, SpringLayout.WEST, scanPanel);
		myLayout.putConstraint(SpringLayout.NORTH, jlOutputPath, 10, SpringLayout.SOUTH, jlInputPath);
		myLayout.putConstraint(SpringLayout.WEST, jtOutputPath, 5, SpringLayout.EAST, jlOutputPath);
		myLayout.putConstraint(SpringLayout.NORTH, jtOutputPath, 10, SpringLayout.SOUTH, jtInputPath);
		myLayout.putConstraint(SpringLayout.WEST, selectOutputPath, 5, SpringLayout.EAST, jtOutputPath);
		myLayout.putConstraint(SpringLayout.NORTH, selectOutputPath, 10, SpringLayout.SOUTH, selectInputPath);

		myLayout.putConstraint(SpringLayout.EAST, startupScanFile, -10, SpringLayout.EAST, scanPanel);
		myLayout.putConstraint(SpringLayout.NORTH, startupScanFile, 10, SpringLayout.SOUTH, selectOutputPath);

		return scanPanel;
	}

	/**
	 * 添加按钮的处理事件
	 */
	private void addAction()
	{
		selectInputPath.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser inputFileChooser = new JFileChooser();
				inputFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				inputFileChooser.setAcceptAllFileFilterUsed(false);
				inputFileChooser.setCurrentDirectory(inputFileChooser.getSelectedFile());
				XmlFileFilter filter = new XmlFileFilter();
				inputFileChooser.setFileFilter(filter);

				int result = inputFileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					jtInputPath.setText(inputFileChooser.getSelectedFile().getPath());
					jtOutputPath.setText(inputFileChooser.getSelectedFile().getParent());
				}
			}
		});

		selectOutputPath.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser outputFileChooser = new JFileChooser();
				outputFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				outputFileChooser.setAcceptAllFileFilterUsed(false);
				outputFileChooser.setCurrentDirectory(outputFileChooser.getSelectedFile());

				int result = outputFileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					jtOutputPath.setText(outputFileChooser.getSelectedFile().getPath());
				}
			}
		});

		startupScanFile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{	
				write2RfaSupportrruFile(scanFile());	
				JOptionPane.showMessageDialog(null, "文件输出成功。" , "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * 扫描文件，输出属性文件"sdrmanager-rfa-supportrru. properties"
	 */
	private HashMap<String, List<SupportRruInfo>> scanFile()
	{
		HashMap<String, List<SupportRruInfo>> rfaSupportRru = new HashMap<String, List<SupportRruInfo>>();
		
		String inputFilePath = jtInputPath.getText();
		String outputFilePath = jtOutputPath.getText();
		String scanRaio = jcbScanRadio.getSelectedItem().toString();
		String radioValue = scanRaio.split(":")[0];
		
		if(null == scanRaio || scanRaio.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "请选择扫描类型", "提示", JOptionPane.INFORMATION_MESSAGE);
			return rfaSupportRru;
		}

		if (inputFilePath.equals("") || outputFilePath.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择输入或输出路径", "提示", JOptionPane.INFORMATION_MESSAGE);
			return rfaSupportRru;
		}

		InputStream stream = null;
		try
		{
			/**第一步：获取射频分析sheet页对应的行数和列数*/
			stream = new FileInputStream(inputFilePath);
			Workbook wb = new HSSFWorkbook(stream);			
			Sheet rfaSheet = wb.getSheet(ScanFileAttribute.RFA_XML_SHEET);
	
			int rowNum = rfaSheet.getPhysicalNumberOfRows();
			int columnNum = 0;
			if(rowNum > 0)
			{
				columnNum = rfaSheet.getRow(0).getPhysicalNumberOfCells();
			}
		
			/**第二步：获取各功能对应的列单元格的索引*/
			int colIndexOfRadio        = -1;
			int colIndexOfVSWR         = -1;
			int colIndexOfPIMD         = -1;
			int colIndexOfALLFREQSCAN  = -1;
			int colIndexOfCELLFREQSCAN = -1;
			int colIndexOfACSD         = -1;
			int colIndexOfTXFREQSCAN   = -1;
			
			for(int i = 0; i < columnNum; i++)
			{				
				String cellInfo = rfaSheet.getRow(0).getCell(i).getStringCellValue();
				if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_RRU_RADIO))
				{
					colIndexOfRadio = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_VSWR_FUNCTION))
				{
					colIndexOfVSWR = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_PIMD_FUNCTION))
				{
					colIndexOfPIMD = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_ALLFREQSCAN_FUNCTION))
				{
					colIndexOfALLFREQSCAN = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_CELLFREQSCAN_FUNCTION))
				{
					colIndexOfCELLFREQSCAN = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_ACSD_FUNCTION))
				{
					colIndexOfACSD = i;
				}
				else if(cellInfo.toString().equalsIgnoreCase(ScanFileAttribute.RFA_TXFREQSCAN_FUNCTION))
				{
					colIndexOfTXFREQSCAN = i;
				}
			}
	
			/**第三步：通过功能列的索引，遍历每一行的数据，判断是否支持对应的功能*/			
			for(int i = 2; i< rowNum; i++)
			{
				String productName   = getCellValue(rfaSheet.getRow(i).getCell(0));
				String productNumber = getCellValue(rfaSheet.getRow(i).getCell(2));
				String cuRruRadio    = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfRadio));
				
				//判断是否包含扫描的制式
				if(cuRruRadio.contains(radioValue))
				{
					String vswr          = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfVSWR));
					String pimd          = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfPIMD));
					String allfs         = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfALLFREQSCAN));
					String cellfs        = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfCELLFREQSCAN));
					String acsd          = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfACSD));
					String txfs          = getCellValue(rfaSheet.getRow(i).getCell(colIndexOfTXFREQSCAN));
					
					SupportRruInfo rru = new SupportRruInfo(productNumber, productName);				
					add2SupportRruMap(ScanFileAttribute.RFA_VSWR_FUNCTION,         vswr, rru, rfaSupportRru);
					add2SupportRruMap(ScanFileAttribute.RFA_PIMD_FUNCTION,         pimd, rru, rfaSupportRru);
					add2SupportRruMap(ScanFileAttribute.RFA_ALLFREQSCAN_FUNCTION,  allfs, rru, rfaSupportRru);
					add2SupportRruMap(ScanFileAttribute.RFA_CELLFREQSCAN_FUNCTION, cellfs, rru, rfaSupportRru);
					add2SupportRruMap(ScanFileAttribute.RFA_ACSD_FUNCTION,         acsd, rru, rfaSupportRru);
					add2SupportRruMap(ScanFileAttribute.RFA_TXFREQSCAN_FUNCTION,   txfs, rru, rfaSupportRru);
				}
			}		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(stream != null)
			{
				try
				{
					stream.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				stream = null;
			}
		}
		return rfaSupportRru;
	}
	
	/**
	 * 通过扫描的HashMap生成对应的配置文件
	 * @param rfaSupportRru
	 */
	private void write2RfaSupportrruFile(HashMap<String, List<SupportRruInfo>> rfaSupportRru)
	{
		String outputFilepath = jtOutputPath.getText() + File.separator + getFileName();
		File outputFile = new File(outputFilepath);

		try
		{
			if (!outputFile.exists())
			{
				outputFile.createNewFile();
			}

			Properties pro = new Properties();			
			FileOutputStream outStream = new FileOutputStream(new File(outputFilepath));
			Iterator<String> iter = rfaSupportRru.keySet().iterator();
			
			
			while (iter.hasNext())
			{
				String functionName = iter.next().toString();
				List<SupportRruInfo> supportRrus = rfaSupportRru.get(functionName);				
				pro.put(functionName, list2String(supportRrus, 1));
				System.out.println(functionName + ": " + supportRrus.size());
			}
						 
			pro.store(outStream, null);
			outStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 获取单元格对应的值
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell)
	{
		try{
		String s = "";
		int cellType = cell.getCellType();
		if(cellType == 0 || cellType == 2)
		{
			s = (int)cell.getNumericCellValue() + "";
		}
		else
		{
			s = cell.getStringCellValue();
		}
		System.out.println(s);
		return s;
		}catch(Exception e)
		{
			System.out.println(cell);
		}
		
		return "";
	}
	
	/**
	 * 判断某功能是否支持该RRU，支持的话添加到对应的Map
	 * @param args
	 */
	private void add2SupportRruMap(String function, String rruSupportValue, SupportRruInfo rru, HashMap<String, List<SupportRruInfo>> rfaSupportRru)
	{
		if(rruSupportValue == null)
		{
			return;
		}
		if(rruSupportValue.trim().equals("1"))
		{
			String functionId = ScanFileAttribute.map.get(function);
			List<SupportRruInfo> rruList = rfaSupportRru.get(functionId);
			if(rruList == null)
			{
				rruList = new ArrayList<SupportRruInfo>();						
			}
			rruList.add(rru);	
			rfaSupportRru.put(functionId, rruList);
		}		
	}
	
	/***
	 * 将List转换为String
	 * @param args
	 */
	private String list2String(List<SupportRruInfo> rrus, int type)
	{
		StringBuffer out = new StringBuffer();
		for(SupportRruInfo rru : rrus)
		{
			if(type == 0)
			{
				out.append(rru.getProductName());
				out.append(",");
			}
			else
			{
				out.append(rru.getProductNumber());
				out.append(",");
			}

		}
		return out.toString();
	}
	
	private String[] getScanRadioList()
	{
		if(scanRadios == null || scanRadios.length()==0)
		{
			JOptionPane.showMessageDialog(null, "请检查扫描制式配置文件,文件名称：scanradioconfig.properties");
			return null;
		}
		else
		{
			return scanRadios.split(",");
		}
	}
	
	private String getFileName()
	{
		String scanRaio = jcbScanRadio.getSelectedItem().toString();
		String radioValue = scanRaio.split(":")[1] + "-rfa-supportrru.properties";		
		return radioValue;		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();		
		new ScanMainPanel(frame).setVisible(true);
		System.out.println("".length());
	}
	
	
}
