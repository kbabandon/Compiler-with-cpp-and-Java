package gui;
//package com.compile;

import entity.token;
import other.Temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jxf
 * @create 2024-05-20 16:15
 */
public class demo{
    static List<token> list = Temp.list;
    static List<quad> quadList = new ArrayList<>();//四元式表
    static int index = 0;
    static int size = 0;
    private static int sub=1;

    //临时变量表
    private static String[] temps = {"t1", "t2", "t3", "t4", "t5",
            "t6", "t7", "t8", "t9", "t10"};
    private static Stack<String> var_stack = new Stack<>();
    private static Stack<String> op_stack = new Stack<>();
    private static int tempIndex = 0;

    void match(int index){
        token token1 = list.get(index);
        if(token1.getKey().equals("IDN")){
            ;
        }
    }

    static void getNext(){
        if(index<size-1)
            index++;
    }

    static class emp{

        static void E(){
            T();
            Eprime();
        }

        static void Eprime(){
            if(list.get(index).getKey().equals("+")){
                getNext();//index++;
                T();
                Eprime();
            }
        }

        static void T(){
            F();
            Tprime();
        }

        static void Tprime(){
            if (list.get(index).getKey().equals("*")){
                getNext();//index++;
                F();
                Tprime();
            }
        /*else if(list.get(index).getValue()==700){
            index++;
        }
        else{
            error();
        }*/
        }

        static void F(){
            if(list.get(index).getKey().equals("(")){
                getNext();//index++;
                E();
                if(list.get(index).getKey().equals(")")){
                    getNext();//index++;
                }
                else{
                    error(list.get(index).getKey());
                }
            }
            else if(list.get(index).getValue()==700){
                getNext();//index++;
            }
            else{
                error(list.get(index).getKey());
            }
        }

    }

    private static void error(String error) {
//        System.out.println("Error!!!");
        System.out.println("Error: " + error);
        System.exit(-1);
    }

    public static void main(String[] args) {
        Temp.main(args);
        list = Temp.list;
        size = list.size();
        //System.out.println(size);
        //System.out.println(list.size());
        System.out.println();
//        System.out.println("aexpr start");
//        E();
//        System.out.println("aexpr end");
//        System.out.println("declaration start");
//        type();
//        System.out.println("declaration end");
//          System.out.println("assexpr start");
//          assexpr();
//          System.out.println("assexpr end");
//            System.out.println("aloexpr start");
//            aloexpr();
//            System.out.println("aloexpr end");
//            System.out.println("relaexpr start");
//            relaexpr();
//            System.out.println("relaexpr end");
        //System.out.println("start");
        //boolexpr();
        //var_declaration();
        //relaexpr();
        //boolexpr();
        //expr();
        //sentence();
        //fors();
        //ifs();
        //function_revoke();
        //var_declaration();
        //function_declaration();
        //dataprocess_sentence();
        //declaration_sentence();
        //sentence_table();
        //compound_sentence();

        //loop_sentence();
        //fors();
        //loop_sentence_table();
        //loop_compound_sentence();
        //loop_sentence();//只有一次
        //sentence();//没有扩展性
        //compound_sentence();
        //whiles();
        //do_whiles();
        //returns();
        //breaks();
        //continues();
        //breaks();
        //fors();
        //loop_ifs();

        //分割线，最后的整合
        //function_definition();
        program();
        //aloexpr();
        //System.out.println("end");
        //声明语句包";"号,数据处理语句不包

        //getquad();
        /*while(var_stack.size()!=1){//还有待考虑终止
            String op = op_stack.pop();
            if(op_stack.peek().equals(")")){
                String num1 = var_stack.pop();
                String num2 = var_stack.pop();
                //String op_prime = op_stack.pop();
            }
            String num1 = var_stack.pop();
            String num2 = var_stack.pop();
            String temp = newTemp();
            gencode(op, num1, num2, temp);
            //String result = Integer.parseInt(num1)+Integer.parseInt(num2)+"";
            var_stack.push(temp);
        }*/

        /*while (!var_stack.isEmpty()){
            System.out.println(var_stack.pop());
        }
        System.out.println("-------------");
        while (!op_stack.isEmpty()){
            System.out.println(op_stack.pop());
        }*/

    }

    private static void getquad(){
        if(var_stack.size()!=1){//还有待考虑终止
            String op = op_stack.pop();
            if(!op_stack.isEmpty()&&op_stack.peek().equals(")")){
                op_stack.pop();
                String temp = var_stack.pop();
                getquad();
                var_stack.push(temp);
                //String op_prime = op_stack.pop();
            }
            if(!op_stack.isEmpty()&&op_stack.peek().equals("(")){
                op_stack.pop();
                String num1 = var_stack.pop();
                String num2 = var_stack.pop();
                String temp = newTemp();
                gencode(op, num2, num1, temp);//更换一下次序
                var_stack.push(temp);
                return;
            }
            String num1 = var_stack.pop();
            String num2 = var_stack.pop();
            String temp = newTemp();
            gencode(op, num2, num1, temp);
            //String result = Integer.parseInt(num1)+Integer.parseInt(num2)+"";
            var_stack.push(temp);

            getquad();//递归
        }
    }

    private static String newTemp() {
        tempIndex++;
        return "t"+tempIndex;
    }

    private static void gencode(String op, String num1, String num2, String temp) {
        System.out.println("("+ op + ", " + num1 + ", " + num2 + ", " + temp + ")");
    }

    private static void gencode(String op) {
        //System.out.println("("+ op + ", " + num1 + ", " + num2 + ", " + temp + ")");
    }

    //<type><def> --> <type> ID <init>
    //<init> --> ASSIGN <expr> | " "
//    static void type(){
//        if(list.get(index).getKey().equals("int")){
//            getNext();
//            def_data();
//        }
//        else{
//            error(list.get(index).getKey());
//        }
//    }

    static void def_data(){
        if(list.get(index).getValue()==700){//IDN
            getNext();
            init();
        }
        else{
            error(list.get(index).getKey());
        }
    }

    private static void init() {
        if(list.get(index).getKey().equals("=")) {
            getNext();
            expr();
        }
    }

//    private static void expr() {
//        if(list.get(index).getValue()==700){
//            getNext();
//            assexpr();
//        }
//        else{
//            error(list.get(index).getKey());
//        }
//    }

    private static void print_sub(){
        for(int i=0; i<sub; i++){
            System.out.print("--");
        }
    }

    private static void expr(){
        if (index+1<list.size()&&list.get(index+1).getKey().equals("=")){
            //getNext();
            sub++;
            assexpr();
            sub--;
        }
        else{
            sub++;
            boolexpr();
            sub--;
        }
    }

    //赋值表达式
    private static void assexpr() {
        print_sub();
        System.out.println("赋值表达式");

        if(list.get(index).getValue()==700) {//IDN
            getNext();
            if(list.get(index).getKey().equals("=")){
                getNext();
                //sub++;
                expr();
                //sub--;
//                if(list.get(index).getValue()==800){//表达式的暂时代替
//                    getNext();
//                }
//                else error(list.get(index).getKey());//此时指针没移动报上一个正确的字符
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());

        print_sub();
        System.out.println("赋值表达式");
    }

    //算术表达式
    private static void aloexpr() {
        print_sub();
        System.out.println("算术表达式");

        item();
        item_prime();

        print_sub();
        System.out.println("算术表达式");
    }

    private static void item() {
        factor();
        factor_prime();
    }

    private static void item_prime() {
        if (list.get(index).getKey().equals("+")||list.get(index).getKey().equals("-")){
            op_stack.push(list.get(index).getKey());
            getNext();
            sub++;
            aloexpr();
            sub--;
        }
        //空
    }

    private static void factor() {
        if(list.get(index).getKey().equals("(")){
            op_stack.push(list.get(index).getKey());
            getNext();
            //aloexpr();//换成关系表达式吗?
            sub++;
            relaexpr();
            sub--;
            if(list.get(index).getKey().equals(")")){
                op_stack.push(list.get(index).getKey());
                getNext();
            }
            else
                error(list.get(index).getKey());
        }
        else if(list.get(index).getValue()==800){//数字常量
            var_stack.push(list.get(index).getKey());
            getNext();
        }
        else if(list.get(index).getValue()==700){//标识符
            if(index+1<list.size()&&list.get(index+1).getKey().equals("("))//后添加,只看不读
            {
                function_revoke();
            }
            else{
                var_stack.push(list.get(index).getKey());
                getNext();
            }
        }
        else error(list.get(index).getKey());
    }

    private static void factor_prime() {
        if (list.get(index).getKey().equals("*")||list.get(index).getKey().equals("/")||list.get(index).getKey().equals("%")){
            op_stack.push(list.get(index).getKey());
            getNext();
            item();
        }
        //空
    }

    //关系表达式
    private static void relaexpr(){
        print_sub();
        System.out.println("关系表达式");

        sub++;
        aloexpr();
        sub--;
        String op = list.get(index).getKey();
        if(op.equals("<")||op.equals(">")||op.equals(">=")||op.equals("<=")||op.equals("!=")||op.equals("==")){//==添加
            getNext();
            sub++;
            aloexpr();
            sub--;
        }
        //因为算术表达式的情况，外加括号的情况
        //else error(list.get(index).getKey());

        print_sub();
        System.out.println("关系表达式");
    }

    //函数调用，后添加
    private static void function_revoke(){
        if(list.get(index).getValue()==700){//700
            getNext();
            if (list.get(index).getKey().equals("(")){
                getNext();
                parameter_table();
                if(list.get(index).getKey().equals(")")){
                    getNext();
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void parameter_table() {
        //if(index+1 < list.size()&&
        if(!list.get(index).getKey().equals(")")){//有参数的函数调用
            parameter();
        }
        //空
    }

    private static void parameter() {
        expr();
        parameter_prime();
        //本来的空
    }

    private static void parameter_prime(){
        if (list.get(index).getKey().equals(",")){//可以独立出去
            getNext();
            parameter();
        }
        //空
    }

    //布尔表达式
    private static void boolexpr(){
        bool_item();
        bool_item_prime();
    }

//    private static void boolexpr_prime() {
//        if(list.get(index).getKey().equals("||")){
//            boolexpr();
//        }
//    }

    private static void bool_item() {
        bool_factor();
        bool_factor_prime();
    }

    private static void bool_item_prime() {
        //String op = list.get(index).getKey();
        if(list.get(index).getKey().equals("||")){//少了getkey(最后测试)
            getNext();
            sub++;
            boolexpr();
            sub--;
        }
        //空
    }

    private static void bool_factor() {//待写,将关系表达式展开的答案
        if(list.get(index).getKey().equals("!")){
            sub++;
            boolexpr();
            sub--;
        }
        else{
            sub++;
            aloexpr();
            sub--;
            aloexpr_prime();
        }
    }

    private static void aloexpr_prime(){//在布尔因子中使用
        String op = list.get(index).getKey();
        if(op.equals("<")||op.equals(">")||op.equals(">=")||op.equals("<=")||op.equals("!=")||op.equals("==")){//添加==
            getNext();
            sub++;
            aloexpr();
            sub--;
        }//空
    }

    private static void bool_factor_prime() {
        if(list.get(index).getKey().equals("&&")){
            getNext();
            bool_item();
        }
        //空
    }

    //常量声明
    private static void const_declaration(){
        if (list.get(index).getKey().equals("const")){
            getNext();
            type();
            const_table();
        }
        else error(list.get(index).getKey());
    }

    private static void type() {
        if (list.get(index).getKey().equals("int")){
            getNext();
        }
        else error(list.get(index).getKey());
    }

    private static void const_table() {
        if(list.get(index).getValue()==700){//IDN
            getNext();
            if(list.get(index).getKey().equals("=")){
                getNext();
                if(list.get(index).getValue()==800) {//常量
                    getNext();
                    const_table_prime();
                }
                else{
                    error(list.get(index).getKey());
                }
            }
            else{
                error(list.get(index).getKey());
            }
        }
        else error(list.get(index).getKey());
    }

    private static void const_table_prime() {//<标识符> = <常量>
        if(list.get(index).getKey().equals(";")){
            getNext();
        }
        else if(list.get(index).getKey().equals(",")){
            getNext();
            const_table();
        }
        else error(list.get(index).getKey());
    }

    //变量声明
    private static void var_declaration(){
        type();
        var_table();
    }

    private static void var_table() {
        single_var_declaration();
        var_table_prime();
    }

    private static void var_table_prime() {
        if(list.get(index).getKey().equals(";")){
            getNext();
        }
        else if(list.get(index).getKey().equals(",")){
            getNext();
            var_table();
        }
        else error(list.get(index).getKey());
    }

    private static void single_var_declaration() {
        if(list.get(index).getValue()==700){//IDN
            getNext();
            single_var_declaration_prime();
        }
        else error(list.get(index).getKey());
    }

    private static void single_var_declaration_prime() {
        if (list.get(index).getKey().equals("=")){
            getNext();//需要移动
            expr();//需要修改,改好了
        }
        //空
    }

    //函数声明
    private static void function_declaration(){
        function_type();//函数类型-void and int
        if(list.get(index).getValue()==700){
            getNext();
            if (list.get(index).getKey().equals("(")){
                getNext();
                //
                declaration_parameter_table();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    //还差分号
                    if(list.get(index).getKey().equals(";")){
                        getNext();
                    }
                    else error(list.get(index).getKey());
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void function_type(){//函数类型
        if (list.get(index).getKey().equals("int") ||
                list.get(index).getKey().equals("void")){
            getNext();
        }
        else error(list.get(index).getKey());
    }

    //???->OK
    private static void declaration_parameter_table(){
        //if (index+1<list.size()&&
        //有参数的情况
        if(!list.get(index).getKey().equals(")")){//只看不读,index已经移动
            declaration_parameter();
        }
        //为空时没有参数
        //空
    }

    private static void declaration_parameter() {
        type();//变量类型
        declaration_parameter_prime();
    }

    private static void declaration_parameter_prime() {
        if(list.get(index).getKey().equals(",")){
            getNext();//这儿没移动
            declaration_parameter();
        }
        //空
    }

    //if语句
    private static void ifs(){
        if(list.get(index).getKey().equals("if")){
            getNext();
            if(list.get(index).getValue()==201) {//"("
                getNext();
                expr();
                if (list.get(index).getValue()==202) {//")"
                    getNext();
                    //block();
                    sentence();
                    //loop_sentence();//修改
                    elses();
                }
                else error(list.get(index).getKey());
            }
            else{
                error(list.get(index).getKey());
            }
        }
        else error(list.get(index).getKey());
    }

    private static void elses() {
        if(list.get(index).getKey().equals("else")){
            getNext();
//            block();
            sentence();
        }
        //空
    }

    private static void fors(){
        if(list.get(index).getKey().equals("for")){
            getNext();
            if (list.get(index).getKey().equals("(")){
                getNext();
                expr();
                if (list.get(index).getKey().equals(";")){//1
                    getNext();
                    expr();
                    if (list.get(index).getKey().equals(";")){
                        getNext();
                        expr();
                        if (list.get(index).getKey().equals(")")){
                            getNext();
                            loop_sentence();//循环语句不同于if
                        }
                        else error(list.get(index).getKey());
                    }
                    else error(list.get(index).getKey());
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void whiles(){
        if(list.get(index).getKey().equals("while")){
            getNext();
            if(list.get(index).getKey().equals("(")){
                getNext();
                expr();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    loop_sentence();
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void do_whiles(){
        if (list.get(index).getKey().equals("do")){
            getNext();
            loop_compound_sentence();
            if (list.get(index).getKey().equals("while")){
                getNext();
                if (list.get(index).getKey().equals("(")){
                    getNext();
                    expr();
                    if (list.get(index).getKey().equals(")")){
                        getNext();
                        if (list.get(index).getKey().equals(";")){
                            getNext();
                        }
                        else error(list.get(index).getKey());
                    }
                    else error(list.get(index).getKey());
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void block() {//不用了
        if(list.get(index).getKey().equals("{")){
            getNext();
            expr();//暂时的
            if(list.get(index).getKey().equals("}")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void sentence(){//这儿需要修改
        if(list.get(index).getKey().equals("int")||
                list.get(index).getKey().equals("const")||
                list.get(index).getKey().equals("void")//添加函数声明
        ){
            declaration_sentence();
        }
        else if(list.get(index).equals("{")){//复合语句
            compound_sentence();
        }
        else{//延迟报错
            execution_sentence();
        }
    }

    private static void execution_sentence() {
        //数据处理语句：标识符开头就是数据处理语句
        if(list.get(index).getValue()==700){//标识符，只看没读，统合原因
            dataprocess_sentence();//升级换代
//           assexpr();
//           if(list.get(index).getValue()==303){//;
//               getNext();
//           }
//           else error(list.get(index).getKey());
        }
        else if(list.get(index).getKey().equals("if")){//控制语句
            ifs();
        }
        else if(list.get(index).getKey().equals("for")){
            fors();
        }
        else if(list.get(index).getKey().equals("while")){
            whiles();
        }
        else if(list.get(index).getKey().equals("do")){
            do_whiles();
        }
        else if(list.get(index).getKey().equals("return")){//控制语句end
            returns();
        }
        else if(list.get(index).getKey().equals("{")){//复合语句
            compound_sentence();
        }
        else error(list.get(index).getKey());
    }

    private static void declaration_sentence() {
        //统合只看不读
        if(list.get(index).getKey().equals("int")){//不用放void
            if(index+2<list.size()&&list.get(index+2).getKey().equals("(")) {//函数声明int开头的情况
                function_declaration();
            }
            else var_declaration();//延迟报错
        }
        else if(list.get(index).getKey().equals("const")){
            const_declaration();
        }
        else if(list.get(index).getKey().equals("void")){//函数申明还需处理int开头
            function_declaration();
        }
        //空
    }

    //粗略调试了一下
    private static void dataprocess_sentence(){
        if(index+1<list.size()){//保证查找下标不越界
            //先看再读
            if(list.get(index+1).getKey().equals("(")){//函数调用
                function_revoke();
                //";"号单独处理
                if(list.get(index).getKey().equals(";"))
                    getNext();
                else error(list.get(index).getKey());
            }
            else if (list.get(index+1).getKey().equals("=")){//
                assexpr();
                if(list.get(index).getKey().equals(";"))
                    getNext();
                else error(list.get(index).getKey());
                //;
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void compound_sentence(){
        if (list.get(index).getKey().equals("{")){
            getNext();
            sentence_table();
            if(list.get(index).getKey().equals("}")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void sentence_table(){
        sentence();
        sentence_table_prime();
    }

    private static void sentence_table_prime() {
        //后半复制后面
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){//???
            //sentence();//修改
            sentence_table();
        }
        //空
    }


    //循环语句
    private static void loop_sentence(){//需不需要加数据处理语句
        if(list.get(index).getKey().equals("int")||
                list.get(index).getKey().equals("const")||
                list.get(index).getKey().equals("void")//增加
        ){
            declaration_sentence();
        }
        else if(list.get(index).getKey().equals("{")){
            loop_compound_sentence();
        }
        else{//延迟报错
            loop_execution_sentence();
        }
    }

    private static void loop_execution_sentence() {
//        if(list.get(index).getValue()==700){//标识符
//            assexpr();
//            if(list.get(index).getValue()==303){//;
//                getNext();
//            }
//            else error(list.get(index).getKey());
//        }
//        else
        //数据处理语句：标识符开头就是数据处理语句
        if(list.get(index).getValue()==700){//标识符，只看没读，统合原因
            dataprocess_sentence();//升级换代
//           assexpr();
//           if(list.get(index).getValue()==303){//;
//               getNext();
//           }
//           else error(list.get(index).getKey());
        }
        else if(list.get(index).getKey().equals("if")){
            loop_ifs();
        }
        else if(list.get(index).getKey().equals("for")){
            fors();
        }
        else if(list.get(index).getKey().equals("while")){
            whiles();
        }
        else if(list.get(index).getKey().equals("do")){
            do_whiles();
        }
        else if(list.get(index).getKey().equals("return")){
            returns();
        }
        else if(list.get(index).getKey().equals("continue")){
            continues();
        }
        else if(list.get(index).getKey().equals("break")){
            breaks();
        }
        else error(list.get(index).getKey());//这儿会有if{}错
    }

    private static void returns() {
        if(list.get(index).getKey().equals("return")){
            getNext();
            return_prime();
        }
        else error(list.get(index).getKey());

    }

    private static void return_prime() {
        if(list.get(index).getKey().equals(";")){
            getNext();
        }
        else{//推迟报错
            expr();
            if(list.get(index).getKey().equals(";")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        //else error(list.get(index).getKey());
    }

    private static void continues() {
        if(list.get(index).getKey().equals("continue")){
            getNext();
            if(list.get(index).getKey().equals(";")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void breaks() {
        if(list.get(index).getKey().equals("break")){
            getNext();
            if(list.get(index).getKey().equals(";")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void loop_ifs(){
        if(list.get(index).getKey().equals("if")){
            getNext();
            if(list.get(index).getValue()==201) {//"("
                getNext();
                expr();
                if (list.get(index).getValue()==202) {//")"
                    getNext();
                    //block();
                    loop_sentence();
                    loop_elses();//
                }
                else error(list.get(index).getKey());
            }
            else{
                error(list.get(index).getKey());
            }
        }
        else error(list.get(index).getKey());
    }

    private static void loop_elses() {
        if(list.get(index).getKey().equals("else")){
            getNext();
//            block();
            loop_sentence();
        }
        //空
    }

    private static void loop_compound_sentence() {
        if (list.get(index).getKey().equals("{")){
            getNext();
//            sentence_table();
            loop_sentence_table();//修改
            if(list.get(index).getKey().equals("}")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void loop_sentence_table() {
        loop_sentence();
        loop_sentence_table_prime();
    }

    private static void loop_sentence_table_prime() {
        //增加了一个条件:if(){}else
        // &&
        //没有结束且没有结束复合语句
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){//???
            //sentence();//修改
            loop_sentence_table();
        }
        //空
    }

    //Sample语言中的函数
    private static void function_definition(){
        function_type();
        if(list.get(index).getValue()==700){//IDN
            getNext();
            if(list.get(index).getKey().equals("(")){
                getNext();
                //添加
                function_definition_parameter_table();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    compound_sentence();//添加
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void function_definition_parameter_table(){
        //type();
        if(list.get(index).getKey().equals("int")){//IDN,只看不读
            function_definition_parameter();
        }
        //空
    }

    private static void function_definition_parameter() {
        type();
        if(list.get(index).getValue()==700){
            getNext();
            function_definition_parameter_prime();
        }
        else  error(list.get(index).getKey());
    }

    private static void function_definition_parameter_prime() {
        if(list.get(index).getKey().equals(",")){
            getNext();
            function_definition_parameter();
        }
        //空
    }

    //最后的一步
    private static void function_block() {
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){
            function_definition();
            function_block();
        }
        //空
    }

    private static void program() {
        //declaration_sentence();
        System.out.println("start");
        declaration_sentence_prime();
        if(list.get(index).getKey().equals("main")){
            getNext();
            if(list.get(index).getKey().equals("(")){
                getNext();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    compound_sentence();
                    function_block();
                    System.out.println("end");
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void declaration_sentence_prime(){
        if(!list.get(index).getKey().equals("main")){
            declaration_sentence();
            declaration_sentence_prime();//递归-扩展性
        }
        //空
    }
}

class quad{
    String op;
    String arg1;
    String arg2;
    String result;

    public quad() {
    }

    public quad(String op, String arg1, String arg2, String result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "gui.quad{" +
                "op='" + op + '\'' +
                ", arg1='" + arg1 + '\'' +
                ", arg2='" + arg2 + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

class demo1 {
    static final int pos = 0;
    static String s = "i+(((((i*i*i*(i+i+(i/i))+i/i)))))$";

    static void eat(){
        s = s.substring(1);
    }

    static void error(){
        System.out.println("failed to match!");
        System.exit(1);
    }

    static void E(){
        switch (s.charAt(pos)){
            case '$': break;
            case '(':
            case 'i': T(); Eprime(); break;
            default: error();
        }
    }

    static void Eprime(){
        switch (s.charAt(pos)){
            case '$': break;
            case '+':
            case '-': eat(); T(); Eprime(); break;
            case ')': break;
            default: error();
        }
    }

    static void T(){
        switch (s.charAt(pos)){
            case '$': break;
            case '(':
            case 'i': F(); Tprime(); break;
            default:error();
        }
    }

    static void Tprime(){
        switch (s.charAt(pos)){
            case '$': break;
            case '+':
            case '-':
            case ')': break;
            case '*':
            case '/': eat(); F(); Tprime(); break;
            default: error();
        }
    }

    static void F(){
        switch (s.charAt(pos)){
            case '$': break;
            case '(': eat(); E();
                if(s.charAt(pos) == ')') {eat(); break;}
                else error();
                break;
            case 'i':
                eat();
                break;
            default: error();
        }
    }

    public static void main(String[] args) {
        E();
        System.out.println("fianlly s is " + s);
        System.out.println("accepted!");
        //return 0;
    }
}

