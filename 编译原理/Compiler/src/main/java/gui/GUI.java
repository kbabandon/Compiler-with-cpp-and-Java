package gui;

import analysis.Compile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * GBK编码文件，gui界面的文字
 */

/**
 * @author j'x'f
 * @date 2024-5-20 16:33
 * 界面布局和文件处理
 * @apiNote
 */
public class GUI {

    String defaultpath = "C:\\Users\\jxf\\DownLoads";//charlie

    //布局
    JFrame frame; //界面框架
    JTextArea textArea; //左文本编辑区
    JTextArea textArea1; //右上文本编辑区
    JTextArea textArea2; //右下文本编辑区
    JMenuBar jMenuBar; //菜单栏

    //文件
    File file;
    JFileChooser lastchooser;
    //设置文件类型过滤器
    String[][] filelist = new String[][]{
            {"JAVA源文件.java", "java"},
            {"文本文件.txt", "txt"}

    };

    public GUI() {

        //外框架和容器
        frame = new JFrame("Compiler");
        frame.setMinimumSize(new Dimension(1500, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); //窗口居中

        Container contentPane = frame.getContentPane();

        /**
         * 文本区域
         * 设置 可编辑、字体样式等
         */
        textArea = new JTextArea();
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();

        textArea1.setEditable(false);
        textArea2.setEditable(false);

        textArea.setFont(new java.awt.Font("楷体", 2, 16));
        textArea1.setFont(new java.awt.Font("楷体", 3, 16));
        textArea2.setFont(new java.awt.Font("楷体", 3, 16));

        //textArea.setEnabled(false);
        //textArea1.setEnabled(false);


        /**
         * 创建两个滚动条面板
         *
         */
        JScrollPane leftScroll = new JScrollPane(textArea);
        leftScroll.setPreferredSize(new Dimension(700, 250));
        //无效标记
        leftScroll.setBackground(new Color(239, 15, 89));//左边没体现
        JScrollPane TopScroll = new JScrollPane(textArea1);
        JScrollPane bottomScroll = new JScrollPane(textArea2);

        //创建水平分割面板
        JSplitPane hsplitPane = new JSplitPane();
        //设置不能调整大小
        //hsplitPane.setEnabled(false);
        hsplitPane.setLeftComponent(leftScroll);//水平分割面板的左组件为。。。
        //设置分割条拖动时连续重绘
        hsplitPane.setContinuousLayout(true);

        //创建垂直分割面板
        JSplitPane vsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        //设置分割条拖动时连续重绘
        vsplitPane.setContinuousLayout(true);
        //设置快速折叠，点开两边组件小按钮
        //vsplitPane.setOneTouchExpandable(true);//没必要分割条上的^按钮

        //设置右上组件
        vsplitPane.setTopComponent(TopScroll);
        //设置右下组件
        vsplitPane.setBottomComponent(bottomScroll);
        //设置分割条位置 y坐标,单参数
        vsplitPane.setDividerLocation(450);
        //将垂直分割面板加入水平分割面板
        hsplitPane.setRightComponent(vsplitPane);
        //将该分割面板加入容器中
        contentPane.add(hsplitPane); //contentPane前面已定义

        //菜单栏
//        JPanel panel = new JPanel();//没用到
//        panel.setBackground(new Color(255, 160, 122));


        /**
         * 设置菜单栏
         */
        jMenuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("文件");
        //设置文件快捷键(alt+F)
        fileMenu.setMnemonic(KeyEvent.VK_F);


        JMenu editMenu = new JMenu("编辑");
        JMenu lexicalAnalysis = new JMenu("词法分析");
        JMenu SyntaxAnalysis = new JMenu("语法分析");
        JMenu middleCode = new JMenu("中间代码");
        JMenu createCode = new JMenu("目标代码生成");
        JMenu viewMenu = new JMenu("查看");
        JMenu helpMenu = new JMenu("帮助");//(alt+H)

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(lexicalAnalysis);
        jMenuBar.add(SyntaxAnalysis);
        jMenuBar.add(middleCode);
        jMenuBar.add(createCode);
        jMenuBar.add(viewMenu);
        jMenuBar.add(helpMenu);

        /**
         * 设置文件下拉菜单
         *
         */
        JMenuItem item1 = new JMenuItem("打开(ctrl+o)");
        //设置打开快捷键(ctrl+o)
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));//InputEvent自带类，静态变量???
        JMenuItem item2 = new JMenuItem("保存(ctrl+s)");
        //设置保存快捷键(ctrl+s)
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        //设置另存为功能
        JMenuItem item3 = new JMenuItem("另存为");


        //设置帮助下拉菜单
        JMenuItem helpItem = new JMenuItem("帮助");
        JMenuItem aboutItem = new JMenuItem("关于Compiler(ctrl+a)");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);


        fileMenu.add(item1);
        fileMenu.addSeparator();//横线吗?? 是的
        fileMenu.add(item2);
        fileMenu.add(item3);

        frame.setJMenuBar(jMenuBar);

        /**
         * 设置按钮事件
         *
         */
        //设置打开按钮事件
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser;
                //选择文件
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

        //设置保存按钮事件
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

        //设置另存为事件
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

        //词法分析
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

        //语法分析
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

        //设置帮助按钮，打开chm文件
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File chmFile = new File("C:\\Users\\j'x'f\\Desktop\\编译原理\\JDK_API_1.8.CHM");
                try {
                    Desktop.getDesktop().open(chmFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        //设置关于按钮打开弹窗
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutFrame = new JFrame("关于");
                //设置仅关闭该一个窗口
                aboutFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                aboutFrame.setMinimumSize(new Dimension(600, 200));
                aboutFrame.setLocationRelativeTo(frame);
                aboutFrame.setLayout(new BorderLayout());

                Container aboutcontent = aboutFrame.getContentPane();
                JLabel aboutlabel = new JLabel("这是关于窗口", JLabel.CENTER);
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

    //读文件
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

    //写文件
    public void writeFile(String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(textArea.getText().getBytes());
            fileOutputStream.close();
            System.out.println("已保存");

        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.getText();
    }

    //为文件选择器添加过滤器
    public void addFileFilter(JFileChooser chooser) {
        for (int i = 0; i < filelist.length; i++) {
            chooser.setFileFilter(new FileNameExtensionFilter(filelist[i][0], filelist[i][1]));
        }
    }


    public static void main(String[] args) {
        new GUI();
    }
}
