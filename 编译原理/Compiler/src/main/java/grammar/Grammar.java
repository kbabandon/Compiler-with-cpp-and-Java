package grammar;

import analysis.Compile;

public class Grammar {
    public static int index = -1;  //token�б��±�
    public static int token = 0;  //tokenֵ
    public static int space = 0;  //�ո���
    public static String str = "";  //�﷨��
    public static String error_str = "";  //�﷨����ľ���
    public static int current_line = 0;  //��ǰtoken������

    private static void output(String s) {
        String str1 = "";
        for (int i = 0; i < space; i++)//�������
            str1 += "->";//-- | ->
        str1 += s;
        str += str1 + "\n";//��������str
    }

    private static void error(Integer line, String s) {
        error_str += "��" + line + "��, " + s + "\n";
    }

    //    ȡ��tokenֵ
    private static int getNextToken() {
        current_line = Compile.list.get(++index).getLine();
        if ((index - 1) >= 0 && current_line != Compile.list.get(index - 1).getLine())
            current_line = Compile.list.get(index - 1).getLine();
        return Compile.list.get(index).getValue();
    }

    //    ���������ӣ����ţ���Ŀȡ����������������,��F
    private static void factor() {
        token = Compile.list.get(index + 1).getValue();
//        �Ǻ�������
        if (token == Compile.dictionary.get("(")) {
//            ��ǰ��
            token = Compile.list.get(index).getValue();
            if (token != 700) {
                error(current_line, "�������ò��Ǳ�ʶ��");
                index--;
            }
            token = getNextToken();
            if (token != Compile.dictionary.get("(")) {
                error(current_line, "��������û��(");
                index--;
            }
            token = Compile.list.get(index + 1).getValue();
            if (token == Compile.dictionary.get(")")) {
                index++;
                return;
            } else {
//              ʵ���б�
                argument();
                token = getNextToken();
                if (token != Compile.dictionary.get(")")) {
                    error(current_line, "ʵ���б�ȱ�٣�");
                    index--;
                }
            }
            return;
        }
//        ��ǰ��index
        token = Compile.list.get(index).getValue();
//        �ǣ��������ʽ��
        if (token == Compile.dictionary.get("(")) {   //��������
            aexpr();    //�����м��E
            token = getNextToken();
            if (token != Compile.dictionary.get(")")) {
                index--;
                error(current_line, "ȱ��)");
            }
        } else if (token == Compile.dictionary.get("-")) {//����Ŀȡ��
            aexpr();
//            ����(��ֵ�� �ַ���) ���� ��ʶ��
        } else if (token == 700 || token == 400 || token == 500) {
            return;
        } else {
            error(current_line, "�������ʽ�﷨����");
            index--;  //-------------------Ӧ��Ҫ��
        }
    }


    //����˳���ȡ���������Ų��֣���T
    private static void term() {
        factor();      //��������
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get("*") || token == Compile.dictionary.get("/")) {
                token = getNextToken();
                factor();
            } else {
                index--;    //�˳���ǰȡ����token
                break;
            }
        }
    }


    //    �����������ʽ,
    public static void aexpr() {
        space++;
        output("��ʼ�������ʽ");
        token = getNextToken();
        term();
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get("+") || token == Compile.dictionary.get("-")
                    || token == Compile.dictionary.get("%")) { //ƥ�䴦��Ӽ���
                token = getNextToken();    //��ȡ��һtoken
                term();
            } else {  //���ʽ�������
                index--;    //�˳���ǰȡ����token
                break;
            }
        }
        output("�����������ʽ");
        space--;
    }

    //--------------------------------------------------------
//    �����ϵ���ʽ
    public static void relational_expression() {
        space++;
        output("��ʼ��ϵ���ʽ");
        aexpr();//�������ʽ
        relational_operator();
        aexpr();
        output("������ϵ���ʽ");
        space--;
    }

    private static void relational_operator() {
        token = getNextToken();//����һ��token
        if (!(token == Compile.dictionary.get(">") || token == Compile.dictionary.get("<")
                || token == Compile.dictionary.get(">=") || token == Compile.dictionary.get("<=")
                || token == Compile.dictionary.get("==") || token == Compile.dictionary.get("!="))) {
            error(current_line, "���ǹ�ϵ�����");
        }
    }

    //--------------------------------------------------------
//    ��������
    private static void bool_factor() {
        token = getNextToken();
        if (token == Compile.dictionary.get("!")) {
            bool_expression();
        } else {
            index--;
            aexpr();
            index++;
            if ((token == Compile.dictionary.get(">") || token == Compile.dictionary.get("<")
                    || token == Compile.dictionary.get(">=") || token == Compile.dictionary.get("<=")
                    || token == Compile.dictionary.get("==") || token == Compile.dictionary.get("!="))) {
                aexpr();
            } else {
                index--;
            }
        }
    }

    //    ������
    private static void bool_item() {
        bool_factor();
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get("&&")) {
                bool_factor();  //��������
            } else {
                index--;
                break;
            }
        }
    }

    //    ���������ʽ
    public static void bool_expression() {//�Ȼ����
        space++;
        output("��ʼ�������ʽ");
        bool_item();  //��������
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get("||")) {
                bool_item();  //��������
            } else {
                index--;
                break;
            }
        }
        output("�����������ʽ");
        space--;
    }

    //------------------------------------------------
    //����ֵ���ʽ
    public static void assignment_expression() {
        space++;
        output("��ʼ��ֵ���ʽ");
        token = getNextToken();
        if (token != 700) {
            error(current_line, "���Ǳ�ʶ�������Ǹ�ֵ���ʽ");
            index--;
        }
        token = getNextToken();
        if (!(token == Compile.dictionary.get("=") || token == Compile.dictionary.get("+=") ||
                token == Compile.dictionary.get("-=") || token == Compile.dictionary.get("*=") ||
                token == Compile.dictionary.get("/="))) {
//                ��ʼ�ж��Ƿ�Ϊ���ʽ ����|��ϵ|����|��ֵ
            error(current_line, "���ǵȺ�");
            index--;
        }
        expression();


        output("������ֵ���ʽ");
        space--;
    }

    //���ʽ���
    public static void expression() {//���ʽ �����˸�ֵ���ʽ
//        ��ǰ���жϵڶ�������  ����ǵȺž��Ǹ�ֵ��������ǲ���
        token = Compile.list.get(index + 2).getValue();
//        ��ֵ�׸�ֵ
        if (token == Compile.dictionary.get("=")){
            space++;output("��ʼ��ֵ���ʽ");
            assignment_expression();
            output("������ֵ���ʽ");space--;
        }

        else
            bool_expression();
    }

    //---------------------------------------------------


    //    ���   ������������������������������
    private static void statement() {
        token = getNextToken();
//      �������  ֵ�����ĳ�������
        if (token == Compile.dictionary.get("const")) {
            token = getNextToken();
            if (!(token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") ||
                    token == Compile.dictionary.get("float"))) {
                error(current_line, "�����������ʹ���");
                index--;
            }
            token = getNextToken();
            if (token != 700) {
                error(current_line, "�������ͺ��治�Ǳ�ʶ��");
                index--;
            }
            token = getNextToken();
            if (token != Compile.dictionary.get("=")) {
                error(current_line, "���������б�ʶ�����ǵȺ�");
                index--;
            }
            //  ����������
            constant_declaration_list();
//            ���������ͺ�������
        } else if (token == Compile.dictionary.get("void")) {
//            �϶��Ǻ�������
            function_declaration();

        } else if (token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") ||
                token == Compile.dictionary.get("float")) {
//            �����Ǻ�������Ҳ�����Ǳ�������������ݱ�ʶ�������Ƿ�����������һ��ȷ��
            token = getNextToken();//��������ж��Ƿ��Ǳ�ʶ��
            token = getNextToken();
//            �����ſ϶��Ǻ���������
            if (token == Compile.dictionary.get("(")) {
                index -= 2;
                function_declaration();
//                ���������Ǿ��Ǳ�������
            } else {
                index -= 2;
                variable_declarations();
            }

        } else {
//            ִ�����  ��Ϊ���ݴ��� ���� ����
////            ��ǰ��һ��  ��indexֵ���ᷢ���ı�
//            token = Compile.list.get(index + 1).getValue();
//            �Ǹ���
            if (token == Compile.dictionary.get("{")) {
                compound_sentence();
//                �ǿ���
            } else if (token == Compile.dictionary.get("if")) {
                if_statements();
            } else if (token == Compile.dictionary.get("while")) {
                while_statements();
            } else if (token == Compile.dictionary.get("do")) {
                do_while_statements();
            } else if (token == Compile.dictionary.get("for")) {
                for_statements();
            } else if (token == Compile.dictionary.get("return")) {
                return_statements();
//                �����ݴ���
            } else if (token == 700) {//��ʶ��
                token = getNextToken();
                if (token == Compile.dictionary.get("=")) {
//                    �������д��ʼ����ֵ���ʽ
                    expression();
                } else if (token == Compile.dictionary.get("(")) {
                    token = Compile.list.get(index + 1).getValue();
//                    �������������޲�ֻ�У���
                    if (token == Compile.dictionary.get(")")) {
                        index++;
                        // �������û�Ҫ��������;�ֺ�
                        token = getNextToken();
                        if (token != Compile.dictionary.get(";")) {
                            error(current_line, "ĩβ��ֺ�");
                            index--;
                        }
                        return;
                    } else {
                        argument();
                        token = getNextToken();
                        if (token != Compile.dictionary.get(")")) {
                            error(current_line, "�вκ�������ȱ�٣�");
                            index--;
                        }
                    }

                } else {
                    error(current_line, "���� ���ݴ����еı�ʶ�����治��=��");
                }
                // ���ݴ������ ��Ҫ��������;�ֺ�
                token = getNextToken();
                if (token != Compile.dictionary.get(";")) {
                    error(current_line, "ĩβ��ֺ�");
                    index--;
                }
            } else if(token == Compile.dictionary.get(";")){
                return ; //ֻ��һ���ֺ�ֱ������
            }
            else{
                error(current_line, "���������");
//                index--;  ���ﲻ�ܼ�
            }
        }

    }

    //    ʵ��
    private static void argument() {
        expression();
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get(",")) {
                argument();
            } else {
                index--;
                break;
            }
        }
    }

    // ��������ȥ���˱�������ǰ���Ѿ��ж��˵��ڷ�֮ǰ   ������������
    private static void variable_declarations() {
        token = getNextToken();
        if (token != 700) {
            error(current_line, "����������߲��Ǳ���");
            index--;
        }
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get("=")) {
                expression();
            } else {
                index--;
                break;
            }
        }
        token = getNextToken();
        if (token == Compile.dictionary.get(";")) {
            return;
        } else if (token == Compile.dictionary.get(",")) {
            variable_declarations();
        } else {
            error(current_line, "����������ʶ�����ǣ��򣻴���");
            index--;
        }
    }

    //����������ȥ���˺�������
    private static void function_declaration() {
        space++;
        output("��ʼ��������");
        token = getNextToken();
        if (token != 700) {
            error(current_line, "���������к������ͺ��治�Ǳ�ʶ��");
            index--;
        }
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "���������βε�����߲���(");
            index--;
        }
        //    ���������β�
        function_declaration_parameter();
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "���������βε����ұ߲���)");
            index--;
        }
        token = getNextToken();
        if (token != Compile.dictionary.get(";")) {
            error(current_line, "��������û��;");
            index--;
        }
        output("������������");
        space--;
    }

    //    ���������β�
    private static void function_declaration_parameter() {
        token = getNextToken();
        if (!(token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") ||
                token == Compile.dictionary.get("float"))) {
            error(current_line, "���������β�ǰ�������ʹ���");
            index--;
        }
        while (true) {
            token = getNextToken();
            if (token == Compile.dictionary.get(",")) {
                function_declaration_parameter();
            } else {
                index--;
                break;
            }
        }
    }

    //    ����������
    private static void constant_declaration_list() {
        token = getNextToken();
        if (token != 500) {
            error(current_line, "���������ұ߲��ǳ���");
            index--;
        }
        token = getNextToken();
        if (token == Compile.dictionary.get(";")) {
            System.out.println("����������");
        } else if (token == Compile.dictionary.get(",")) {
            constant_declaration_list();
        } else {
            error(current_line, "��β����;��,");
        }
    }

    //    ����  ֻ���м��  ���������Ѿ��ڸ�������ж���
    private static void statement_table() {
        statement(); //���
//        ִ�����һ������ ���û�г���}��������������䣬ֱ������}����token�궼û������ȱ��}
        while (true) {
            token = Compile.list.get(index + 1).getValue();
            if (token != Compile.dictionary.get("}"))
                statement();
            else
                break;
        }

    }


    // �������
    private static void compound_sentence() {
        space++;
        output("��ʼ�������");
        token = getNextToken();
        if (token != Compile.dictionary.get("{")) {
            error(current_line, "�������ȱ��'{'");
            index--;
        }
        statement_table(); //����
        token = getNextToken();
        if (token != Compile.dictionary.get("}")) {
            error(current_line, "�������ȱ��'}'");
            index--;
        }
        output("�����������");
        space--;
    }

    //    if���
    public static void if_statements() {
        space++;
        output("��ʼif���");
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "if���ȱ��'('");
            index--;
        }
//        token = getNextToken();
        expression();  //���÷������ʽ
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "if���ȱ��')'");
            index--;
        }
        //if����ֻ��һ��������������Ҳ����û���� ������Ҫ��ǰ�жϺ���һ���Ƿ�Ϊ{
        token = Compile.list.get(index + 1).getValue();
//        ������{������븴�����
        if (token == Compile.dictionary.get("{")) {
            compound_sentence();  //���������
        }
//       ���������䡣
        else
            statement();
        token = Compile.list.get(index + 1).getValue();
        if (token == Compile.dictionary.get("else")) {    //����else����ʱ����else����
            token = getNextToken();//�Ե�else
            token = Compile.list.get(index + 1).getValue();
//        ������{������븴�����
            if (token == Compile.dictionary.get("{")) {

                compound_sentence();  //���������
            }
//       ���������䡣
            else
                statement();
        }
        output("����if���");
        space--;
    }

    //    for���
    public static void for_statements() {
        space++;
        output("��ʼfor���");
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "for���治��(");
            index--;
        }
        expression();
        token = getNextToken();
        if (token != Compile.dictionary.get(";")) {
            error(current_line, "for�ĵ�һ���������治��;");
            index--;
        }
        expression();
        token = getNextToken();
        if (token != Compile.dictionary.get(";")) {
            error(current_line, "for�ĵڶ����������治��;");
            index--;
        }
        expression();
//                    ���ʽ����û�зֺ�
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "for�ĵ������������治��)");
            index--;
        }
        loop_statements();
        output("����for���");
        space--;
    }

    //    while���
    public static void while_statements() {
        space++;
        output("��ʼwhile���");
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "while���治��(");
            index--;
        }
        expression();
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "whileû��)");
            index--;
        }
        loop_statements();
        output("����while���");
        space--;
    }

    //    do_while���
    public static void do_while_statements() {
        space++;
        output("��ʼdo_while���");
//        ѭ���������
        cyclic_statement();
        token = getNextToken();
        if (token != Compile.dictionary.get("while")) {
            error(current_line, "do_while���û��while");
            index--;
        }
        while_statements();
//            �����һ����������
        token = getNextToken();
        if (token != Compile.dictionary.get(";")) {
            error(current_line, "do_while������û��;");
            index--;
        }
        output("����do_while���");
        space--;
    }

    //ѭ������?
    private static void cyclic_statement() {
        token = getNextToken();
        if (token != Compile.dictionary.get("{")) {
            error(current_line, "do���治��{");
            index--;
        }
//            ѭ������
//            ѭ�����
        loop_statements();
        token = Compile.list.get(index + 1).getValue();
        if (token == Compile.dictionary.get("}")) {
//                ˵��ѭ��������
            index++;
            return;
//                �������ѭ�����
        } else {
            loop_statements();
        }
    }


    //    return���
    public static void return_statements() {
        space++;
        output("��ʼreturn���");
        token = getNextToken();
        if (token == Compile.dictionary.get(";"))
            return;
        else {
//            ����ѣ�����
            index--;
            expression();
            token = getNextToken();
            if (token != Compile.dictionary.get(";")){
                error(current_line, "return�������;");
                index--;
            }

        }
        output("����return���");
        space--;
    }

    //     ѭ�����
    public static void loop_statements() {
        space++;
        output("��ʼѭ�����");
        token = Compile.list.get(index + 1).getValue();
//        ѭ���ø�������
        if (token == Compile.dictionary.get("{")) {
//            ����whileѭ���ж����ֱ������}
            token = getNextToken();  //�Ե�{
            token = Compile.list.get(index + 1).getValue();
            if (token == Compile.dictionary.get("}")) {
                token = getNextToken();
                return;
            }
            while (token != Compile.dictionary.get("}")) {
                statement();
                token = Compile.list.get(index + 1).getValue();
            }
            token = getNextToken();
//            ֻ��һ�����
        } else {
            statement();
        }
        output("����ѭ�����");
        space--;
    }

    // �������
    private static boolean declaration_statements() {
        boolean flag = false;
        token = getNextToken();
        //      �����ж��Ƿ�Ϊ�������
        if (token == Compile.dictionary.get("const")) {
            space++;
            output("��ʼ��������");
            flag = true;
            token = getNextToken();
            if (!(token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") ||
                    token == Compile.dictionary.get("float"))) {
                error(current_line, "�����������ʹ���");
                index--;
            }
            token = getNextToken();
            if (token != 700) {
                error(current_line, "�������ͺ��治�Ǳ�ʶ��");
                index--;
            }
            token = getNextToken();
            if (token != Compile.dictionary.get("=")) {
                error(current_line, "���������б�ʶ�����ǵȺ�");
                index--;
            }
            //  ����������
            constant_declaration_list();
            output("������������");
            space--;
//            ���������ͺ�������
        } else if (token == Compile.dictionary.get("void")) {
            flag = true;
//            �϶��Ǻ�������
            function_declaration();

        } else if (token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") ||
                token == Compile.dictionary.get("float")) {
//            �����Ǻ�������Ҳ�����Ǳ�������������ݱ�ʶ�������Ƿ�����������һ��ȷ��
            flag = true;
            token = getNextToken();//��������ж��Ƿ��Ǳ�ʶ�������������ж���
            token = getNextToken();
//            �����ſ϶��Ǻ���������
            if (token == Compile.dictionary.get("(")) {
                index -= 2;
                function_declaration();
//                ���������Ǿ��Ǳ�������
            } else {
                index -= 2;
                variable_declarations();
            }

        }
        return flag;
    }

    //    ��������
    private static void function() {
        if (index == Compile.list.size() - 1)
            return;
        token = getNextToken();
        if (!(token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") || token ==
                Compile.dictionary.get("float") || token == Compile.dictionary.get("void"))) {
            error(current_line, "���Ǻ����������");
            index--;
        }
        token = getNextToken();
        if (token != 700) {
            error(current_line, "�������岻�Ǳ�ʶ��");
            index--;
        }
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "��������û��(");
            index--;
        }
        function_parameters_list();
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "��������û��)");
            index--;
        }
        compound_sentence();
    }

    //���������β��б�
    private static void function_parameters_list() {
        token = Compile.list.get(index + 1).getValue();
//        ���ǿյ��β��б�
        if (token == Compile.dictionary.get(")"))
            return;
//        ���������β�
        function_parameters();
    }

    //        ���������β�
    private static void function_parameters() {
        token = getNextToken();
        if (!(token == Compile.dictionary.get("int") || token == Compile.dictionary.get("char") || token ==
                Compile.dictionary.get("float"))) {
            error(current_line, "���������βεı������ʹ���");
            index--;
        }
        token = getNextToken();
        if (token != 700) {
            error(current_line, "���������βεı�ʶ������");
            index--;
        }
        token = Compile.list.get(index + 1).getValue();
        if (token == Compile.dictionary.get(",")) {
//                    �ѣ��Ե�
            token = getNextToken();
            function_parameters();
        } else if (token == Compile.dictionary.get(")")) {
            return;
        } else {
            error(current_line, "���������β��б���,");
            index--; //--------------------------------------Ӧ��Ҫ���ɣ���
        }
    }

    //    main�������
    public static void main_body() {
        index = -1;  //token�б��±�
        token = 0;  //tokenֵ
        space = 0;  //�ո���
        str = "";  //�﷨��
        error_str = "";  //�﷨����ľ���
        current_line = 0;  //��ǰtoken������
//        ������� ���п���  �еĻ�Ҳ��ֻһ��
        while (true) {
            if (!declaration_statements()) {
                index--;
            }

            if (Compile.list.get(index + 1).getValue() == Compile.dictionary.get("main")) {
                token = getNextToken();
                break;
            }
            if (index == Compile.list.size() - 1) {
                error(current_line, "û��main����");
                return;
            }
        }

//        main��������
        space++;
        output("��ʼmain����");
        token = getNextToken();
        if (token != Compile.dictionary.get("(")) {
            error(current_line, "main����û��(");
            index--;
        }
        token = getNextToken();
        if (token != Compile.dictionary.get(")")) {
            error(current_line, "main����û��)");
            index--;
        }
        compound_sentence();
        output("����main����");
        space--;
        while (index != Compile.list.size() - 1) {
            space++;
            output("��ʼ��������");
            function();
            output("������������");
            space--;
        }
    }

}

