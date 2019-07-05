import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Frame extends JFrame implements ActionListener, ItemListener , MouseListener {

    int index;

    int op=0;

    private JFrame jf=new JFrame();//选择修改时弹出框
    //进程信息标签  进程编号PID,所需运行时间requiredTime,优先级priority,当前状态statues,内存中的基址base,所需内存大小limit
    private JLabel PIDLabel, NameLabel, SizeLabel, TimeLabel, PriorityLabel, BaseLabel;
    //进程信息文本框 PID(可编辑),requiredTime(可编辑),priority(可编辑),status(不可编辑),base(不可编辑),limit(可编辑)
    private JTextField PIDtf, Nametf, Sizetf, Timetf, Prioritytf, Basetf;
    private JButton button1, button2;
    private JPanel jpopuppanel;
    private JPopupMenu jpm=new JPopupMenu();
    private Vector v=new Vector();
    private JRadioButtonMenuItem[] items=new JRadioButtonMenuItem[2]; //初始化数组

    JTextArea memoryta=new JTextArea();

    int coll=0;

    int argStste=0;
    int armstate=1;
    int ramid=0;
    int user=1;

    public JTextField jta1=new JTextField();
    public JTextField jta2=new JTextField();
    public JTextField jta3=new JTextField();
    public JTextField jta4=new JTextField();
    public JTextField jta5=new JTextField();
    JTextField jta6=new JTextField();
    JTextField time=new JTextField("3");
    int times=3;
    public JButton jb1=new JButton("添加");
    public JButton jb2=new JButton("清空");
    JButton jb3=new JButton("运行");
    JButton jb4=new JButton("暂停");
    JButton jb5=new JButton("清空");
    JRadioButton jrb1=new JRadioButton("timeSlice");
    JRadioButton jrb2=new JRadioButton("SJF");
    JRadioButton jrb3=new JRadioButton("Priority");
    JRadioButton jrb4=new JRadioButton("FCFS");
    JRadioButton jr1=new JRadioButton("首次适应");
    JRadioButton jr2=new JRadioButton("循环首次适应");
    JRadioButton jr3=new JRadioButton("最佳适应");
    JRadioButton jr4=new JRadioButton("最坏适应");
    JRadioButton jRadioButton=new JRadioButton("就绪");
    JRadioButton jRadioButton1=new JRadioButton("阻塞");
    JRadioButton jRadioButton2=new JRadioButton("后备");
    JRadioButton jRadioButton3=new JRadioButton("挂起");
    DefaultListModel defaultListModel=new DefaultListModel();
    JTextArea jTextArea=new JTextArea();
    JTextArea jTextArea1=new JTextArea();
    JRadioButton user1=new JRadioButton("用户一");
    JRadioButton user2=new JRadioButton("用户二");

    JPanel list=new JPanel();
    JList jList=new JList(defaultListModel);

    public Frame(){


        jpm.setFont(new Font("宋体",Font.BOLD,30));
        items[0]=new JRadioButtonMenuItem("查看");
        items[1]=new JRadioButtonMenuItem("修改");
        jpm.add(items[0]); //增加菜单项到菜单上
        jpm.add(items[1]); //增加菜单项到菜单上
        items[0].addActionListener(this); //菜单项事件处理
        items[1].addActionListener(this); //菜单项事件处理


        jr1.setSelected(true);
        this.setSize(800,600);
        this.setLocation(250,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        jList.addMouseListener(this);
        defaultListModel.addElement("  PID   |   名称   |   大小  ");
        JScrollPane jScrollPane=new JScrollPane(jList);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(jrb1);
        buttonGroup.add(jrb2);
        buttonGroup.add(jrb3);
        buttonGroup.add(jrb4);
        jrb1.addItemListener(this);
        jrb2.addItemListener(this);
        jrb3.addItemListener(this);
        jrb4.addItemListener(this);

        ButtonGroup buttonGroup1=new ButtonGroup();
        buttonGroup1.add(jr1);
        buttonGroup1.add(jr2);
        buttonGroup1.add(jr3);
        buttonGroup1.add(jr4);
        jr1.addItemListener(this);
        jr2.addItemListener(this);
        jr3.addItemListener(this);
        jr4.addItemListener(this);

        ButtonGroup buttonGroup2=new ButtonGroup();
        buttonGroup2.add(user1);
        user1.setSelected(true);
        buttonGroup2.add(user2);
        user1.addItemListener(this);
        user2.addItemListener(this);

        JLabel jl1=new JLabel("pid");
        JLabel jl2=new JLabel("name");
        JLabel jl3=new JLabel("size");
        JLabel jl4=new JLabel("time");
        JLabel jl5=new JLabel("right");
        JLabel jl6=new JLabel("选择调度算法:");
        JLabel jl7=new JLabel("begin");
        JPanel processDisplay=new JPanel();
        JPanel options=new JPanel();

        JPanel RAM=new JPanel();
        JPanel tmp=new JPanel();
        JPanel tmp2=new JPanel();

        //processDisplay.setBorder(BorderFactory.createTitledBorder("运行区"));
        options.setBorder(BorderFactory.createTitledBorder("输入区"));
        list.setBorder(BorderFactory.createTitledBorder("列表区"));
        //RAM.setBorder(BorderFactory.createTitledBorder("内存区"));
        tmp.setBorder(BorderFactory.createTitledBorder("内存算法"));
        tmp2.setBorder(BorderFactory.createTitledBorder("多用户选择"));

        JScrollPane jScrollPane11=new JScrollPane(jTextArea);
        jScrollPane11.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane jScrollPane12=new JScrollPane(jTextArea1);
        jScrollPane12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane jScrollPane13=new JScrollPane(memoryta);
        jScrollPane13.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jScrollPane11.setBorder(BorderFactory.createTitledBorder("运行区"));
        jScrollPane12.setBorder(BorderFactory.createTitledBorder("内存区"));
        jScrollPane13.setBorder(BorderFactory.createTitledBorder("内存表"));

        GridBagConstraints gridBagConstraints1=new GridBagConstraints();//布局
        gridBagConstraints1.fill=GridBagConstraints.BOTH;
        GridBagLayout gridBagLayout1=new GridBagLayout();

        this.setLayout(gridBagLayout1);

        gridBagConstraints1.insets=new Insets(5,5,10,10);

        gridBagConstraints1.gridx=0;
        gridBagConstraints1.gridy=0;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=2;
        gridBagConstraints1.weightx=0.1;
        gridBagConstraints1.weighty=0.1;
        gridBagLayout1.setConstraints(jScrollPane11,gridBagConstraints1);

        gridBagConstraints1.gridx=0;
        gridBagConstraints1.gridy=2;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=1;
        gridBagConstraints1.weightx=0.1;
        gridBagConstraints1.weighty=0.1;
        gridBagLayout1.setConstraints(jScrollPane12,gridBagConstraints1);

        gridBagConstraints1.gridx=1;
        gridBagConstraints1.gridy=2;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=1;
        gridBagConstraints1.weightx=0.1;
        gridBagConstraints1.weighty=0.1;
        gridBagLayout1.setConstraints(jScrollPane13,gridBagConstraints1);

        gridBagConstraints1.gridx=2;
        gridBagConstraints1.gridy=0;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=2;
        gridBagConstraints1.weightx=0;
        gridBagConstraints1.weighty=0;
        gridBagLayout1.setConstraints(tmp,gridBagConstraints1);

        gridBagConstraints1.gridx=2;
        gridBagConstraints1.gridy=2;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=2;
        gridBagConstraints1.weightx=0;
        gridBagConstraints1.weighty=0;
        gridBagLayout1.setConstraints(tmp2,gridBagConstraints1);

        gridBagConstraints1.gridx=4;
        gridBagConstraints1.gridy=0;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=2;
        gridBagConstraints1.weightx=0;
        gridBagConstraints1.weighty=0;
        gridBagLayout1.setConstraints(options,gridBagConstraints1);

        gridBagConstraints1.gridx=4;
        gridBagConstraints1.gridy=2;
        gridBagConstraints1.gridheight=2;
        gridBagConstraints1.gridwidth=2;
        gridBagConstraints1.weightx=0;
        gridBagConstraints1.weighty=0;
        gridBagLayout1.setConstraints(list,gridBagConstraints1);

        this.add(jScrollPane11);
        this.add(jScrollPane12);
        this.add(jScrollPane13);
        this.add(tmp);
        this.add(tmp2);
        this.add(options);
        this.add(list);

        this.validate();

        GridBagConstraints gridBagConstraints=new GridBagConstraints();//布局
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        GridBagLayout gridBagLayout=new GridBagLayout();
        options.setLayout(gridBagLayout);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl1,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta1,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl2,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta2,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl3,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta3,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl4,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta4,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl5,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta5,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=5;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl7,gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=5;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jta6,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jb1,gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jb2,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=7;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jl6,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=8;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jrb1,gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=8;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(time,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=9;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jrb2,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=10;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jrb3,gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=11;
        gridBagConstraints.gridheight=1;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(jrb4,gridBagConstraints);

        options.add(jl1);
        options.add(jta1);
        options.add(jl2);
        options.add(jta2);
        options.add(jl3);
        options.add(jta3);
        options.add(jl4);
        options.add(jta4);
        options.add(jl5);
        options.add(jta5);
        options.add(jl7);
        options.add(jta6);
        options.add(jb1);
        options.add(jb2);
        options.add(jl6);
        options.add(jrb1);
        options.add(time);
        options.add(jrb2);
        options.add(jrb3);
        options.add(jrb4);

        GridBagConstraints gridBagConstraints2=new GridBagConstraints();//布局
        gridBagConstraints2.fill=GridBagConstraints.BOTH;
        GridBagLayout gridBagLayout2=new GridBagLayout();
        gridBagConstraints2.insets=new Insets(2,5,0,2);
        list.setLayout(gridBagLayout2);

        gridBagConstraints2.gridx=0;
        gridBagConstraints2.gridy=0;
        gridBagConstraints2.gridheight=4;
        gridBagConstraints2.gridwidth=4;
        gridBagConstraints2.weightx=0;
        gridBagConstraints2.weighty=0.1;
        gridBagLayout2.setConstraints(jScrollPane,gridBagConstraints2);

        gridBagConstraints2.gridx=0;
        gridBagConstraints2.gridy=4;
        gridBagConstraints2.gridheight=1;
        gridBagConstraints2.gridwidth=2;
        gridBagConstraints2.weightx=0;
        gridBagConstraints2.weighty=0;
        gridBagLayout2.setConstraints(jb3,gridBagConstraints2);

        gridBagConstraints2.gridx=2;
        gridBagConstraints2.gridy=4;
        gridBagConstraints2.gridheight=1;
        gridBagConstraints2.gridwidth=1;
        gridBagConstraints2.weightx=0;
        gridBagConstraints2.weighty=0;
        gridBagLayout2.setConstraints(jb4,gridBagConstraints2);

        gridBagConstraints2.gridx=3;
        gridBagConstraints2.gridy=4;
        gridBagConstraints2.gridheight=1;
        gridBagConstraints2.gridwidth=1;
        gridBagConstraints2.weightx=0;
        gridBagConstraints2.weighty=0;
        gridBagLayout2.setConstraints(jb5,gridBagConstraints2);

        list.add(jScrollPane);
        list.add(jb3);
        list.add(jb4);
        list.add(jb5);

        /*GridBagConstraints gridBagConstraints3=new GridBagConstraints();//布局
        gridBagConstraints3.fill=GridBagConstraints.BOTH;
        GridBagLayout gridBagLayout3=new GridBagLayout();
        gridBagConstraints3.insets=new Insets(2,5,0,2);
        tmp.setLayout(gridBagLayout3);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=0;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(jr1,gridBagConstraints3);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=1;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(jr2,gridBagConstraints3);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=2;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(jr3,gridBagConstraints3);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=3;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(jr4,gridBagConstraints3);*/
        tmp.setLayout(new GridLayout(4,1));

        tmp.add(jr1);
        tmp.add(jr2);
        tmp.add(jr3);
        tmp.add(jr4);

        /*GridBagConstraints gridBagConstraints4=new GridBagConstraints();//布局
        //gridBagConstraints4.fill=GridBagConstraints.BOTH;
        GridBagLayout gridBagLayout4=new GridBagLayout();
        gridBagConstraints4.insets=new Insets(2,5,0,2);
        tmp2.setLayout(gridBagLayout4);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=0;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(user1,gridBagConstraints3);

        gridBagConstraints3.gridx=0;
        gridBagConstraints3.gridy=1;
        gridBagConstraints3.gridheight=1;
        gridBagConstraints3.gridwidth=2;
        gridBagConstraints3.weightx=0;
        gridBagConstraints3.weighty=0;
        gridBagLayout2.setConstraints(user2,gridBagConstraints3);*/
        tmp2.setLayout(new GridLayout(2,1));

        tmp2.add(user1);
        tmp2.add(user2);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb5.addActionListener(this);
        this.validate();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(jb1)){
            if(user==1) {
                newReady(new PID(Integer.parseInt(jta1.getText()), jta2.getText(), Integer.parseInt(jta3.getText()), Integer.parseInt(jta4.getText()), Integer.parseInt(jta5.getText()), Integer.parseInt(jta6.getText()), ramid++), readyHead);
                defaultListModel.addElement(user+"|"+jta1.getText()+"       "+jta2.getText()+"       "+jta3.getText()+"  ");
            }
            else if(user==2) {
                newReady(new PID(Integer.parseInt(jta1.getText()), jta2.getText(), Integer.parseInt(jta3.getText()), Integer.parseInt(jta4.getText()), Integer.parseInt(jta5.getText()), Integer.parseInt(jta6.getText()), ramid++), readyHead2);
                defaultListModel.addElement(user+"|"+jta1.getText()+"       "+jta2.getText()+"       "+jta3.getText()+"  ");
            }

            //jTextArea.append("  "+jta1.getText()+"       "+jta2.getText()+"       "+jta3.getText()+"  "+"\n");
            jta1.setText("");
            jta2.setText("");
            jta3.setText("");
            jta4.setText("");
            jta5.setText("");
            jta6.setText("");
        }
        else if(e.getSource().equals(jb2)){
            jta1.setText("");
            jta2.setText("");
            jta3.setText("");
            jta4.setText("");
            jta5.setText("");
            jta6.setText("");
        }
        else if(e.getSource().equals(jb3)){
            jTextArea.append("启动运行\n");
            myThread myThread1=new myThread();
            myThread1.start();
        }
        else if(e.getSource().equals(jb4)){
            //defaultListModel.removeAllElements();
            //defaultListModel.addElement("  PID   |   名称   |   大小  ");
            op=1;
        }
        else if(e.getSource().equals(jb5)){
            defaultListModel.removeAllElements();
            defaultListModel.addElement("  PID   |   名称   |   大小  ");
        }
        else if (e.getSource()==items[0]) {
            String t=(String)defaultListModel.getElementAt(index);
            int t1=t.indexOf(" ");
            int t2=t.indexOf("|");
            String uu=t.substring(0,t2);
            String ii=t.substring(t2+1,t1);
            int fdsfds=Integer.parseInt(ii);

            if(uu.equals("1")){
                pid_List tmpppp=readyHead.next;
                while(tmpppp!=null){
                    if(tmpppp.thisPid.pid==fdsfds) {
                        JOptionPane.showMessageDialog(null, "起始地址：" + tmpppp.thisPid.begin + "\n结束地址：" + (tmpppp.thisPid.begin + tmpppp.thisPid.size), "物理内存", JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                     tmpppp=tmpppp.next;
                }
            }
            else if(uu.equals("2")){
                pid_List tmpppp=readyHead2.next;
                while(tmpppp!=null){
                    if(tmpppp.thisPid.pid==fdsfds){
                        JOptionPane.showMessageDialog(null,"起始地址："+tmpppp.thisPid.begin+"\n结束地址："+(tmpppp.thisPid.begin+tmpppp.thisPid.size),"物理内存",JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                    tmpppp=tmpppp.next;
                }
            }
        }
        else if (e.getSource()==items[1]) {
            //修改，弹出一个jpanel显示
            jpopuppanel=initPCBItemPanel();
            jf.setSize(200,200);
            jf.setLocation(400,200);
            jf.add(jpopuppanel);
            jf.setVisible(true);
            button1.addActionListener(this);
            button2.addActionListener(this);
        }
        else if(e.getSource()== button1) {
            String t=(String)defaultListModel.getElementAt(index);
            int t1=t.indexOf(" ");
            int t2=t.indexOf("|");
            String uu=t.substring(0,t2);
            String ii=t.substring(t2+1,t1);
            int fdsfds=Integer.parseInt(ii);

            if(uu.equals("1")){
                pid_List tmpppp=readyHead.next;
                while(tmpppp!=null){
                    if(tmpppp.thisPid.pid==fdsfds) {
                        PID v=tmpppp.thisPid;
                        v.pid=Integer.parseInt(PIDtf.getText());
                        v.name=Nametf.getText();
                        //v.size=Integer.parseInt(Sizetf.getText());
                        v.time=Integer.parseInt(Timetf.getText());
                        v.right=Integer.parseInt(Prioritytf.getText());
                        //v.begin=Integer.parseInt(Basetf.getText());
                        jf.dispose();
                        defaultListModel.remove(index);
                        defaultListModel.addElement(uu+"|"+v.pid+"       "+v.name+"       "+v.size+"  ");
                        break;
                    }
                    tmpppp=tmpppp.next;
                }
            }
            else if(uu.equals("2")){
                pid_List tmpppp=readyHead2.next;
                while(tmpppp!=null){
                    if(tmpppp.thisPid.pid==fdsfds){
                        PID v=tmpppp.thisPid;
                        v.pid=Integer.parseInt(PIDtf.getText());
                        v.name=Nametf.getText();
                        //v.size=Integer.parseInt(Sizetf.getText());
                        v.time=Integer.parseInt(Timetf.getText());
                        v.right=Integer.parseInt(Prioritytf.getText());
                        //v.begin=Integer.parseInt(Basetf.getText());
                        jf.dispose();
                        defaultListModel.remove(index);
                        defaultListModel.addElement(uu+"|"+v.pid+"       "+v.name+"       "+v.size+"  ");
                        break;
                    }
                    tmpppp=tmpppp.next;
                }
            }
            //修改对应pcb的数据
        }
        else if(e.getSource()== button2) {
            jf.dispose();
        }
    }

    public void itemStateChanged(ItemEvent e){
        if(e.getSource().equals(jrb1)){
            argStste=1;
            times=Integer.parseInt(time.getText());
        }
        else if(e.getSource().equals(jrb2)){argStste=2;}
        else if(e.getSource().equals(jrb3)){argStste=3;}
        else if(e.getSource().equals(jrb4)){argStste=4;}
        else if(e.getSource().equals(jr1)){armstate=1;}
        else if(e.getSource().equals(jr2)){armstate=2;}
        else if(e.getSource().equals(jr3)){armstate=3;}
        else if(e.getSource().equals(jr4)){armstate=4;}
        else if(e.getSource().equals(user1)){user=1;}
        else if(e.getSource().equals(user2)){user=2;}
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            // 弹出菜单
            jpm.show(list, e.getX(), e.getY());
            index=jList.locationToIndex(e.getPoint());
        }
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }



    public JPanel initPCBItemPanel() {
        JPanel iniPCBItemJPanel = new JPanel(new BorderLayout());
        JPanel iniNamePanel = new JPanel(new GridLayout(6, 1));
        JPanel iniValuePanel = new JPanel(new GridLayout(6, 1));
        JPanel iniButtonPanel = new JPanel(new GridLayout(1, 2));

        button1 =new JButton("确定");
        button2 =new JButton("取消");
        PIDLabel = new JLabel("PID:");
        NameLabel = new JLabel("Name:");
        SizeLabel = new JLabel("Size:");
        TimeLabel = new JLabel("Time:");
        PriorityLabel = new JLabel("Priority:");
        BaseLabel = new JLabel("Base:");

        PIDLabel.setFont(new Font("宋体",Font.BOLD,10));
        NameLabel.setFont(new Font("宋体",Font.BOLD,10));
        SizeLabel.setFont(new Font("宋体",Font.BOLD,10));
        TimeLabel.setFont(new Font("宋体",Font.BOLD,10));
        PriorityLabel.setFont(new Font("宋体",Font.BOLD,10));
        BaseLabel.setFont(new Font("宋体",Font.BOLD,10));

        iniNamePanel.add(PIDLabel);
        iniNamePanel.add(NameLabel);
        iniNamePanel.add(SizeLabel);
        iniNamePanel.add(TimeLabel);
        iniNamePanel.add(PriorityLabel);
        iniNamePanel.add(BaseLabel);

        PIDtf = new JTextField("", 10);
        PIDtf.setEditable(true);
        Nametf = new JTextField("", 10);
        Nametf.setEditable(true);
        Sizetf = new JTextField("", 10);
        Sizetf.setEditable(false);
        Timetf = new JTextField("", 10);
        Timetf.setEditable(true);
        Prioritytf = new JTextField("", 10);
        Prioritytf.setEditable(true);
        Basetf = new JTextField("", 10);
        Basetf.setEditable(false);

        PIDtf.setFont(new Font("宋体",Font.BOLD,10));
        Nametf.setFont(new Font("宋体",Font.BOLD,10));
        Sizetf.setFont(new Font("宋体",Font.BOLD,10));
        Timetf.setFont(new Font("宋体",Font.BOLD,10));
        Prioritytf .setFont(new Font("宋体",Font.BOLD,10));
        Basetf.setFont(new Font("宋体",Font.BOLD,10));

        iniValuePanel.add(PIDtf);
        iniValuePanel.add(Nametf);
        iniValuePanel.add(Sizetf);
        iniValuePanel.add(Timetf);
        iniValuePanel.add(Prioritytf);
        iniValuePanel.add(Basetf);

        iniButtonPanel.add(button1);
        iniButtonPanel.add(button2);
        button1.setFont(new Font("宋体",Font.BOLD,10));
        button2.setFont(new Font("宋体", Font.BOLD,10));

        iniPCBItemJPanel.add(iniNamePanel, BorderLayout.WEST);
        iniPCBItemJPanel.add(iniValuePanel, BorderLayout.CENTER);
        iniPCBItemJPanel.add(iniButtonPanel, BorderLayout.SOUTH);
        return iniPCBItemJPanel;
    }//面板函数

    public class PID {
        int pid, time, size, right,begin,rid;
        String name;

        public PID(int pid,String name,int size,int time, int right,int begin,int rid) {
            this.pid = pid;
            this.time = time;
            this.size = size;
            this.right = right;
            this.name = name;
            this.begin=begin;
            this.rid=rid;
        }
    }

    public class myThread extends Thread {

        public void run() {
            pid_List head=null;
            if(user==1)
                head=readyHead;
            else if(user==2)
                head=readyHead2;
            try {
                if (argStste == 1) {
                    timeSlice(times,head);
                } else if (argStste == 2) {
                    SJF(head);
                } else if (argStste == 3) {
                    Priority(head);
                } else if (argStste == 4) {
                    FCFS(head);
                }
            }catch(Exception e){}
        }
    }  //进程并发执行类

    class pid_List {
        pid_List before, next;
        PID thisPid;
    }//pid链

    pid_List readyHead =new pid_List();//就绪队列
    pid_List readyHead2 =new pid_List();//就绪队列
    pid_List backupHead=new pid_List();//后备队列
    pid_List suspendHead=new pid_List();//挂起队列

    void selectSuspend(pid_List selectOne){
        pid_List tmp=readyHead.next;
        while(tmp!=selectOne&&tmp!=null){
            tmp=tmp.next;
        }
        pid_List tmp1=tmp.before;
        pid_List tmp2=tmp.next;
        tmp1.next=tmp2;
        tmp2.before=tmp1;
        newSuspend(tmp.thisPid);
    }

    fdf n=new fdf();

    void newReady(PID newPid,pid_List head) {
        if (armstate == 1){
            n.firstFit(newPid.size);
            n.showZones();
        }
        else if(armstate==2){
            n.nextFit(newPid.size);
            n.showZones();
        }
        else if(armstate==3) {
            n.bestFit(newPid.size);
            n.showZones();
        }
        else if(armstate==4){
            n.worstFit(newPid.size);
            n.showZones();
        }
        pid_List tmp= head;
        while(tmp.next!=null){
            tmp=tmp.next;
        }
        tmp.next=new pid_List();
        tmp.next.before=tmp;
        tmp=tmp.next;
        tmp.next=null;
        tmp.thisPid=newPid;
    }

    void newSuspend(PID newPid) {
        pid_List tmp= suspendHead;
        while(tmp.next!=null){
            tmp=tmp.next;
        }
        tmp.next=new pid_List();
        tmp.next.before=tmp;
        tmp=tmp.next;
        tmp.next=null;
        tmp.thisPid=newPid;
    }

    void newBackup(PID newPid) {
        pid_List tmp= backupHead;
        while(tmp.next!=null){
            tmp=tmp.next;
        }
        tmp.next=new pid_List();
        tmp.next.before=tmp;
        tmp=tmp.next;
        tmp.next=null;
        tmp.thisPid=newPid;
    }

    void timeSlice(int time,pid_List head) throws Exception{
        String a=null;
        if(head==readyHead)
            a="用户一";
        else if(head==readyHead2)
            a="用户二";
        while (head.next != null) {
            if (head.next.thisPid.time > time) {
                head.next.thisPid.time -= time;
                jTextArea.append(a+" "+head.next.thisPid.name+"\n");
                Thread.sleep(time*1000);
                //这里做传出信号
            } else {
                if(head.next.next==null||head.next.thisPid.time+head.next.next.thisPid.time<time){
                    if(head.next.next==null){
                        n.collection(head.next.thisPid.rid);
                        pid_List tmp=head.next;
                        head.next=null;
                        n.showZones();
                        Thread.sleep(time * 1000);
                        if (coll == 1) {
                            pid_List tt = readyHead.next;
                            while (tt != null) {
                                if (tt.thisPid.rid > tmp.thisPid.rid)
                                    tt.thisPid.rid--;
                                tt = tt.next;
                            }
                            tt = readyHead2.next;
                            while (tt != null) {
                                if (tt.thisPid.rid > tmp.thisPid.rid)
                                    tt.thisPid.rid--;
                                tt = tt.next;
                            }
                            coll = 0;
                        } else if (coll == 2) {
                            pid_List tt = readyHead.next;
                            while (tt != null) {
                                if (tt.thisPid.rid > tmp.thisPid.rid)
                                    tt.thisPid.rid--;
                                tt = tt.next;
                            }
                            tt = readyHead2.next;
                            while (tt != null) {
                                if (tt.thisPid.rid > tmp.thisPid.rid)
                                    tt.thisPid.rid--;
                                tt = tt.next;
                            }
                            coll = 0;
                        }
                    }
                    else if(head.next.thisPid.time+head.next.next.thisPid.time<time){

                    }
                }
                else {
                    pid_List tmp = head.next;
                    head.next = tmp.next;
                    tmp.next = null;
                    jTextArea.append(a + " " + tmp.thisPid.name + "\n");
                    head.next.thisPid.time -= (time - tmp.thisPid.time);
                    head.next.before = head;
                    jTextArea.append(a + " " + head.next.thisPid.name + "\n");
                    n.collection(tmp.thisPid.rid);
                    n.showZones();
                    Thread.sleep(time * 1000);
                    if (coll == 1) {
                        pid_List tt = readyHead.next;
                        while (tt != null) {
                            if (tt.thisPid.rid > tmp.thisPid.rid)
                                tt.thisPid.rid--;
                            tt = tt.next;
                        }
                        tt = readyHead2.next;
                        while (tt != null) {
                            if (tt.thisPid.rid > tmp.thisPid.rid)
                                tt.thisPid.rid--;
                            tt = tt.next;
                        }
                        coll = 0;
                    } else if (coll == 2) {
                        pid_List tt = readyHead.next;
                        while (tt != null) {
                            if (tt.thisPid.rid > tmp.thisPid.rid)
                                tt.thisPid.rid--;
                            tt = tt.next;
                        }
                        tt = readyHead2.next;
                        while (tt != null) {
                            if (tt.thisPid.rid > tmp.thisPid.rid)
                                tt.thisPid.rid--;
                            tt = tt.next;
                        }
                        coll = 0;
                    }
                }
            }
            if(op==1){
                op=0;
                break;
            }
        }
        jTextArea.append("运行结束\n");
    }//时间片轮转

    void SJF(pid_List head) throws Exception{
        String a1=null;
        if(head==readyHead)
            a1="用户一";
        else if(head==readyHead2)
            a1="用户二";
        while(head.next!=null) {
            pid_List tmp2 = head.next;
            pid_List tmp1 = tmp2.next;
            while (tmp1!= null) {
                if (tmp1.thisPid.time < tmp2.thisPid.time){
                    tmp2 = tmp1;
                    tmp1=tmp1.next;
                }
                else
                    tmp1 = tmp1.next;
            }//选取时间最少的
            //预留接口供frame使用
            pid_List a, b;
            a = tmp2.before;
            b = tmp2.next;
            if(b!=null) {
                a.next = b;
                b.before = a;
                tmp2.before = null;
                tmp2.next = null;
            }
            else{
                a.next=null;
                tmp2.before = null;
                tmp2.next = null;
            }
            n.collection(tmp2.thisPid.rid);
            n.showZones();
            if(coll==1){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            else if(coll==2){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            jTextArea.append(a1+" "+tmp2.thisPid.name+"\n");
            Thread.sleep(tmp2.thisPid.time*1000);
            if(op==1){
                op=0;
                break;
            }
        }
        jTextArea.append("运行结束\n");
    }//最短作业优先

    public void Priority(pid_List head) throws Exception{
        String a1=null;
        if(head==readyHead)
            a1="用户一";
        else if(head==readyHead2)
            a1="用户二";
        while(head.next!=null) {
            pid_List tmp2 = head.next;
            pid_List tmp1 = tmp2.next;
            while (tmp1!= null) {
                if (tmp1.thisPid.right < tmp2.thisPid.right){
                    tmp2 = tmp1;
                    tmp1=tmp1.next;
                }
                else
                    tmp1 = tmp1.next;
            }//选取时间最少的
            //预留接口供frame使用
            pid_List a, b;
            a = tmp2.before;
            b = tmp2.next;
            if(b!=null) {
                a.next = b;
                b.before = a;
                tmp2.before = null;
                tmp2.next = null;
            }
            else{
                a.next=null;
                tmp2.before = null;
                tmp2.next = null;
            }
            n.collection(tmp2.thisPid.rid);
            n.showZones();
            if(coll==1){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            else if(coll==2){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp2.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            jTextArea.append(a1+" "+tmp2.thisPid.name+"\n");
            Thread.sleep(tmp2.thisPid.time*1000);
            if(op==1){
                op=0;
                break;
            }
        }
        jTextArea.append("运行结束\n");
    }//优先权

    public void FCFS(pid_List head) throws Exception{
        String a=null;
        if(head==readyHead)
            a="用户一";
        else if(head==readyHead2)
            a="用户二";
        while (head.next != null) {
            pid_List tmp = head.next;
            head.next = tmp.next;
            n.collection(tmp.thisPid.rid);
            n.showZones();
            jTextArea.append(a+" "+tmp.thisPid.name+"\n");
            Thread.sleep(tmp.thisPid.time*1000);
            if(coll==1){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            else if(coll==2){
                pid_List tt=readyHead.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                tt=readyHead2.next;
                while(tt!=null){
                    if(tt.thisPid.rid>tmp.thisPid.rid)
                        tt.thisPid.rid--;
                    tt=tt.next;
                }
                coll=0;
            }
            if(op==1){
                op=0;
                break;
            }
        }
        jTextArea.append("运行结束\n");
    }//先来先服务

    public class fdf {
        // 内存大小
        private int size;
        // 最小剩余分区大小
        private static final int MIN_SIZE = 5;
        //内存分区
        private LinkedList<Zone> zones;
        //上次分配的空闲区位置
        private int pointer;

        //分区节点类
        class Zone {
            //分区大小
            private int size;
            //分区始址
            private int head;
            //空闲状态
            private boolean isFree;
            int num;

            public Zone(int head, int size) {
                this.head = head;
                this.size = size;
                this.isFree = true;
            }
        }

        fdf() {
            this.size = 5000;
            this.pointer = 0;
            this.zones = new LinkedList<>();
            zones.add(new Zone(0, size));
        }
        //首次适应
        private void firstFit(int size) {
            //遍历分区链表
            for (pointer = 0; pointer < zones.size(); pointer++) {
                Zone tmp = zones.get(pointer);
                //找到可用分区（空闲且大小足够）
                if (tmp.isFree && (tmp.size > size)) {
                    doAllocation(size, pointer, tmp);
                    return;
                }
            }
            //遍历结束后未找到可用分区, 则内存分配失败
            jTextArea1.append("无可用内存空间!\n");
        }

        //循环首次适应
        private void nextFit(int size) {
            //从上次分配空闲区位置开始遍历分区链表
            Zone tmp = zones.get(pointer);
            if (tmp.isFree && (tmp.size > size)) {
                doAllocation(size, pointer, tmp);
                return;
            }
            int len = zones.size();
            int i = (pointer + 1) % len;
            for (; i != pointer; i = (i + 1) % len) {
                tmp = zones.get(i);
                //找到可用分区（空闲且大小足够）
                if (tmp.isFree && (tmp.size > size)) {
                    doAllocation(size, i, tmp);
                    return;
                }
            }
            //遍历结束后未找到可用分区, 则内存分配失败
            jTextArea1.append("无可用内存空间!\n");
        }

        //最佳适应算法
        private void bestFit(int size) {
            int flag = -1;
            int min = this.size;
            for (pointer = 0; pointer < zones.size(); pointer++) {
                Zone tmp = zones.get(pointer);
                if (tmp.isFree && (tmp.size > size)) {
                    if (min > tmp.size - size) {
                        min = tmp.size - size;
                        flag = pointer;
                    }
                }
            }
            if (flag == -1) {
                jTextArea1.append("无可用内存空间!\n");
            } else {
                doAllocation(size, flag, zones.get(flag));
            }
        }

        //最坏适应
        private void worstFit(int size) {
            int flag = -1;
            int max = 0;
            for (pointer = 0; pointer < zones.size(); pointer++) {
                Zone tmp = zones.get(pointer);
                if (tmp.isFree && (tmp.size > size)) {
                    if (max < tmp.size - size) {
                        max = tmp.size - size;
                        flag = pointer;
                    }
                }
            }
            if (flag == -1) {
                jTextArea1.append("无可用内存空间!\n");
            } else {
                doAllocation(size, flag, zones.get(flag));
            }
        }

        //执行分配
        private void doAllocation(int size, int location, Zone tmp) {
            //如果分割后分区剩余大小过小（MIN_SIZE）则将分区全部分配，否则分割为两个分区
            if (tmp.size - size <= MIN_SIZE) {
                tmp.isFree = false;
            } else {
                Zone split = new Zone(tmp.head + size, tmp.size - size);
                zones.add(location + 1, split);
                tmp.size = size;
                tmp.isFree = false;
            }
            jTextArea1.append("成功分配 " + size + "KB 内存!\n");
        }

        //内存回收
        public void collection(int id) {
            if (id >= zones.size()) {
                jTextArea1.append("无此分区编号!\n");
                return;
            }
            Zone tmp = zones.get(id);
            int size = tmp.size;
            if (tmp.isFree) {
                jTextArea1.append("指定分区未被分配, 无需回收\n");
                return;
            }
            //如果回收分区不是尾分区且后一个分区为空闲, 则与后一个分区合并
            if (id < zones.size() - 1 && zones.get(id + 1).isFree) {
                coll=1;
                Zone next = zones.get(id + 1);
                tmp.size += next.size;
                zones.remove(next);
            }
            //如果回收分区不是首分区且前一个分区为空闲, 则与前一个分区合并
            if (id > 0 && zones.get(id - 1).isFree) {
                coll=2;
                Zone previous = zones.get(id - 1);
                previous.size += tmp.size;
                zones.remove(id);
                id--;;
            }
            zones.get(id).isFree = true;
            jTextArea1.append("内存回收成功!, 本次回收了 " + size + "KB 空间!\n");
        }

        //打印分区表
        public void showZones() {
            memoryta.setText("");
            memoryta.append("序号    始址    大小    是否空闲\n");
            for (int i = 0; i < zones.size(); i++){
                Zone tmp = zones.get(i);
                memoryta.append(i + "           " + tmp.head + "           " +
                        tmp.size + "           " + tmp.isFree+"\n");
            }
        }
    }

    public static void main(String str[]){
        new Frame();
    }
}

