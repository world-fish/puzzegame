package com.game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //记录图片的位置
    int[][] data = new int[4][4];
    //记录空白块儿在二维数组中的位置
    int x,y;
    //记录当前展示图片的路径
    String path = "..\\puzzlegame\\image\\girl\\girl5\\";
    //定义一个二位数组，储存正确答案
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };
    //定义计数器,用来统计步数
    int step = 0;
    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    //构造方法，构造游戏界面
    public GameJFrame(){
        //初始化界面
        initJFram();

        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱)
        initData();

        //初始化图片
        initImage();

        //设置窗口可见
        setVisible(true);
    }

    //初始化数据(打乱)
    private void initData() {
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = i * 4 + j;
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int r1 = r.nextInt(data.length);
                int r2 = r.nextInt(data[i].length);
                int temp = data[i][j];
                data[i][j] = data[r1][r2];
                data[r1][r2] = temp;
            }
        }
    }

    //初始化图片
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        //判断是否胜利
        if (victory()) {
            //显示胜利图片
            JLabel winJLable = new JLabel(new ImageIcon("..\\puzzlegame\\image\\win.png"));
            winJLable.setBounds(203,283,197,73);
            this.getContentPane().add(winJLable);

            //添加步数
            JLabel stepCount = new JLabel("步数：" + step);
            stepCount.setBounds(50,30,100,20);
            this.getContentPane().add(stepCount);
        }

        //添加步数
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //路径分为两种
        //绝对路径：一定是从盘符开始的
        //相对路径：不是从盘符开始的
        //相对路径是相对当前下个内幕而言的
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                int temp = data[i][j];
                if (temp == 0) {
                    x = i;
                    y = j;
                }
                //创建一个JLabel的对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(path + temp + ".jpg"));
                //指定图片的位置
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                //给图片添加边框:
                //0表示让图片凸出来
                //1表示让图片凹下去
                jLabel.setBorder(new BevelBorder(1));


                //把管理容器添加到页面当中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("../puzzlegame/image/background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);
        //注意：先添加的图片在前面，后添加的图片在后面


        //刷新一下界面
        this.getContentPane().repaint();

    }

    //初始化菜单
    private void initJMenuBar() {
        //初始化菜单
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //常见菜单上面的两个选项的对象
        JMenu functionJM = new JMenu("功能");
        JMenu aboutJM = new JMenu("关于");
        JMenu changeImage = new JMenu("更换图片");


        //将每一个选项下面的条目添加到选项当中
        functionJM.add(changeImage);
        functionJM.add(replayItem);
        functionJM.add(reLoginItem);
        functionJM.add(closeItem);
        aboutJM.add(accountItem);
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);


        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);


        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJM);
        jMenuBar.add(aboutJM);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFram() {
        //设置界面的宽高
        setSize(603,680);
        //设置界面的标题
        setTitle("拼图  单机版 v1.0");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //用户不能调整界面
        setResizable(false);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override//长按按键
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            if (victory()) {
                //结束方法
                return;
            }
            //把界面当中所有图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //添加步数
            JLabel stepCount = new JLabel("步数：" + step);
            stepCount.setBounds(50,30,100,20);
            this.getContentPane().add(stepCount);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("../puzzlegame/image/background.png"));
            background.setBounds(40,40,508,560);
            //把背景图片添加到界面当中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override//松开按键
    public void keyReleased(KeyEvent e) {

        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的代码了
        if (victory()) {
            //结束方法
            return;
        }

        //对上下左右进行判断
        //左：37  上：38  右：39  下：40
        int code = e.getKeyCode();
        int temp;
        if (code == 37) {
            //向左
            temp = y;
            if (y == 0) {
                y = 4;
            }
            data[x][temp] = data[x][y - 1];
            data[x][y - 1] = 0;
            //每进行一步，步数就加一
            step++;
            initImage();
        }else if (code == 38) {
            //向上
            temp = x;
            if (x == 0) {
                x = 4;
            }
            data[temp][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            //每进行一步，步数就加一
            step++;
            initImage();
        }else if (code == 39) {
            //向右
            temp = y;
            if (y == 3) {
                y = -1;
            }
            data[x][temp] = data[x][y + 1];
            data[x][y + 1] = 0;
            //每进行一步，步数就加一
            step++;
            initImage();
        }else if (code == 40) {
            //向下
            temp = x;
            if (x == 3) {
                x = -1;
            }
            data[temp][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            //每进行一步，步数就加一
            step++;
            initImage();
        }else if (code == 65) {
            //松开按键时返回正常游戏界面
            initImage();
        }else if (code == 87) {

            for (int i = 0; i < win.length; i++) {
                for (int j = 0; j < win[i].length; j++) {
                    data[i][j] = win[i][j];
                }
            }
            initImage();
        }
    }

    //判断是否获胜
    public boolean victory(){
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length; j++) {
//                if (data[i][j] != win[i][j]){
//                    return false;
//                }
//            }
//        }
//        return true;
        if (Arrays.equals(data,win)) {
            return true;
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j]);
            }
        }
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(win[i][j]);
            }
        }
        System.out.println();
        return false;
    }

    @Override//点击鼠标左键或空格
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //设置随机数
        Random r = new Random();
        //判断鼠标点击目标
        if (obj == replayItem) {
            //重新游戏
            // 再次打乱数据
            initData();
            //计步器清零
            step = 0;
            //重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            //重新登录
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            //关闭游戏
            System.exit(0);
        } else if (obj == accountItem) {
            //公众号
            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            //给弹窗设置大小
            jDialog.setSize(344,344);
            //创建一个管理图片的容器对象
            JLabel jLabel = new JLabel(new ImageIcon("D:\\puzzlegame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹窗当中
            jDialog.getContentPane().add(jLabel);
            //让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //让弹窗居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法下面的操作
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);
        } else if (obj == girl) {
            step = 0;
            path = "..\\puzzlegame\\image\\girl\\girl" + (r.nextInt(13) + 1) + "\\";
            //初始化数据(打乱)
            initData();
            //重新加载图片
            initImage();
        } else if (obj == animal) {
            step = 0;
            path = "..\\puzzlegame\\image\\animal\\animal" + (r.nextInt(8) + 1) + "\\";
            //初始化数据(打乱)
            initData();
            //重新加载图片
            initImage();
        } else if (obj == sport) {
            step = 0;
            path = "..\\puzzlegame\\image\\sport\\sport" + (r.nextInt(10) + 1) + "\\";
            //初始化数据(打乱)
            initData();
            //重新加载图片
            initImage();
        }
    }
}
