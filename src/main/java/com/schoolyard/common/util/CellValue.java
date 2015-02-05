package com.schoolyard.common.util;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * Excel取值类
 * 
 * @version 版本信息 创建时间 2010-6-25 下午16:01:50
 */
public class CellValue {

	public static final String TXT_SEPARATOR = "\t";
	public static final String XML_SEPARATOR = "root/rows/row";

	 

	/**
	 * 返回Excel2003去掉空行的记录数(xls)
	 */
	public static int getRightRows(HSSFSheet sheet) {
		int rsRows = sheet.getPhysicalNumberOfRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		// 定义 row、cell
		HSSFRow row;
		// HSSFCell cell;
		String cell;
		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum(); i < rsRows; i++) { // 统计行中为空的单元格数
			row = sheet.getRow(i);
			nullCellNum = 0;
			if (row == null) {
				afterRows--;
				continue;
			}

			int rsCols = row.getPhysicalNumberOfCells();// 列数

			for (int j = 0; j < rsCols; j++) {
				cell = row.getCell(j) == null ? "" : row.getCell(j).toString();
				if ("".equals(cell.trim())) {
					nullCellNum++;
				}
			}
			if (nullCellNum >= rsCols) { // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}
	
	/**
	 * 返回Excel2003，2007去掉空行的记录数(xls)
	 */
	public static int getRightRows(Sheet sheet) {
		int rsRows = sheet.getPhysicalNumberOfRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		// 定义 row、cell
		Row row;
		// HSSFCell cell;
		String cell;
		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum(); i < rsRows; i++) { // 统计行中为空的单元格数
			row = sheet.getRow(i);
			nullCellNum = 0;
			if (row == null) {
				afterRows--;
				continue;
			}

			int rsCols = row.getPhysicalNumberOfCells();// 列数

			for (int j = 0; j < rsCols; j++) {
				cell = row.getCell(j) == null ? "" : row.getCell(j).toString();
				if ("".equals(cell.trim())) {
					nullCellNum++;
				}
			}
			if (nullCellNum >= rsCols) { // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}


	/**
	 * excel2003单元格内容读取
	 * 
	 * @param cell
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStringValue(HSSFCell cell, String defaultValue) {
		String strReturn = defaultValue;

		try {
			if (cell == null)
				return defaultValue;

			int type = cell.getCellType();

			switch (type) {
				case HSSFCell.CELL_TYPE_STRING:
					strReturn = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
					if (isDate) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
						 sdf.format(new Date());
						strReturn =  sdf.format(new Date());
						if (strReturn == null) {
							strReturn = cell.getDateCellValue().toLocaleString();
						}
					} else {
						double d = cell.getNumericCellValue();
						if (d - (int) d < Double.MIN_VALUE) { // 是否为int型
							strReturn = Integer.toString((int) d);
						}
						else { // 是否为double型
							strReturn = Double.toString(cell.getNumericCellValue());
						}
					}
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					strReturn = String.valueOf(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					strReturn = defaultValue;
					break;
				default:
					strReturn = defaultValue;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			strReturn = defaultValue;
		}

		return strReturn;
	}

	  
	/**
	 * excel2003，2007单元格内容读取
	 * 
	 * @param cell
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStringValue(Cell cell, String defaultValue) {
		String strReturn = defaultValue;

		try {
			if (cell == null)
				return defaultValue;

			int type = cell.getCellType();

			switch (type) {
				case Cell.CELL_TYPE_STRING:
					strReturn = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
					if (isDate) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
						 sdf.format(new Date());
						strReturn =  sdf.format(new Date());
						if (strReturn == null) {
							strReturn = cell.getDateCellValue().toLocaleString();
						}
					} else {
						double d = cell.getNumericCellValue();
						if (d - (int) d < Double.MIN_VALUE) { // 是否为int型
							strReturn = Integer.toString((int) d);
						}
						else { // 是否为double型
							strReturn = Double.toString(cell.getNumericCellValue());
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					strReturn = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_ERROR:
					strReturn = defaultValue;
					break;
				default:
					strReturn = defaultValue;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			strReturn = defaultValue;
		}

		return strReturn;
	}

	/**
	 * 获取excel2003标题行列名组成的list
	 * 
	 * @param row 标题行
	 * @return list
	 */
	public static List<String> getColNames(HSSFRow row) {
		int cols = row.getPhysicalNumberOfCells();
		List<String> colNames = new ArrayList<String>();

		for (int i = 0; i < cols; i++) {
			String colName = getStringValue(row.getCell(i), "");
			if (colName != null && !colName.equals(""))
				colNames.add(colName);
		}

		return colNames;
	}

	/**
	 * 获取excel2003标题行列名组成的list
	 * 
	 * @param row 标题行
	 * @return list
	 */
	public static Map<Integer, String> getMapColNames(HSSFRow row) {
		int cols = row.getPhysicalNumberOfCells();
		Map<Integer, String> colNames = new HashMap<Integer, String>();

		for (int i = 0; i < cols; i++) {
			String colName = getStringValue(row.getCell(i), "");
			if (colName != null && !colName.equals(""))
				colNames.put(i, colName);
		}

		return colNames;
	}


	/**
	 * 获取txt标题行列名组成的list(默认第一个非空行为标题行)
	 * 
	 * @param fieName 文件名
	 * @return list
	 */
	public static List<String> getColNames(String fileName, String separator) {
		BufferedReader in = null;
		try {
			List<String> colNames = new ArrayList<String>();
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				if (!"".equals(line.trim())) {
					for (String str : line.split(separator)) {
						colNames.add(str.trim());
					}
					return colNames;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取txt标题行列名组成的list(默认第一个非空行为标题行)
	 * 
	 * @param fieName 文件名
	 * @return list
	 */
	public static Map<Integer, String> getMapColNames(String fileName,
			String separator) {
		BufferedReader in = null;
		try {
			Map<Integer, String> colNames = new HashMap<Integer, String>();
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				if (!"".equals(line.trim())) {
					for (int i = 0; i < line.split(separator).length; i++) {
						String str = line.split(separator)[i];
						colNames.put(i, str.trim());
					}
					return colNames;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static Map<Integer, String> getMapColNames(FileInputStream fis,
			String separator) {
		BufferedReader in = null;
		try {
			Map<Integer, String> colNames = new HashMap<Integer, String>();
			in = new BufferedReader(new InputStreamReader(fis));
			String line;
			while ((line = in.readLine()) != null) {
				if (!"".equals(line.trim())) {
					for (int i = 0; i < line.split(separator).length; i++) {
						String str = line.split(separator)[i];
						colNames.put(i, str.trim());
					}
					return colNames;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取txt标题行列名组成的list(默认第一个非空行为标题行)
	 * 
	 * @param fieName 文件名
	 * @return list
	 */
	public static List<String> getColNames(InputStream is, boolean firstHeader,
			String separator) {
		BufferedReader in = null;
		try {
			List<String> colNames = new ArrayList<String>();
			in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			boolean isRun = true;
			while (isRun) {
				line = in.readLine();
				if (line == null) {
					continue;
				}
				isRun = false;
				if (!firstHeader) {
					line = in.readLine();
				}
			}
			if (!"".equals(line.trim())) {
				for (int i = 0; i < line.split(separator).length; i++) {
					String str = line.split(separator)[i];
					colNames.add(str.trim());
				}
				return colNames;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取txt标题行列名组成的list(默认第一个非空行为标题行)
	 * 
	 * @param fieName 文件名
	 * @return list
	 */
	public static Map<Integer, String> getMapColNames(InputStream is,
			boolean firstHeader, String separator) {
		BufferedReader in = null;
		try {
			Map<Integer, String> colNames = new HashMap<Integer, String>();
			in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			boolean isRun = true;
			while (isRun) {
				line = in.readLine();
				if (line == null) {
					continue;
				}
				isRun = false;
				if (!firstHeader) {
					line = in.readLine();
				}
			}
			if (!"".equals(line.trim())) {
				for (int i = 0; i < line.split(separator).length; i++) {
					String str = line.split(separator)[i];
					colNames.put(i, str.trim());
				}
				return colNames;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取txt内容组成的list(默认第一个非空行为标题行)
	 * 
	 * @param fieName 文件名
	 * @return list
	 */
	public static List<String[]> getTxtContents(String fileName,
			String separator) {
		BufferedReader in = null;
		try {
			List<String[]> colNames = new ArrayList<String[]>();
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				if (!"".equals(line.trim())) {
					colNames.add(line.split(separator));
				}
			}
			colNames.remove(0);
			return colNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static List<String[]> getTxtContents(FileInputStream fis,
			String separator) {
		
		BufferedReader in = null;
		try {
			List<String[]> colNames = new ArrayList<String[]>();
			in = new BufferedReader(new InputStreamReader(fis));
			String line;
			while ((line = in.readLine()) != null) {
				if (!"".equals(line.trim())) {
					colNames.add(line.split(separator));
				}
			}
			colNames.remove(0);
			return colNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 返回xml文件对应的内容
	 */
	public static Document getXmlDoc(File file) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (Exception e) {
			try {
				InputStream in = new FileInputStream(file);
				InputStreamReader strInStream = new InputStreamReader(in, "GBK");
				document = reader.read(strInStream);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return document;
	}

	/**
	 * 返回xml文件对应的内容
	 */
	public static Document getXmlDoc(FileInputStream fis) {
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			BufferedReader input = null;
			PushbackInputStream pis = new PushbackInputStream(fis, 1024);
			String bomEncoding = getBOMEncoding(pis);
			if (bomEncoding == null) {
				input = new BufferedReader(new InputStreamReader(pis, "UTF-8"));
			} else {
				input = new BufferedReader(new InputStreamReader(pis,
						bomEncoding));
			}
			document = reader.read(input);
		} catch (Exception e) {
			try {
				InputStreamReader strInStream = new InputStreamReader(fis, "GBK");
				document = reader.read(strInStream);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return document;
	}

	/**
	 * 获取xml最后一级节点名组成的list
	 * 
	 * @param element0  节点
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getLastLevelNodeNames(Element element0) {
		List<String> nodeNames = new ArrayList<String>();
		List<Element> elements = element0.elements();
		for (Element ele : elements) {
			nodeNames.add(ele.getName());
		}
		return nodeNames;
	}

	protected static String getBOMEncoding(PushbackInputStream is)
			throws IOException {
		String encoding = null;
		int[] bytes = new int[3];
		bytes[0] = is.read();
		bytes[1] = is.read();
		bytes[2] = is.read();
		if (bytes[0] == 0xFE && bytes[1] == 0xFF) {
			encoding = "UTF-16BE";
			is.unread(bytes[2]);
		} else if (bytes[0] == 0xFF && bytes[1] == 0xFE) {
			encoding = "UTF-16LE";
			is.unread(bytes[2]);
		} else if (bytes[0] == 0xEF && bytes[1] == 0xBB && bytes[2] == 0xBF) {
			encoding = "UTF-8";
		} else {
			for (int i = bytes.length - 1; i >= 0; i--) {
				is.unread(bytes[i]);
			}
		}
		return encoding;
	}

	/**
	 * 验证与正则表达式是否匹配
	 */
	public static boolean matchRegEx(String input, String regEx) {
		Pattern pattern = null;
		try {
			pattern = new Perl5Compiler().compile(regEx);
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		Perl5Matcher matcher = new Perl5Matcher();
		PatternMatcherInput matcherInput = new PatternMatcherInput(input);
		return matcher.contains(matcherInput, pattern);
	}
	
	public static void main(String[] args) {
//		File file = new File("D:\\test.xls");
		File file = new File(FileUtil.REAL_PATH+"//excel//test.xls");
		FileInputStream fis = null;
		int startNum = 0;
		try {
			fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rows = CellValue.getRightRows(sheet);
			HSSFRow row0 = sheet.getRow(startNum);
			Map<Integer, String> colsName = CellValue.getMapColNames(row0);
			Object[] cols = colsName.keySet().toArray();
			for (int i = startNum + 1; i < rows; i++) {
				try {
					for (int j = 0; j < cols.length; j++) {
						String data = CellValue.getStringValue(sheet.getRow(i).getCell(j), null);
						if (data != null) {
							data = data.trim();
							switch (j) {
							case 0:
								System.out.println("姓名："+data);
								break;
							case 1:
								System.out.println("电话："+data);
								break;
							default:
								break;
							}
						}
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
