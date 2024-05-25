package utils;

/**
 * @author jxf
 * @create 2024-05-21 17:01
 */
public class LexUtil {
    public static boolean isLetter(char ch){
        if(Character.isLetter(ch) || ch=='_'){
            return true;
        }
        return false;
    }
    public static boolean isDigit(char ch){
        if(Character.isDigit(ch)){
            return true;
        }
        return false;
    }
    public static boolean isDigit1(char ch){
        if(Character.isDigit(ch)){
            if(ch=='0')
                return false;
            return true;
        }
        return false;
    }
}
