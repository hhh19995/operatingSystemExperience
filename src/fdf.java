
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


/*内存表，并在界面上显示出来，每进来一个进程就根据这个进程的大小和某种算法分配一块内存，改变内存表*/
public class fdf extends JFrame{
    // 内存大小
    private int size;
    // 最小剩余分区大小
    private static final int MIN_SIZE = 5;
    //内存分区
    private LinkedList<Zone> zones;
    //上次分配的空闲区位置
    private int pointer;
    //分区节点类
    class Zone{
        //分区大小
        private int size;
        //分区始址
        private int head;
        //空闲状态
        private boolean isFree;
        public Zone(int head, int size) {
            this.head = head;
            this.size = size;
            this.isFree = true;
        }
    }
    private JPanel memoryp=new JPanel();
    private JTextArea memoryta=new JTextArea();
    fdf(){
        this.size = 5000;
        this.pointer = 0;
        this.zones = new LinkedList<>();
        zones.add(new Zone(0, size));
        memoryta.setFont(new Font("宋体",Font.BOLD,10));
        memoryta.setEditable(false);
        memoryp.setBorder(BorderFactory.createTitledBorder(null,
                "内存分配表",TitledBorder.LEFT,TitledBorder.TOP,new java.awt.Font("宋体",Font.BOLD,30)));
        memoryp.setLayout(new BorderLayout());
        memoryp.add(memoryta);
        //进度条
        JScrollPane jsp = new JScrollPane(memoryta);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        memoryp.add(jsp);
        this.add(memoryp);
        this.setSize(1000, 1000);
        this.setLocation(800,200);
        this.setDefaultCloseOperation((EXIT_ON_CLOSE));
        this.setVisible(true);
    }
    //首次适应
    private void firstFit(int size){
        //遍历分区链表
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            //找到可用分区（空闲且大小足够）
            if (tmp.isFree && (tmp.size > size)){
                doAllocation(size, pointer, tmp);
                return;
            }
        }
        //遍历结束后未找到可用分区, 则内存分配失败
        System.out.println("无可用内存空间!");
    }

    //循环首次适应
    private void nextFit(int size){
        //从上次分配空闲区位置开始遍历分区链表
        Zone tmp = zones.get(pointer);
        if (tmp.isFree && (tmp.size > size)){
            doAllocation(size, pointer, tmp);
            return;
        }
        int len = zones.size();
        int i = (pointer + 1) % len;
        for (; i != pointer; i = (i+1) % len){
            tmp = zones.get(i);
            //找到可用分区（空闲且大小足够）
            if (tmp.isFree && (tmp.size > size)){
                doAllocation(size, i, tmp);
                return;
            }
        }
        //遍历结束后未找到可用分区, 则内存分配失败
        System.out.println("无可用内存空间!");
    }
    //最佳适应算法
    private void bestFit(int size){
        int flag = -1;
        int min = this.size;
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            if (tmp.isFree && (tmp.size > size)){
                if (min > tmp.size - size){
                    min = tmp.size - size;
                    flag = pointer;
                }
            }
        }
        if (flag == -1){
            System.out.println("无可用内存空间!");
        }else {
            doAllocation(size, flag, zones.get(flag));
        }
    }
    //最坏适应
    private void worstFit(int size){
        int flag = -1;
        int max = 0;
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            if (tmp.isFree && (tmp.size > size)){
                if (max < tmp.size - size){
                    max = tmp.size - size;
                    flag = pointer;
                }
            }
        }
        if (flag == -1){
            System.out.println("无可用内存空间!");
        }else {
            doAllocation(size, flag, zones.get(flag));
        }
    }

    //执行分配
    private void doAllocation(int size, int location, Zone tmp) {
        //如果分割后分区剩余大小过小（MIN_SIZE）则将分区全部分配，否则分割为两个分区
        if (tmp.size - size <= MIN_SIZE){
            tmp.isFree = false;
        } else {
            Zone split = new Zone(tmp.head + size, tmp.size - size);
            zones.add(location + 1, split);
            tmp.size = size;
            tmp.isFree = false;
        }
        System.out.println("成功分配 " + size + "KB 内存!");
    }

    //内存回收
    public void collection(int id){
        if (id >= zones.size()){
            System.out.println("无此分区编号!");
            return;
        }
        Zone tmp = zones.get(id);
        int size = tmp.size;
        if (tmp.isFree) {
            System.out.println("指定分区未被分配, 无需回收");
            return;
        }
        //如果回收分区不是尾分区且后一个分区为空闲, 则与后一个分区合并
        if (id < zones.size() - 1 && zones.get(id + 1).isFree){
            Zone next = zones.get(id + 1);
            tmp.size += next.size;
            zones.remove(next);
        }
        //如果回收分区不是首分区且前一个分区为空闲, 则与前一个分区合并
        if (id > 0 && zones.get(id - 1).isFree){
            Zone previous = zones.get(id - 1);
            previous.size += tmp.size;
            zones.remove(id);
            id--;
        }
        zones.get(id).isFree = true;
        System.out.println("内存回收成功!, 本次回收了 " + size + "KB 空间!");
    }

    //打印分区表
    public void showZones(){
        memoryta.setText("");
        memoryta.append("序号    始址    大小    是否空闲\n");
        for (int i = 0; i < zones.size(); i++){
            Zone tmp = zones.get(i);
            memoryta.append(i + "\t" + tmp.head + "\t" +
                    tmp.size + "\t" + tmp.isFree+"\n");
        }
    }
    public static void main(String[] args)throws Exception{
        fdf ma=new fdf();
        ma.firstFit(100);
        ma.showZones();
        Thread.sleep(1000);
        ma.firstFit(100);
        ma.showZones();
        Thread.sleep(1000);
        ma.firstFit(100);
        ma.showZones();
        ma.collection(2);
        ma.firstFit(50);
        ma.showZones();
        Thread.sleep(1000);
        ma.bestFit(50);
        ma.showZones();
        Thread.sleep(1000);
    }
}
