package tk.codecube.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 自动生成模板、参数等
 * @author songjl
 *
 */
public class AutoCreate {
    
    /**
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     * @Dec 自动创建任务脚本
     * songjl 2017年5月6日 上午8:45:20
     */
    @Test
    public void autoCreateTaskScript() throws UnsupportedEncodingException, FileNotFoundException, IOException{
        String configPath = "src/test/resources/data/transDataTask.model";
        String destPath = "src/test/resources/data/transDataTask.dest";
        
        String tableBase = "acct_trans_#no#";
        String dataSourceBase = "ws_rds_#no#";
        
        int tableCount = 1000;
        int dataSourceCount = 10;
        
        String configModel = TestUtils.readFile(configPath);
        List<String> tableNames = getListOfBase(tableBase,tableCount);
        List<String> dataSourceNames = getListDataSource(dataSourceBase,dataSourceCount);
        
        StringBuilder configs = new StringBuilder();
        for(int i = 0 ; i< dataSourceCount ; ++i){
            
            int start = i * tableNames.size() / dataSourceCount;
            String connectionTable = tableNames.get(start);
            
            configs.append("\n===================" + i + "=======================\n");
            String tables = createTablesStr(i,dataSourceCount,tableNames);
            String config = configModel.replace("#tables#", tables).replace("#dataSource#", dataSourceNames.get(i)).replace("#connectionTable#", connectionTable);
            configs.append(config);
        }
        
        TestUtils.writeFile(configs.toString(),destPath,false);
    }

    /**
     * @Dec 创建需要的表字符串
     * @param index
     * @param total
     * @param tableNames
     * @return
     * songjl 2017年5月6日 上午9:06:59
     */
    private String createTablesStr(int index,int total, List<String> tableNames) {
        StringBuilder ret = new StringBuilder();
        int start = index * tableNames.size() / total;
        int end = (index + 1) * tableNames.size() / total;
        for(int i = start; i< end ; ++i){
            ret.append("\"`").append(tableNames.get(i)).append("`\",");
        }
        return StringUtils.substringBeforeLast(ret.toString(), ",");
    }

    /**
     * @Dec 生成表名、数据源名
     * @param bae
     * @param count
     * @return
     * songjl 2017年5月6日 上午8:54:10
     */
    private List<String> getListOfBase(String base, int count) {
        List<String> ret = new ArrayList<String>();
        for(int i = 0; i < count; ++i){
            String key = "";
            if(i > 99){
                key = "" + i;
            }else if(i > 9){
                key = "0"+i;
            }else if(i >= 0){
                key = "00"+i;
            }
            String tableName = base.replace("#no#", key);
            ret.add(tableName);
        }
        return ret;
    }
    
    /**
     * @Dec 数据源定义为2位数
     * @param base
     * @param count
     * @return
     * songjl 2017年5月6日 上午9:23:22
     */
    private List<String> getListDataSource(String base, int count) {
        List<String> ret = new ArrayList<String>();
        for(int i = 1; i <= count; ++i){
            String key = "";
            if(i > 9){
                key = ""+i;
            }else if(i >= 0){
                key = "0"+i;
            }
            String tableName = base.replace("#no#", key);
            ret.add(tableName);
        }
        return ret;
    }
}
