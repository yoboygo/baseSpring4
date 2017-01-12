package tk.codecube.test.poi.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 资产变现情况表
 * @author jianlong.song
 *
 */
public class Yeb_zcbxqk extends AbstractExcelParseModel {

	@Override
	public Map<String, String> assembelColumnFormulaMap() {
		Map<String,String> ret = new LinkedHashMap<String, String>();
		ret.put("filedate", "A1");
		ret.put("date", "B4");
		ret.put("hj", "C4");
		ret.put("lvzjy", "D4");
		ret.put("xyzjy", "E4");
		ret.put("yhjzhg", "F4");
		ret.put("tycdjy", "G4");
		ret.put("jyszhgrz", "H4");
		ret.put("qt", "I4");
		
		return ret;
	}

}
