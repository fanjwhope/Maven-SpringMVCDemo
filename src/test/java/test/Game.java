package test;

import java.util.Random;
import java.util.Scanner;

public class Game {
	// 玩家
	public Player player1 = new Player();
	public Player player2 = new Player();
	public boolean flag = true;// true为玩家1
	// 地图数组
	public char[] map = new char[100];

	// 存放道具的数组
	public int[] lucky = { 6, 22, 41, 69, 82 };
	public int[] mines = { 5, 12, 16, 33, 39, 51, 64, 80, 93 };
	public int[] pause = { 9, 26, 60, 92 };
	public int[] tunnel = { 20, 24, 46, 63, 72, 87, 89 };


	// 开始游戏
	/*
	 * 1.选择角色 2.初始化地图地图 3.显示地图 4.摇骰子-->(1摇骰子（交替） 2判断道具 3显示地图 )  5.判断输赢
	 */
	public void start() {
		player1.name = "玩家1";
		player2.name = "玩家2";
		// 选择角色
		choosePlayer();
		System.out.println("欢迎来到飞行棋游戏");
		// 初始化地图
		init();
		// 显示地图
		show();
		//循环摇骰子
		while(true){
			//摇骰子
			rollDice();
			//显示地图
			show();
			flag = !flag;
		}

	}

	//判断道具
	public int props(int index){
		//接收当前位置的值
		char ch = map[index];
		switch(ch){
			//地雷
			case '★':
				//去掉走过的道具
				map[index]='∷';
				if(index>=5){
					index -= 6;
				}else{
					index = -1;
				}
				System.out.println("踩到地雷，退后6步");
				break;
			//幸运轮盘
			case '◎':
				//去掉走过的道具
				map[index]='∷';
				System.out.println("提供两种选择: 1.跟对方交换位置   2.让对倒退6步");
				Scanner sc = new Scanner(System.in);
				int n = sc.nextInt();
				//判断
				if(n == 1){
					int temp = player1.pos;
					player1.pos = player2.pos;
					player2.pos = temp;
					System.out.println("成功交换位置");
				}else{
					if(flag){
						//玩家2退后
						if(player2.pos>=5){
							player2.pos -= 6;
						}else{
							player2.pos = -1;
						}
					}else{
						//玩家1退后
						if(player1.pos>=5){
							player1.pos -= 6;
						}else{
							player1.pos = -1;
						}
					}
					System.out.println("对方后退了6步");
				}
				break;
			//时空隧道 (前进10步)
			case '〓':
				map[index]='∷';
				index += 10;
				System.out.println("时空隧道 ,前进10步");
				break;
			//暂停
			case '■':
				map[index]='∷';
				System.out.println("暂停一回合");
				//棋子赋值
				if (player1.pos != -1)
					map[player1.pos] = '①';
				if (player2.pos != -1)
					map[player2.pos] = '②';
				show();
				flag = !flag;
				for(int i=0;i<2;i++){
					rollDice();
					if(i==0){
						show();
					}
				}

				break;
			//玩家2踩到玩家1
			case '①':
				map[index]='∷';
				//玩家1回到起点
				player1.pos = -1;
				System.out.println("玩家1"+player1.pos+",玩家2"+player1.pos);
				System.out.println(player2.name+"踩到"+player1.name+","+player1.name+"回到起点");
				break;
			//玩家1踩到玩家2
			case '②':
				map[index]='∷';
				//玩家2回到起点
				player2.pos = -1;
				System.out.println("玩家1"+player1.pos+",玩家2"+player1.pos);
				System.out.println(player1.name+"踩到"+player2.name+","+player2.name+"回到起点");
				break;
		}
		return index;
	}

	// 摇骰子
	public void rollDice() {
		//在这里输出玩家一的下标
		System.out.println("玩家一     " +player1.pos);

		if (flag) {
			System.out.println(player1.name+"摇骰子");
			//清除上次棋子的值
			if (player1.pos != -1)
				map[player1.pos]='∷';
		} else {
			System.out.println(player2.name+"摇骰子");
			//清除上次棋子的值
			if (player2.pos != -1)
				map[player2.pos]='∷';
		}
		System.out.println("请输入任意字符开始掷骰子");
		Scanner sc = new Scanner(System.in);
		sc.next();
		//获取随机数
		Random random = new Random();
		int num = random.nextInt(6)+1;
		num=10;
		//摇骰子
		System.out.println(player1.pos  +"  ,.......     " +  player1.pos);
		if (flag) {
			player1.pos += num;
			System.out.println(player1.name+"摇了" + num);
			while(player1.pos!=-1 && map[player1.pos]!='∷'){
				player1.pos = props(player1.pos);
			}
		} else {
			player2.pos += num;
			System.out.println(player2.name+"摇了" + num);
			while(player2.pos!=-1 && map[player2.pos]!='∷'){
				player2.pos = props(player2.pos);
			}
		}
		// 玩家棋子
		if (player1.pos != -1)
			map[player1.pos] = '①';
		if (player2.pos != -1)
			map[player2.pos] = '②';
	}

	// 选择角色
	public void choosePlayer() {
		System.out.println("请选择人物:1.悟空  2.八戒 ");
		System.out.println("请玩家1选择人物");
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		switch (num1) {
			case 1:
				player1.name = "悟空";
				break;
			case 2:
				player1.name = "八戒";
				break;
		}
		System.out.println("玩家1选择了" + player1.name);
		System.out.println("请玩家2选择人物");
		int num2 = sc.nextInt();
		switch (num2) {
			case 1:
				player2.name = "悟空";
				break;
			case 2:
				player2.name = "八戒";
				break;
		}
		System.out.println("玩家2选择了" + player2.name);
	}

	// 初始化地图
	public void init() {
		// ∷普通格
		for (int i = 0; i < 100; i++) {
			map[i] = '∷';
		}
		// 幸运轮盘
		for (int i = 0; i < lucky.length; i++) {
			map[lucky[i]] = '◎';
		}
		// 地雷
		for (int i = 0; i < mines.length; i++) {
			map[mines[i]] = '★';
		}
		// 暂停
		for (int i = 0; i < tunnel.length; i++) {
			map[tunnel[i]] = '〓';
		}
		// 时空隧道
		for (int i = 0; i < pause.length; i++) {
			map[pause[i]] = '■';
		}

	}

	// 输出棋盘
	public void show() {
		// 第一行
		for (int i = 0; i < 31; i++) {
			System.out.print(map[i]);
		}
		System.out.println();
		// 第一列
		for (int i = 31; i <= 34; i++) {
			for (int j = 0; j < 30; j++) {
				System.out.print('一');
			}
			System.out.println(map[i]);
		}
		// 第二行
		for (int i = 65; i >= 35; i--) {
			System.out.print(map[i]);
		}
		System.out.println();
		// 第二列
		for (int i = 66; i <= 68; i++) {
			System.out.print(map[i]);
			for (int j = 0; j < 30; j++) {
				System.out.print('一');
			}
			System.out.println();
		}
		// 第三行
		for (int i = 69; i <= 99; i++) {
			System.out.print(map[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();

	}
}
