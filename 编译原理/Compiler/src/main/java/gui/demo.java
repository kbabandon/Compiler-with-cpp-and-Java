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
    static List<quad> quadList = new ArrayList<>();//��Ԫʽ��
    static int index = 0;
    static int size = 0;
    private static int sub=1;

    //��ʱ������
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
        //loop_sentence();//ֻ��һ��
        //sentence();//û����չ��
        //compound_sentence();
        //whiles();
        //do_whiles();
        //returns();
        //breaks();
        //continues();
        //breaks();
        //fors();
        //loop_ifs();

        //�ָ��ߣ���������
        //function_definition();
        program();
        //aloexpr();
        //System.out.println("end");
        //��������";"��,���ݴ�����䲻��

        //getquad();
        /*while(var_stack.size()!=1){//���д�������ֹ
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
        if(var_stack.size()!=1){//���д�������ֹ
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
                gencode(op, num2, num1, temp);//����һ�´���
                var_stack.push(temp);
                return;
            }
            String num1 = var_stack.pop();
            String num2 = var_stack.pop();
            String temp = newTemp();
            gencode(op, num2, num1, temp);
            //String result = Integer.parseInt(num1)+Integer.parseInt(num2)+"";
            var_stack.push(temp);

            getquad();//�ݹ�
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

    //��ֵ���ʽ
    private static void assexpr() {
        print_sub();
        System.out.println("��ֵ���ʽ");

        if(list.get(index).getValue()==700) {//IDN
            getNext();
            if(list.get(index).getKey().equals("=")){
                getNext();
                //sub++;
                expr();
                //sub--;
//                if(list.get(index).getValue()==800){//���ʽ����ʱ����
//                    getNext();
//                }
//                else error(list.get(index).getKey());//��ʱָ��û�ƶ�����һ����ȷ���ַ�
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());

        print_sub();
        System.out.println("��ֵ���ʽ");
    }

    //�������ʽ
    private static void aloexpr() {
        print_sub();
        System.out.println("�������ʽ");

        item();
        item_prime();

        print_sub();
        System.out.println("�������ʽ");
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
        //��
    }

    private static void factor() {
        if(list.get(index).getKey().equals("(")){
            op_stack.push(list.get(index).getKey());
            getNext();
            //aloexpr();//���ɹ�ϵ���ʽ��?
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
        else if(list.get(index).getValue()==800){//���ֳ���
            var_stack.push(list.get(index).getKey());
            getNext();
        }
        else if(list.get(index).getValue()==700){//��ʶ��
            if(index+1<list.size()&&list.get(index+1).getKey().equals("("))//�����,ֻ������
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
        //��
    }

    //��ϵ���ʽ
    private static void relaexpr(){
        print_sub();
        System.out.println("��ϵ���ʽ");

        sub++;
        aloexpr();
        sub--;
        String op = list.get(index).getKey();
        if(op.equals("<")||op.equals(">")||op.equals(">=")||op.equals("<=")||op.equals("!=")||op.equals("==")){//==���
            getNext();
            sub++;
            aloexpr();
            sub--;
        }
        //��Ϊ�������ʽ�������������ŵ����
        //else error(list.get(index).getKey());

        print_sub();
        System.out.println("��ϵ���ʽ");
    }

    //�������ã������
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
        if(!list.get(index).getKey().equals(")")){//�в����ĺ�������
            parameter();
        }
        //��
    }

    private static void parameter() {
        expr();
        parameter_prime();
        //�����Ŀ�
    }

    private static void parameter_prime(){
        if (list.get(index).getKey().equals(",")){//���Զ�����ȥ
            getNext();
            parameter();
        }
        //��
    }

    //�������ʽ
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
        if(list.get(index).getKey().equals("||")){//����getkey(������)
            getNext();
            sub++;
            boolexpr();
            sub--;
        }
        //��
    }

    private static void bool_factor() {//��д,����ϵ���ʽչ���Ĵ�
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

    private static void aloexpr_prime(){//�ڲ���������ʹ��
        String op = list.get(index).getKey();
        if(op.equals("<")||op.equals(">")||op.equals(">=")||op.equals("<=")||op.equals("!=")||op.equals("==")){//���==
            getNext();
            sub++;
            aloexpr();
            sub--;
        }//��
    }

    private static void bool_factor_prime() {
        if(list.get(index).getKey().equals("&&")){
            getNext();
            bool_item();
        }
        //��
    }

    //��������
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
                if(list.get(index).getValue()==800) {//����
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

    private static void const_table_prime() {//<��ʶ��> = <����>
        if(list.get(index).getKey().equals(";")){
            getNext();
        }
        else if(list.get(index).getKey().equals(",")){
            getNext();
            const_table();
        }
        else error(list.get(index).getKey());
    }

    //��������
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
            getNext();//��Ҫ�ƶ�
            expr();//��Ҫ�޸�,�ĺ���
        }
        //��
    }

    //��������
    private static void function_declaration(){
        function_type();//��������-void and int
        if(list.get(index).getValue()==700){
            getNext();
            if (list.get(index).getKey().equals("(")){
                getNext();
                //
                declaration_parameter_table();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    //����ֺ�
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

    private static void function_type(){//��������
        if (list.get(index).getKey().equals("int") ||
                list.get(index).getKey().equals("void")){
            getNext();
        }
        else error(list.get(index).getKey());
    }

    //???->OK
    private static void declaration_parameter_table(){
        //if (index+1<list.size()&&
        //�в��������
        if(!list.get(index).getKey().equals(")")){//ֻ������,index�Ѿ��ƶ�
            declaration_parameter();
        }
        //Ϊ��ʱû�в���
        //��
    }

    private static void declaration_parameter() {
        type();//��������
        declaration_parameter_prime();
    }

    private static void declaration_parameter_prime() {
        if(list.get(index).getKey().equals(",")){
            getNext();//���û�ƶ�
            declaration_parameter();
        }
        //��
    }

    //if���
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
                    //loop_sentence();//�޸�
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
        //��
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
                            loop_sentence();//ѭ����䲻ͬ��if
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

    private static void block() {//������
        if(list.get(index).getKey().equals("{")){
            getNext();
            expr();//��ʱ��
            if(list.get(index).getKey().equals("}")){
                getNext();
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void sentence(){//�����Ҫ�޸�
        if(list.get(index).getKey().equals("int")||
                list.get(index).getKey().equals("const")||
                list.get(index).getKey().equals("void")//��Ӻ�������
        ){
            declaration_sentence();
        }
        else if(list.get(index).equals("{")){//�������
            compound_sentence();
        }
        else{//�ӳٱ���
            execution_sentence();
        }
    }

    private static void execution_sentence() {
        //���ݴ�����䣺��ʶ����ͷ�������ݴ������
        if(list.get(index).getValue()==700){//��ʶ����ֻ��û����ͳ��ԭ��
            dataprocess_sentence();//��������
//           assexpr();
//           if(list.get(index).getValue()==303){//;
//               getNext();
//           }
//           else error(list.get(index).getKey());
        }
        else if(list.get(index).getKey().equals("if")){//�������
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
        else if(list.get(index).getKey().equals("return")){//�������end
            returns();
        }
        else if(list.get(index).getKey().equals("{")){//�������
            compound_sentence();
        }
        else error(list.get(index).getKey());
    }

    private static void declaration_sentence() {
        //ͳ��ֻ������
        if(list.get(index).getKey().equals("int")){//���÷�void
            if(index+2<list.size()&&list.get(index+2).getKey().equals("(")) {//��������int��ͷ�����
                function_declaration();
            }
            else var_declaration();//�ӳٱ���
        }
        else if(list.get(index).getKey().equals("const")){
            const_declaration();
        }
        else if(list.get(index).getKey().equals("void")){//�����������账��int��ͷ
            function_declaration();
        }
        //��
    }

    //���Ե�����һ��
    private static void dataprocess_sentence(){
        if(index+1<list.size()){//��֤�����±겻Խ��
            //�ȿ��ٶ�
            if(list.get(index+1).getKey().equals("(")){//��������
                function_revoke();
                //";"�ŵ�������
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
        //��븴�ƺ���
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){//???
            //sentence();//�޸�
            sentence_table();
        }
        //��
    }


    //ѭ�����
    private static void loop_sentence(){//�費��Ҫ�����ݴ������
        if(list.get(index).getKey().equals("int")||
                list.get(index).getKey().equals("const")||
                list.get(index).getKey().equals("void")//����
        ){
            declaration_sentence();
        }
        else if(list.get(index).getKey().equals("{")){
            loop_compound_sentence();
        }
        else{//�ӳٱ���
            loop_execution_sentence();
        }
    }

    private static void loop_execution_sentence() {
//        if(list.get(index).getValue()==700){//��ʶ��
//            assexpr();
//            if(list.get(index).getValue()==303){//;
//                getNext();
//            }
//            else error(list.get(index).getKey());
//        }
//        else
        //���ݴ�����䣺��ʶ����ͷ�������ݴ������
        if(list.get(index).getValue()==700){//��ʶ����ֻ��û����ͳ��ԭ��
            dataprocess_sentence();//��������
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
        else error(list.get(index).getKey());//�������if{}��
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
        else{//�Ƴٱ���
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
        //��
    }

    private static void loop_compound_sentence() {
        if (list.get(index).getKey().equals("{")){
            getNext();
//            sentence_table();
            loop_sentence_table();//�޸�
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
        //������һ������:if(){}else
        // &&
        //û�н�����û�н����������
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){//???
            //sentence();//�޸�
            loop_sentence_table();
        }
        //��
    }

    //Sample�����еĺ���
    private static void function_definition(){
        function_type();
        if(list.get(index).getValue()==700){//IDN
            getNext();
            if(list.get(index).getKey().equals("(")){
                getNext();
                //���
                function_definition_parameter_table();
                if (list.get(index).getKey().equals(")")){
                    getNext();
                    compound_sentence();//���
                }
                else error(list.get(index).getKey());
            }
            else error(list.get(index).getKey());
        }
        else error(list.get(index).getKey());
    }

    private static void function_definition_parameter_table(){
        //type();
        if(list.get(index).getKey().equals("int")){//IDN,ֻ������
            function_definition_parameter();
        }
        //��
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
        //��
    }

    //����һ��
    private static void function_block() {
        if(index<list.size()-1&&!list.get(index).getKey().equals("}")){
            function_definition();
            function_block();
        }
        //��
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
            declaration_sentence_prime();//�ݹ�-��չ��
        }
        //��
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

