package tk.codecube.test.poi.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 资产到期情况表
 * @author jianlong.song
 *
 */
public class Yeb_zcdqqk extends AbstractExcelParseModel {

	@Override
	public Map<String, String> assembelColumnFormulaMap() {
		Map<String,String> ret = new LinkedHashMap<String, String>();
		ret.put("filedate", "TEXT(A2,\"yyyyMMdd\")");
		ret.put("date", "TEXT(A4,\"yyyyMMdd\")");
		ret.put("hj", "SUM(B4,D4:Q4)");
		return ret;
	}

}
