package tk.codecube.test.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import tk.codecube.test.poi.model.AbstractExcelParseModel;

/**
 * POI测试主类
 * @author jianlong.song
 *
 */
public class TestPOIMain {

	private DecimalFormat df = new DecimalFormat("####################0.00");
	/**
	 * 测试动态计算Excel公式
	 * @throws Exception 
	 */
	@Test
	public void testCellFormula() throws Exception
	{
		String path = "D:\\config\\YEB_SYQXJGRB_20161121.xls";
		
		//剩余期限结构日报表
		@SuppressWarnings("unchecked")
		Class<AbstractExcelParseModel> classModel = (Class<AbstractExcelParseModel>) ClassLoader.getSystemClassLoader().loadClass("tk.codecube.test.poi.model.Yeb_syqxjgrb");
		AbstractExcelParseModel syqxjgrb = classModel.newInstance();
		
		InputStream inp = new FileInputStream(path);
		//InputStream inp = new FileInputStream("workbook.xlsx");

		Workbook wb = WorkbookFactory.create(inp);
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		
		Sheet sheet = wb.getSheetAt(0);
		wb.setForceFormulaRecalculation(true);
		//将第一个单元格定义为计算单元格
		Row firstRow = sheet.getRow(0);
		Map<String,String> columnMap = syqxjgrb.getColumnFormulaMap();
		Map<String,String> ret = new LinkedHashMap<String, String>();
		for(String col : columnMap.keySet())
		{
			Cell retCell = firstRow.createCell(firstRow.getLastCellNum()+1);
			String formula = columnMap.get(col);
			//自己实现SUM
			if(formula.startsWith("SUM("))
			{
				String val = caculateSum(formula, sheet);
				ret.put(col, val);
			}else{
				retCell.setCellType(CellType.FORMULA);
				retCell.setCellFormula(formula);
				Cell retDataCell = evaluator.evaluateInCell(retCell);
				ret.put(col, getCellValue(retDataCell));
			}
			
		}
		
		
		System.out.println(JSONObject.toJSONString(ret));
		
	}

	/**
	 * 从Excel中读取数据，返回数据集合List<Map<String,String>>
	 * 适用于一个Excel（sheet）返回多条数据
	 * @param inp
	 * @param columnFormulaMap:列公式Map
	 * @param rowStart:取值在Excel中开始的行数		
	 * @param recordLines:一条记录在Excel中占得行数
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> readRecordsFromExcel(InputStream inp,
			Map<String, String> columnFormulaMap,String rowStartStr,String recordLinesStr,int rowEndOffset,String flagFields) throws Exception {
		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		
		Workbook wb = WorkbookFactory.create(inp);
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		Sheet sheet = wb.getSheetAt(0);
		wb.setForceFormulaRecalculation(true);
		int rowEnd = sheet.getLastRowNum() + rowEndOffset;//最后一行
		Row firstRow = sheet.getRow(0);
		int retCellCol = firstRow.getLastCellNum()+1;
		int rowStart = rowEnd;
		int recordLines = rowEnd;
		if(rowStartStr != null)
		{
			rowStart = Integer.parseInt(rowStartStr);
		}
		if(recordLinesStr != null)
		{
			recordLines = Integer.parseInt(recordLinesStr);
		}
		int recordsCounter = (rowEnd - rowStart + 1)/recordLines;
		
		System.out.println("rowEnd:"+rowEnd+ "记录条数："+(recordsCounter+1));
		
		for(int i = 0 ; i<=recordsCounter ; ++i)
		{
			Map<String,String> data = new LinkedHashMap<String, String>();
			for(String col : columnFormulaMap.keySet())
			{
				//在第一行的最后一个单元格后，创建一个新的单元格用于计算公式，返回结果
				Cell retCell = firstRow.createCell(retCellCol);
				String formula = columnFormulaMap.get(col);
				
				//判断是否属于固定字段
				if(flagFields.indexOf(col) == -1)
				{
					formula = caculateRealFormula(formula,i*recordLines);
				}
				
				//自己实现SUM
				if(formula.startsWith("SUM("))
				{
					String val = caculateSum(formula, sheet);
					data.put(col, val);
				}else{
					retCell.setCellType(CellType.FORMULA);
					retCell.setCellFormula(formula);
					Cell retDataCell = evaluator.evaluateInCell(retCell);
					data.put(col, getCellValue(retDataCell));
				}
				
			}
			ret.add(data);
		}
		return ret;
	}
	
	/**Yeb_zcbxqk
	 * 测试从Excel中读取数组
	 * @throws Exception 
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testReadArrayFromExcel() throws Exception
	{
//		String path = "D:\\config\\YEB_ZCBXQK_20161121.xls";
//		String path = "D:\\config\\YEB_SYQXJGRB_20161121.xls";
		String path = "D:\\config\\ZCDQQK_20161101.xls";
		Class<AbstractExcelParseModel> classModel = (Class<AbstractExcelParseModel>) ClassLoader.getSystemClassLoader().loadClass("tk.codecube.test.poi.model.Yeb_zcbxqk");
		AbstractExcelParseModel zcbxqk = classModel.newInstance();
		Class<AbstractExcelParseModel> classModel2 = (Class<AbstractExcelParseModel>) ClassLoader.getSystemClassLoader().loadClass("tk.codecube.test.poi.model.Yeb_syqxjgrb");
		AbstractExcelParseModel syqxjgrb = classModel2.newInstance();
		Class<AbstractExcelParseModel> classModel3 = (Class<AbstractExcelParseModel>) ClassLoader.getSystemClassLoader().loadClass("tk.codecube.test.poi.model.Yeb_zcdqqk");
		AbstractExcelParseModel zcdqqk = classModel3.newInstance();
		
		InputStream inp = new FileInputStream(path);
//		List<Map<String,String>> records = readRecordsFromExcel(inp, zcbxqk.getColumnFormulaMap(),4+"", 1+"",0,"filedate");
//		List<Map<String,String>> records = readRecordsFromExcel(inp, syqxjgrb.getColumnFormulaMap(), null, null);
		List<Map<String,String>> records = readRecordsFromExcel(inp, zcdqqk.getColumnFormulaMap(), 4+"", 1+"",-1,"filedate");

		System.out.println(records.size());
		System.out.println(JSONArray.toJSONString(records));
	}
	
	
	/**
	 * 计算最真实的公式
	 * @param formula
	 * @param offset
	 * @return
	 */
	private String caculateRealFormula(String formula, int offset) {
		if(offset == 0) 
			return formula;
		String ret = formula;
		Pattern numPattern = Pattern.compile("(\\d+)");
		Matcher numMatcher = numPattern.matcher(formula);
		Map<String,String> sourceAnDestMap = new HashMap<String, String>();
		while(numMatcher.find())
		{
			long sourNum = Long.parseLong(numMatcher.group());
			long desNum = sourNum + offset;
			sourceAnDestMap.put(""+sourNum, ""+desNum);
		}
		for(Map.Entry<String, String> entry : sourceAnDestMap.entrySet())
		{
			ret = ret.replaceAll(entry.getKey(), entry.getValue());
		}
		return ret;
	}

	@Test
	public void testReg()
	{
		String formula = "SUM(A1:B1,C1,D3)";
		caculateRealFormula(formula, 1);
	}
	
	
	/**
	 * 求和
	 * 因为Excel中的数字数据的格式是字符串不能使用原生的SUM求和，
	 * SUMROPDUCT会报错，暂时用自己实现的方法
	 * @param formula
	 * @return
	 * @throws Exception 
	 */
	private String caculateSum(String formula,Sheet sheet) throws Exception {
		
		String realArea = formula.replaceAll("SUM\\((\\S+)\\)", "$1");
		BigDecimal ret = new BigDecimal("0");
		System.out.println(formula);
		//各个区域
		String[] array = realArea.split(",");
		for(int i=0 ; i<array.length ; ++i)
		{
			String[] area = array[i].split(":");
			switch(area.length)
			{
			case 1://表示单个单元格
				CellReference cr = new CellReference(array[i]);
				String val = getCellValue(sheet.getRow(cr.getRow()).getCell(cr.getCol()));
				ret = ret.add(new BigDecimal(val));
				break;
			case 2://表示一个区域
				CellReference crStart = new CellReference(area[0]);
				CellReference crEnd = new CellReference(area[1]);
				
				int xOffset = crEnd.getCol()-crStart.getCol(); 
				int yOffset = crEnd.getRow()-crStart.getRow(); 
				for(int j=0 ; j<=xOffset ; ++j)
				{
					for(int k=0; k <=yOffset ; ++k)
					{
						int y = j + crStart.getCol();
						int x = k + crStart.getRow();
						Cell cv = sheet.getRow(x).getCell(y);
						
						if(cv == null)
						{
							continue;
						}
						String cellVal = getCellValue(cv);
						ret = ret.add(new BigDecimal(cellVal));
					}
				}
				
				break;
			default: 
				throw new Exception("不支持的参数个数！");
			}
			
		}
		return df.format(ret);
	}

	@SuppressWarnings("deprecation")
	private String getCellValue(Cell cell)
	{
		switch(cell.getCellType())
		{
		 	case Cell.CELL_TYPE_BOOLEAN:
		 		return cell.getBooleanCellValue()+"";
	        case Cell.CELL_TYPE_NUMERIC:
	        	return df.format(new BigDecimal(cell.getNumericCellValue()));
	        case Cell.CELL_TYPE_STRING:
	        	return cell.getStringCellValue();
	        case Cell.CELL_TYPE_BLANK:
	        	return "0.00";
	        case Cell.CELL_TYPE_ERROR:
	        	return String.valueOf(cell.getErrorCellValue());
	        case Cell.CELL_TYPE_FORMULA:
	        	return "0.00";
        	default: 
        		return "0.00";
		}
	}
	
}
