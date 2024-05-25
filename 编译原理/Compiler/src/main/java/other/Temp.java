package other;

import entity.errorWord;
import entity.token;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author jxf
 * @create 2022-05-22 17:06
 */
public class Temp {

    public static int index = 0;  //文件字符串下标
    public static int line = 1;  //文件多少行 用于报错
    public static String str = "";   //文件内容
    public static HashMap<String, Integer> dictionary = null;  //字典
    public static ArrayList<token> list = null;  //内容字典
    public static ArrayList<errorWord> errorList = null;  //报错的内容
    public static String str1 = "";

    private static void initDictionary() {//建立token映射
        dictionary = new HashMap<>();
        list = new ArrayList<>();//tokenlist吧
//        关键字
        //100
        dictionary.put("main", 100);
        dictionary.put("void", 101);
        dictionary.put("char", 102);
        dictionary.put("int", 103);
        dictionary.put("float", 104);
        dictionary.put("const", 105);
        //110
        dictionary.put("if", 111);
        dictionary.put("else", 112);
        dictionary.put("for", 113);
        dictionary.put("do", 114);
        dictionary.put("while", 115);
        dictionary.put("continue", 116);
        dictionary.put("break", 117);
        dictionary.put("return", 118);
        //120
        dictionary.put("string", 120);
        //C语言没有bool值
//        dictionary.put("bool", 121);
//        dictionary.put("true", 122);
//        dictionary.put("false", 122);
//        运算符
        //200
        dictionary.put("(", 201);
        dictionary.put(")", 202);
        dictionary.put("[", 203);
        dictionary.put("]", 204);
        //210
        dictionary.put("+", 211);
        dictionary.put("-", 212);
        dictionary.put("*", 213);
        dictionary.put("/", 214);
        dictionary.put("%", 215);
        //220
        dictionary.put("!", 221);
        dictionary.put("=", 222);
        dictionary.put("<", 223);
        dictionary.put(">", 224);
        dictionary.put("!=", 225);
        dictionary.put("==", 226);
        dictionary.put("<=", 227);
        dictionary.put(">=", 228);
        //230
        dictionary.put("&", 231);
        dictionary.put("|", 232);//为了||,因为是单个扫描的
        dictionary.put("&&", 233);
        dictionary.put("||", 234);

//        dictionary.put(".", 220);
//        dictionary.put("^", 229);
        //240
        dictionary.put("++", 241);
        dictionary.put("--", 242);
        dictionary.put("+=", 243);
        dictionary.put("-=", 244);
        dictionary.put("*=", 245);
        dictionary.put("/=", 246);
        dictionary.put("<<", 247);
        dictionary.put(">>", 248);
//        界符
        dictionary.put("{", 301);
        dictionary.put("}", 302);
        dictionary.put(";", 303);
        dictionary.put(",", 305);
        dictionary.put("'", 306);
        dictionary.put("\"", 307);
        dictionary.put(" ", 308);

        dictionary.put("IDN", 700);
    }

    private static char getNextChar() {
        return str.charAt(index++);//现
    }

    public static void main(String[] args) {
        index = 0;
        line = 1;
        str = //"//if and while,阶乘\n" +
               // "//双重for循环测试，求给定数以内的素数\n" +
                        "\n" +
                        "main(){\n" +
                        "\n" +
                        "    int N = read() ;\n" +
                        "    int count=0,nprime=0,i,j;\n" +
                        "    for(i=2;i<=N;i=i+1) {\n" +
                        "       nprime = 0;\n" +
                        "       for(j=2;j<i;j=j+1) {\n" +
                        "\t   if(i%j == 0) nprime = nprime + 1;\n" +
                        "       }\n" +
                        "       if(nprime == 0) {\n" +
                        "            write(i);\n" +
                        "            count = count + 1;\n" +
                        "        }\n" +
                        "     }\n" +
                        "\n" +
                        "}\n" +
                        "\n ";//测到test4_1

        str = "{a = ass; " +
                "int a,b,c;} " ;
        str = "{ if(a) write(); else const int a=1; } ";
        str = "{a = ass; " +
                "int a,b,c; " +
                "if(a) write(); else const int a=1; } ";
        //loop语句表和复合语句测试
        str = "for(i=0;i<2;i=i+1) {const int a=1,c=5; " +
                "if(a) {int a=a+b;} else void write(); } ";

        str = "while(a) {int a; } ";
        str = "do{int a,b,c;}while(a); ";
        str = "return a+b ; ";
        str = "continue ; ";
        str = "break ; ";
        str = "const int k=2; " +
                "if(a<=b) {while(a>b){int a=1;break;}int a,n;" +
                "for( a=0; a<2; a=a+1) { if(a) const int b=2; } " +
                "} else {const int b=2;" +
                "do{int b;}while(a>3);" +
                "} " +
                "return a+c; " ;
        str = "{const int k=2; " +
                "{a = 3;" +
                "write();} " +
                "if(a<=b) {while(a>b){int a=1;break;}int a,n;" +
                "for( a=0; a<2; a=a+1) { if(a) const int b=2; } " +
                "} else { b=2;" +//修改常量->assexpr
                "do{int b;}while(a>3);" +
                "} " +
                "return a+c;} " ;
        str = "void fun(int a,int b){for(i=1;i<=N;i=i+1)\n" +
                "{\n" +
                "\n" +
                "     sum = sum+i;\n" +
                "    }} ";//文法实现与测试用例不匹配
        str = "int seq(int);\n" +//有参数的测试
                "main()\n" +
                "{\n" +
                "   int n;\n" +
                "   n=read();\n" +
                "   write(seq(n));\n" +
                "}\n" +
                "\n" +
                "int seq(int m) \n" +
                "{\n" +
                "      int s,a,b;\n" +
                "      if(m<=2)\n" +
                "          s=1;\n" +
                "      else{\n" +
               // "//           a=seq(m-1);//这种写法可以正确运行\n" +
               // "//           b=seq(m-2);\n" +
               // "//           s=a+b;\n" +
                "     s=seq(m-1)+seq(m-2);\n" +
                "      }\n" +
                "      return s;\n" +
                "\n" +
                "} ";
        str = "main()\n" +
                "\n" +
                "{\n" +
                "\n" +
                "  int i,factor,n;\n" +
                "\t\n" +
                "  i=0;\n" +
                "\n" +
                "  n=read();\n" +
                "\n" +
                "  if(n<1)\n" +
                "{\n" +
                "\n" +
                "\tfactor=0;\n" +
                "\n" +
                "    }\n" +
                "\t\n" +
                "    else {\n" +
                "\n" +
                "       factor=1;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "  while(i<n)\n" +
                "{\n" +
                "\n" +
                "    i=i+1;\n" +
                "\n" +
                "    factor=factor*i;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "  write(factor);\n" +
                "\n" +
                "} ";

        str = "int a = 1 ;\n" +
                "\n" +
                "int sum(int,int);\n" +
                "\n" +
                "main(){\n" +
                "\n" +
                "    int N = read() ;\n" +
                "\n" +
                "    a = sum(sum(3,4),N) ;\n" +
                "    write(a);\n" +
                "\n" +
                "}\n" +
                "\n" +
                "int sum(int sum_x,int sum_y){\n" +
                "\n" +
                "    int result ;\n" +
                "    result = sum_x + sum_y ;\n" +
                "\n" +
                "    return result ;\n" +
                "\n" +
                "} ";

        str = "main()\n" +
                "\n" +
                "{\n" +
                "\n" +
                "  int i,factor,n;\n" +
                "\t\n" +
                "  i=0;\n" +
                "\n" +
                "  n=read()\n" +//;
                "\n" +
                "  if(n<1)\n" +
                "{\n" +
                "\n" +
                "\tfactor=0;\n" +
                "\n" +
                "    }\n" +
                "\t\n" +
                "    else {\n" +
                "\n" +
                "       factor=1;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "  while(i<n)\n" +
                "{\n" +
                "\n" +
                "    i=i+1;\n" +
                "\n" +
                "    factor=factor*i;\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "  write(factor);\n" +
                "\n" +
                "}\n ";

        //中间代码测试
        //str = "1+b+3-(4*c)/d ";

        //str = "{int a=a+b; } ";
        //str = "for(i=0; i< 2; i=i+1) if(a<b) int a=b; ";
        //str = "write(a,b); ";
        //str = "const int a=2,b=3,c=1; ";
        //str = "a = 3+a; ";

        //System.out.println(str);
//        System.out.println("----------");

        StringBuffer stringBuffer1 = new StringBuffer();//结果字符串
        StringBuffer stringBuffer2 = new StringBuffer();//错误字符串
        initDictionary();
        errorList = new ArrayList<>();

        while(index < str.length()){
            char ch = getNextChar();
            recognize(ch);
        }

        //System.out.println(str1);

        //lex(str1);//str

        System.out.println("----------");
        for (int i = 0; i < list.size(); i++) {//结果输出
            stringBuffer1.append(list.get(i).toString()+"\n");
        }
        System.out.print("str:\n"+ stringBuffer1.toString());//stringBuffer1的\n

        System.out.println("----------");
        if (errorList.size() == 0) {
            stringBuffer2.append("No error!!!");
        } else {
            for (int i = 0; i < errorList.size(); i++) {//词法错误输出
                stringBuffer2.append(i+":"+errorList.get(i).toString() + "\n");
            }
        }
        System.out.print("error:\n"+stringBuffer2.toString());
    }

    //根据word生成token
    private static void getToken(String word){
        if(dictionary.get(word) == null){
            char ch = word.charAt(0);
            int value = 700;
            if(Character.isDigit(ch))
                value = 800;
            if(word.length() == 1)//标识符
            {
                list.add(new token(line, word, value));
            }
            else
                list.add(new token(line, word, value));
        }
        else
            list.add(new token(line, word, dictionary.get(word)));
    }

    private static void error(String word){
        errorList.add(new errorWord(line, word));
    }
    private static void recognize(char ch) {

        if (ch == '\n') {//先处理空白字符
            line++;//行号
            //index++;//字符在总字符串中的位置
            return;
        } else if (ch == ' ' || ch == '\t') {
            //index++;
            return;
        }

//        str1 += ch;
//        String word = "";
//        int state = 0;
//        boolean flag = true;

        while(index<str.length()) {
            String word = "";
            //char ch = str.charAt(index++);
            word += ch;
            if (Character.isLetter(ch) || ch == '_') {
                //index++;
                ch = str.charAt(index++);
                while (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') {
                    word += ch;
                    if (dictionary.containsKey(word)) {
                        index++;//与后面的index--抵消
                        break;
                    }
                    //index++;
                    ch = str.charAt(index++);
                    //ch=getNextChar();
                }
                index--;
                getToken(word);
                //continue;
                break;
            } else if (Character.isDigit(ch)) {
                //index++;
                ch = str.charAt(index++);
                //ch = getNextChar();
                while (Character.isDigit(ch)) {
                    word += ch;
                    //index++;
                    ch = str.charAt(index++);
                    //ch=getNextChar();
                }
                index--;
                getToken(word);
                //continue;
                break;
            } else if (dictionary.containsKey(ch + "")) {//'=' != "="
                //word+=ch;
                char t = str.charAt(index);//向前特判一下
                int t_value=0;
                if(dictionary.containsKey(ch+(t+"")))//左结合性要加括号
                    t_value = dictionary.get(ch+(t+""));
                if (
                    t_value>=241&&t_value<=248 ||
                    t_value>=225&&t_value<=228 ||
                    t_value>=233&&t_value<=234//&&和||
                   )
                {
                    word+=t;
                    index++;
                }
                getToken(word);
                break;
            }
        }
    }

    static boolean prefix(String str){
        int value =  dictionary.get(str);
        if(value>=221&&value<=224)
            return true;
        return false;
    }
    private static void lex(String str){//index的指向比当前字符多1,getNextChar()没有用浓缩后的字符串
        int i=0;
        index = 0;
        while(index<str.length()){
            String word = "";
            char ch = str.charAt(index++);
            word+=ch;
            //System.out.println((i++)+":"+ch);
            if(Character.isLetter(ch)|| ch == '_'){
                //index++;
                ch = str.charAt(index++);
                while(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_'){
                    word+=ch;
                    if(dictionary.containsKey(word)){
                        index++;//与后面的index--抵消
                        break;
                    }
                    //index++;
                    ch = str.charAt(index++);
                    //ch=getNextChar();
                }
                index--;
                getToken(word);
                continue;
            }
            else if(Character.isDigit(ch)){
                //index++;
                ch = str.charAt(index++);
                //ch = getNextChar();
                while (Character.isDigit(ch)){
                    word+=ch;
                    //index++;
                    ch = str.charAt(index++);
                    //ch=getNextChar();
                }
                index--;
                getToken(word);
                continue;
            }
            else if(dictionary.containsKey(ch+"")){//'=' != "="
                //word+=ch;
                getToken(word);
            }
        }

//        while(index < str.length()){
//            System.out.print(str.charAt(index++));
//        }
    }

    void empty(){
        //循环终结状态，就相当于结束一次函数调用了
        /*while(
                state !=  2 && state !=  9 && state != 10 && state != 11 &&
                state != 13 && state != 14 && state != 19 && state != 20 &&
                state != 22 && state != 23 && state != 24 && state != 27
             )
        {
            switch (state){
                case 0://1 3 12 14 15 21 -1
                    if (Character.isLetter(ch)) {
                        state = 1;
                        word += ch;
                        //System.out.println("start:"+index);
                    }//是字母，转向状态1
                    else if (Character.isDigit(ch)) {//ch >= '1' && ch <= '9'
                        state = 3;
                        word += ch;
                    }      //1-9的数字，不包含0,转向状态3
//                    else if (ch == '0') {//数字0
//                        state = 12;                            //转12
//                        word += ch;
//                    }
                    else if (dictionary.get(ch + "") != null && dictionary.get(ch + "") > 300 && dictionary.get(ch + "") < 400) {
                        //token值在300-400之间是界符
                        state = 14;//终结状态
                        word += ch;
                        //index++;//界符都是单字符，调整指针
                    }
                    else if (ch == '/') {//识别到一个运算符’/‘
                        state = 15;
                        word += ch;
                    }
                    else if (dictionary.get(ch + "") != null && dictionary.get(ch + "") > 200 && dictionary.get(ch + "") < 300) {       //除了’/‘的运算符
                        //200-300之间是运算符
                        state = 21;
                        word += ch;
                    } else {
                        state = -1;
                        word += ch;
                    }
                    // 否则调用出错处理，识别其他的单词
                    break;
                case 1:
                    ch = getNextChar();
                    if(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_'){
                        state = 1;
                        word += ch;
                    }else{//多读一个,可能越界
                        //index--;//添加，因为要向前看才能读出，所以回退一步
                        //System.out.println("end:"+index);
                        state = 2;//终结外循环状态
                    }
                    break;
                case 3:
                    ch = getNextChar();
                    if(Character.isDigit(ch)){
                        state = 3;
                        word += ch;
                    }
                    else if(Character.isLetter(ch)){
                        state = -1;
                        word += ch;
                    }
                    else{
                        //index--;//添加，因为要向前看才能读出，所以回退一步//因为index后加所以指向的是预读取字符，不用回移
                        if(ch != ' ' || ch != '\t')
                            index--;
                        state = 11;//终结外循环状态
                        flag = false;//不需要额外操作tokens(list变量)了
                        list.add(new token(line, word, 400));
                    }
                    break;
                case 15:
                    ch = getNextChar();//检查/后面第一个字符
                    if(ch == '/'){
                        state = 16;
                        word = "";
                    }
                    else if(ch == '*'){
                        state = 17;
                        word = "";
                    }
                    else{//单个除号，"/a"或"/ "
                        index--;
                        state = 20;
                    }
                    break;
                case 16://单行注释
                    ch = getNextChar();
                    if ((ch + "").equals("\n")) { //读到直至回车换行符  直接退出
                        return;
                    }
                    break;
                case 17://多行注释内容+*
                    ch = getNextChar();
                    if(ch == '*'){
                        state =18;
                    }
                    break;
                case 18://多行注释结束"/"
                    ch = getNextChar();
                    if(ch == '/'){
                        state = 19;
                        index++;//直接return,操作指针
                        return;
                    }
                    break;
                case 21://运算符
                    //System.out.println(ch);
                    ch = getNextChar();
                    if(dictionary.containsKey(ch+"")){
                        word += ch;
                        state = 23;
                    }else{
                        //index--;//出错退一格
                        getToken(word);
                        return ;
                        //state = -1;
                    }
                    if(word.equals(")") || word.equals("(")){
                        state = 23;//循环终结状态
                    }
                    else if((dictionary.get(ch+"")== null) || ch == '(' || ch == ')'){
                        state = 23;
                    }
                    else if(dictionary.get(ch+"") > 200 && dictionary.get(ch+"") < 300){
                        word += ch;
                        state =22;//循环终结状态
                        char three = str.charAt(index + 1);
                        if(three > 200 && three < 300 && three != '(' && three != ')'){
                            state = -1;
                            word += ch;
                        }
                        index++;
                    }
                    else{
                        state = 23;
                    }
                    break;
                //错误处理
                case -1:
                    ch = getNextChar();
                    if (dictionary.get(ch + "") == null || ch == '.') {  //不是界符和运算符  但是是‘.’也继续读
                        if (ch == ' ' || ch == '\t' || ch == '\n')
                            state = -2;   //结束读取错误的字符串
                        else
                            word += ch;       //读取错误的字符串
                    } else
                        state = -2;
                    break;
                case -2:
                    error(word);
                    state = 0;   //归0
                    flag = false;
                    break;
            }
        }
        if(flag)
            getToken(word);*/
    }
}
