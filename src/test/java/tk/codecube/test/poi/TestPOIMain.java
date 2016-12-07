package tk.codecube.test.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.filters.RemoteIpFilter.XForwardedRequest;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.aop.ThrowsAdvice;

import tk.codecube.test.poi.model.ExcelParseCommonModel;

/**
 * POI测试主类
 * @author jianlong.song
 *
 */
public class TestPOIMain {

	/**
	 * 测试动态计算Excel公式
	 * @throws Exception 
	 */
	@Test
	public void testCellFormula() throws Exception
	{
		String path = "D:\\config\\YEB_SYQXJGRB_20161121.xls";
		
		//剩余期限结构日报表
		ExcelParseCommonModel syqxjgrb = new ExcelParseCommonModel(){

			@Override
			public void init() {
				this.setColumnFormulaMap(getSyqxjgrbColumnFormulaMap());
			}
			
		};
		
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
			Cell retCell = firstRow.createCell(0);
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
				ret.add(new BigDecimal(val));
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
		return ret.toPlainString();
	}

	@SuppressWarnings("deprecation")
	private String getCellValue(Cell cell)
	{
		switch(cell.getCellType())
		{
		 	case Cell.CELL_TYPE_BOOLEAN:
		 		return cell.getBooleanCellValue()+"";
	        case Cell.CELL_TYPE_NUMERIC:
	        	return String.valueOf(cell.getNumericCellValue());
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
	
	/**
	 * 返回 剩余期限结构日报表 字段和Excel数据的对应
	 * @return
	 */
	private Map<String,String> getSyqxjgrbColumnFormulaMap()
	{
		Map<String,String> ret = new LinkedHashMap<String,String>();
		//合计
		ret.put("hj", "J3");
		//活期存款
		ret.put("hqck", "J4");
		//同业存款
		ret.put("tyck", "J5");
		//同业存单
//		ret.put("tycd", "SUMPRODUCT(E24*1,F24*1,G24*1,H24*1,I24*1)");
		ret.put("tycd", "SUM(J14:J21)");
		//逆回购
		ret.put("nhg", "J22");
		//债券
		ret.put("zq", "J34");
		//国债
		ret.put("gz", "J24");
		//央票
		ret.put("yp", "J23");
		//政府性金融债
		ret.put("zfxjrz", "J26");
		//地方政府债
		ret.put("dfzfz", "J27");
		//企业债
		ret.put("qyz", "J28");
		//公司债
		ret.put("gsz", "J29");
		//中期票据
		ret.put("zqpj", "J30");
		//短期融资券
		ret.put("dqrzq", "J31");
		//资产支持证券
		ret.put("zczczq", "J32");
		
		return ret;
	}
}
