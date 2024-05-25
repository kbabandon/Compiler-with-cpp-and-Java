package gui;

import analysis.Compile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * GBK�����ļ���gui���������
 */

/**
 * @author j'x'f
 * @date 2024-5-20 16:33
 * ���沼�ֺ��ļ�����
 * @apiNote
 */
public class GUI {

    String defaultpath = "C:\\Users\\jxf\\DownLoads";//charlie

    //����
    JFrame frame; //������
    JTextArea textArea; //���ı��༭��
    JTextArea textArea1; //�����ı��༭��
    JTextArea textArea2; //�����ı��༭��
    JMenuBar jMenuBar; //�˵���

    //�ļ�
    File file;
    JFileChooser lastchooser;
    //�����ļ����͹�����
    String[][] filelist = new String[][]{
            {"JAVAԴ�ļ�.java", "java"},
            {"�ı��ļ�.txt", "txt"}

    };

    public GUI() {

        //���ܺ�����
        frame = new JFrame("Compiler");
        frame.setMinimumSize(new Dimension(1500, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); //���ھ���

        Container contentPane = frame.getContentPane();

        /**
         * �ı�����
         * ���� �ɱ༭��������ʽ��
         */
        textArea = new JTextArea();
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();

        textArea1.setEditable(false);
        textArea2.setEditable(false);

        textArea.setFont(new java.awt.Font("����", 2, 16));
        textArea1.setFont(new java.awt.Font("����", 3, 16));
        textArea2.setFont(new java.awt.Font("����", 3, 16));

        //textArea.setEnabled(false);
        //textArea1.setEnabled(false);


        /**
         * �����������������
         *
         */
        JScrollPane leftScroll = new JScrollPane(textArea);
        leftScroll.setPreferredSize(new Dimension(700, 250));
        //��Ч���
        leftScroll.setBackground(new Color(239, 15, 89));//���û����
        JScrollPane TopScroll = new JScrollPane(textArea1);
        JScrollPane bottomScroll = new JScrollPane(textArea2);

        //����ˮƽ�ָ����
        JSplitPane hsplitPane = new JSplitPane();
        //���ò��ܵ�����С
        //hsplitPane.setEnabled(false);
        hsplitPane.setLeftComponent(leftScroll);//ˮƽ�ָ����������Ϊ������
        //���÷ָ����϶�ʱ�����ػ�
        hsplitPane.setContinuousLayout(true);

        //������ֱ�ָ����
        JSplitPane vsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        //���÷ָ����϶�ʱ�����ػ�
        vsplitPane.setContinuousLayout(true);
        //���ÿ����۵����㿪�������С��ť
        //vsplitPane.setOneTouchExpandable(true);//û��Ҫ�ָ����ϵ�^��ť

        //�����������
        vsplitPane.setTopComponent(TopScroll);
        //�����������
        vsplitPane.setBottomComponent(bottomScroll);
        //���÷ָ���λ�� y����,������
        vsplitPane.setDividerLocation(450);
        //����ֱ�ָ�������ˮƽ�ָ����
        hsplitPane.setRightComponent(vsplitPane);
        //���÷ָ�������������
        contentPane.add(hsplitPane); //contentPaneǰ���Ѷ���

        //�˵���
//        JPanel panel = new JPanel();//û�õ�
//        panel.setBackground(new Color(255, 160, 122));


        /**
         * ���ò˵���
         */
        jMenuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("�ļ�");
        //�����ļ���ݼ�(alt+F)
        fileMenu.setMnemonic(KeyEvent.VK_F);


        JMenu editMenu = new JMenu("�༭");
        JMenu lexicalAnalysis = new JMenu("�ʷ�����");
        JMenu SyntaxAnalysis = new JMenu("�﷨����");
        JMenu middleCode = new JMenu("�м����");
        JMenu createCode = new JMenu("Ŀ���������");
        JMenu viewMenu = new JMenu("�鿴");
        JMenu helpMenu = new JMenu("����");//(alt+H)

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(lexicalAnalysis);
        jMenuBar.add(SyntaxAnalysis);
        jMenuBar.add(middleCode);
        jMenuBar.add(createCode);
        jMenuBar.add(viewMenu);
        jMenuBar.add(helpMenu);

        /**
         * �����ļ������˵�
         *
         */
        JMenuItem item1 = new JMenuItem("��(ctrl+o)");
        //���ô򿪿�ݼ�(ctrl+o)
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));//InputEvent�Դ��࣬��̬����???
        JMenuItem item2 = new JMenuItem("����(ctrl+s)");
        //���ñ����ݼ�(ctrl+s)
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        //�������Ϊ����
        JMenuItem item3 = new JMenuItem("���Ϊ");


        //���ð��������˵�
        JMenuItem helpItem = new JMenuItem("����");
        JMenuItem aboutItem = new JMenuItem("����Compiler(ctrl+a)");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);


        fileMenu.add(item1);
        fileMenu.addSeparator();//������?? �ǵ�
        fileMenu.add(item2);
        fileMenu.add(item3);

        frame.setJMenuBar(jMenuBar);

        /**
         * ���ð�ť�¼�
         *
         */
        //���ô򿪰�ť�¼�
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser;
                //ѡ���ļ�
                if (lastchooser != null) {
                    chooser = new JFileChooser(lastchooser.getCurrentDirectory());
                } else {
                    chooser = new JFileChooser(defaultpath);
                }
                addFileFilter(chooser);
                if (chooser.showOpenDialog(item1) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    lastchooser = new JFileChooser(chooser.getCurrentDirectory());
                    textArea.setText(file.getName() + ":" + file.getPath() + "\n" + file.length());
                    readfile(file);
                }
            }
        });

        //���ñ��水ť�¼�
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                addFileFilter(chooser);
                //if (chooser.showSaveDialog(item2) == JFileChooser.APPROVE_OPTION){
                //    //File file = chooser.getSelectedFile();
                //    writeFile(file.getPath());
                //}
                writeFile(file.getPath());
            }
        });

        //�������Ϊ�¼�
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser;
                if (lastchooser != null) {
                    chooser = new JFileChooser(lastchooser.getCurrentDirectory());
                } else {
                    chooser = new JFileChooser(defaultpath);

                }
                addFileFilter(chooser);
                if (chooser.showSaveDialog(item3) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    writeFile(file.getPath());
                }
            }
        });

        //�ʷ�����
        lexicalAnalysis.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Compile.phraseAnalysis(textArea,textArea1,textArea2);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //�﷨����
        SyntaxAnalysis.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Compile.SyntaxAnalysis(textArea1,textArea2);
                demo.main(new String[0]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //���ð�����ť����chm�ļ�
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File chmFile = new File("C:\\Users\\j'x'f\\Desktop\\����ԭ��\\JDK_API_1.8.CHM");
                try {
                    Desktop.getDesktop().open(chmFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        //���ù��ڰ�ť�򿪵���
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutFrame = new JFrame("����");
                //���ý��رո�һ������
                aboutFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                aboutFrame.setMinimumSize(new Dimension(600, 200));
                aboutFrame.setLocationRelativeTo(frame);
                aboutFrame.setLayout(new BorderLayout());

                Container aboutcontent = aboutFrame.getContentPane();
                JLabel aboutlabel = new JLabel("���ǹ��ڴ���", JLabel.CENTER);
                aboutcontent.add(aboutlabel);


                aboutFrame.pack();
                aboutFrame.setVisible(true);

            }
        });


        //JLabel Label = new JLabel("123456",JLabel.CENTER);
        //Label.setBackground(new Color(2, 57, 255));
        //contentPane.add(Label,BorderLayout.NORTH);
        //contentPane.add(panel,BorderLayout.SOUTH);
        //contentPane.add(jScrollPane,BorderLayout.WEST);

        //contentPane.add(Label);
        //contentPane.add(jScrollPane);
        //contentPane.add(jsScroll);
        //contentPane.add(panel);
        //Label.setBounds(0,0,50,50);
        //jScrollPane.setBounds(10,50,450,750);
        //jsScroll.setBounds(470,50,400,400);


        frame.pack();
        frame.setVisible(true);


    }

    //���ļ�
    public void readfile(File file) {

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str + "\n");
            }
            textArea.setText(stringBuffer.toString());
        } catch (Exception e) {

        }
    }

    //д�ļ�
    public void writeFile(String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(textArea.getText().getBytes());
            fileOutputStream.close();
            System.out.println("�ѱ���");

        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.getText();
    }

    //Ϊ�ļ�ѡ������ӹ�����
    public void addFileFilter(JFileChooser chooser) {
        for (int i = 0; i < filelist.length; i++) {
            chooser.setFileFilter(new FileNameExtensionFilter(filelist[i][0], filelist[i][1]));
        }
    }


    public static void main(String[] args) {
        new GUI();
    }
}
