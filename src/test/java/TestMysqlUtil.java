import com.lzz.dao.NodeMonitorInfoDao;
import com.lzz.model.NodeModel;
import com.lzz.model.NodeMonitorInfo;
import com.lzz.util.MysqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2017/8/5.
 */
public class TestMysqlUtil {
    @Test
    public void test1(){
        List s = MysqlUtil.select("select * from node_info");
        System.out.println( s );
    }

    @Test
    public void test2() throws IllegalAccessException {
        String sql = "insert into node_info";
        List field = new ArrayList();
        List values = new ArrayList();
        NodeModel o = new NodeModel();
        Field[] fields=o.getClass().getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            String name = f.getName();
            f.setAccessible(true);
            field.add( name );
            String tmp = "";
            String type = f.getGenericType().toString();
            if( type.equals("class java.lang.String") ){
                tmp = "'" + f.get(o) + "'";
            }else{
                tmp = String.valueOf( f.get(o) );
            }
            values.add( tmp );
        }
        String fieldStr = StringUtils.join( field, "," );
        String valueStr = StringUtils.join( values, "," );
        sql += "(" + fieldStr + ") ";
        sql += "values (" + valueStr + ")";
        System.out.println( sql );
    }

    @Test
    public void test3(){
        NodeMonitorInfoDao dao = new NodeMonitorInfoDao();
        NodeMonitorInfo nodeMonitorInfo = new NodeMonitorInfo();
        nodeMonitorInfo.setAdd_time(11221);
        dao.addNodeInfo( nodeMonitorInfo );
    }
}
