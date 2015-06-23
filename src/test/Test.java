import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/8
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    private int m;
    private String s;
    public void test(){

        int a,b=0;
        b++;
        String d,s="s";
        //a++;
        System.out.println(s);
        logger.info("a{}",new Object[]{s});
    }
    public static void main(String[] args){

int[] arr = new int[10];
        new Test().test();
        logger.info("m:{}",new Test().s);
        logger.info("aa{}",arr[1]);

    }
}
