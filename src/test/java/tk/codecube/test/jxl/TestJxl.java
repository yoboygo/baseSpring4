package tk.codecube.test.jxl;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.Test;


public class TestJxl {

	/**
	 * 测试JXL读取Excel
	 * @throws IOException 
	 * @throws BiffException 
	 */
	@Test
	public void testJxlReader() throws BiffException, IOException
	{
		String path = "D:\\config\\ZCDQQK_20161220.xls";
		Workbook book = Workbook.getWorkbook(new File(path));
		int rowNum = book.getSheet(0).getRows();
		System.out.println(rowNum);
	}
}
