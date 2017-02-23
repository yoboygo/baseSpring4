package tk.codecube.test.poi.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Yeb_syqxjgrb extends AbstractExcelParseModel{

	@Override
	public Map<String, String> assembelColumnFormulaMap() {
		Map<String,String> ret = new LinkedHashMap<String, String>();
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
