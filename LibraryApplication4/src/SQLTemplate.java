public class SQLTemplate {
    public static String sqlSelect(String value){ return "select * from "+value; }
    public static String setValue(String value){ return "'"+value+"'"; }
}