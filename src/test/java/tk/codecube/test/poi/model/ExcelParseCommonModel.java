package tk.codecube.test.poi.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 到期资产统计
 * @author jianlong.song
 *
 */
public abstract class ExcelParseCommonModel {

	/**
	 * 数据库中列和对应的Excel中的计算公式
	 */
	private Map<String,String> columnFormulaMap = new LinkedHashMap<String, String>();
	
	public abstract void init();

	public ExcelParseCommonModel() {
		init();
	}
	
	public Map<String, String> getColumnFormulaMap() {
		return columnFormulaMap;
	}

	public void setColumnFormulaMap(Map<String, String> columnFormulaMap) {
		this.columnFormulaMap = columnFormulaMap;
	}
	
	
}
