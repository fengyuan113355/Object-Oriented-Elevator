package elevator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class diaodu {
	
	public static void main(String args[]){
		System.out.println("命令序列：");
		Scanner sc=new Scanner(System.in);
		String m = new String();
		if (sc.hasNextLine()){	
			m=sc.nextLine();
		}
		sc.close();
		String instr=m.replaceAll(" ","");/*instr是命令序列*/
		instr=instr.replace("\t","");
		if(instr.equals("")){/*boolean*/
			System.out.println("Input Wrong");
			System.exit(0);
		}
		/*Pattern pattern=Pattern.compile("(\\(FR,\\+?0*([1-9]|10),(UP|DOWN),\\+?0*\\d+\\)|\\(ER,\\+?0*([1-9]|10),\\+?0*\\d+\\)){1,10000}");////////////////////////
		Matcher pattern_temp=pattern.matcher(instr);
		boolean pd=pattern_temp.matches();
		if(!pd){
			System.out.println("Input Wrong");
			System.exit(0);
		}/*输入格式错误*//*instr代表命令序列*/
		/*else{
			System.out.println("Right");
			System.exit(0);
		}*///////////////////////////////////////////////////////////////////
		Pattern dil=Pattern.compile("\\(FR,\\+?0*([1-9]|10),(UP|DOWN),\\+?0*\\d+\\)|\\(ER,\\+?0*([1-9]|10),\\+?0*\\d+\\)");////////////////
		Matcher dil_temp=dil.matcher(instr);
		int i=0;
		int number1=0, number2=0;
		int record_time=0;
		rq[] instr_mat=new rq[10000];/*新建一个数组需先分配空间*/
		for(;i<10000;i++){
			rq a=new rq();
			instr_mat[i]=a;
		}
		for(i=0;dil_temp.find()&i<10000;i++){
			String section=dil_temp.group();
			String[] fetch=section.split("[,)]");/*在循环中定义的变量，每次循环时都是重新开始，没有初值*/
			if(section.charAt(1)=='F'){
				try {	
					number1=Integer.parseInt(fetch[1]);
					number2=Integer.parseInt(fetch[3]);
				}
				catch (NumberFormatException e) {
					System.out.println("一条命令有误，被剔除");
					i--;
					continue;
				}//
				if(fetch[2].equals("DOWN")&number1==1){
					System.out.println("一条命令有误，被剔除");
					i--;
					continue;
				}
				if(fetch[2].equals("UP")&number1==10){
					System.out.println("一条命令有误，被剔除");
					i--;
					continue;
				}/*检测1楼和10楼不符合方向要求的命令*/
			}
			else{
				try{
					number1=Integer.parseInt(fetch[1]);
					number2=Integer.parseInt(fetch[2]);
				}
				catch(NumberFormatException e){
					System.out.println("一条命令有误，被剔除");
					i--;
					continue;
				}
			}
			if(i==0&number2!=0){
				System.out.println("Input Wrong");
				System.exit(0);
			}/*第一个时间必须是0，否则视为非法输入，退出程序*/
			if(number2<record_time|number1<1|number1>10){
				i--;
				System.out.println("一条命令有误，被剔除");
				continue;
			}/*将不符合升序排序的命令以及楼层不在规定范围内的命令剔除掉*/
			record_time=number2;
			instr_mat[i].fz_rq_time(number2);
			instr_mat[i].fz_Fl(number1);
			instr_mat[i].fz_io( section.charAt(1)=='F'? 0:1);/*楼层外是0，电梯里是1*/
			if(section.charAt(1)=='F'){
				if(fetch[2].equals("UP")){
					instr_mat[i].fz_direction(1);
				}
				else{
					instr_mat[i].fz_direction(-1);
				}
			}
			else{
				instr_mat[i].fz_direction(0);
			}
		}
		if(i==0){
			System.out.println("无有效命令");
			System.exit(0);
		}
		rqquene px=new rqquene();
		px.reset(instr_mat);/*将构造的指令数组传到rqquene中进行排序问题的检查及过滤*/
		
		floor give=new floor();
		give.receive(instr_mat);
		
		/*elevator real=new elevator();
		for(i=0;i<10000;i++){
			if(instr_mat[i].getFl()==0){
				continue;
			}
			real.action(instr_mat[i]);
		}*/
	}
}


class elevator{
	private int state=1;/*0运动，1到10分别代表静止在1到10的哪个楼层*/
	private int first=1,last=1;/*运动时first初始楼层last结束楼层*/
	private double time=0;/*time代表电梯里的时间*/
	private int initial=0;/*initial决定最开始的开关门情况*/
	void action(rq instruction){
		int n,t;
		n=instruction.getFl();
		t=instruction.getrq_time();
		if(time<t){
			time=t;
		}
		if(state!=0&state==n){
			time=time+1;/*ͬ同楼层变化一次开关门时间作为响应*/
			state=n;
			System.out.println("("+n+",STILL,"+time+")");
		}
		else if(state!=0&state!=n){
			if(state>n){
				time=time+(state-n)*0.5;
				System.out.println("("+n+",DOWN,"+time+")");
				time=time+1;
				state=n;
			}
			else{
				time=time+(n-state)*0.5;
				System.out.println("("+n+",UP,"+time+")");
				time=time+1;
				state=n;
			}
		}
		else{
			System.out.println("Wrong");//////////////////////
			System.exit(0);
		}
	}
}

class floor{
	void receive(rq a[]){
		elevator real=new elevator();
		int i=0;
		for(;i<10000;i++){
			if(a[i].getFl()==0){
				continue;
			}
			real.action(a[i]);
		}
	}
}

class rq{
	private int rq_time;/*请求发出的时间（一定是整数）*/
	private int Fl;/*该命令下电梯的目标楼层*/
	private int io;/*命令类型,楼层外是0，电梯里是1*/
	private int direction;/*命令的方向1代表向上 0代表不动 -1代表向下*/
	int getrq_time(){
		return rq_time;
	}
	int getFl(){
		return Fl;
	}
	int getio(){
		return io;
	}
	int getdirection(){
		return direction;
	}
	void fz_Fl(int a){
		Fl=a;
	}
	void fz_rq_time(int b){
		rq_time=b;
	}
	void fz_io(int c){
		io=c;
	}
	void fz_direction(int d){
		direction=d;
	}
}/*命令结点*/

class rqquene{
	private double[] run_time=new double[10000];/*定义一个记录run_end_time的数组*/
	void reset(rq init[]){/*进行命令有效性的检查，过滤掉不符合要求及不必要的命令*/
		int i=0,j=0,k=0;
		int run_end_time=0;
		int sub;
		/*对init数组进行操作*/
		for(j=0;j<10000;j++){
			for(i=0,k=0;i<j;i++){
				if(init[i].getFl()==0){
					continue;
				}
				if(init[i].getio()==init[j].getio()&init[i].getdirection()==init[j].getdirection()&init[i].getFl()==init[j].getFl()&init[j].getrq_time()<=run_time[i]){
					init[j].fz_Fl(0);
					System.out.println("一条命令被剔除");
					break;
				}
				k=i;
			}
			if(init[j].getFl()==0){
				continue;
			}/*被剔除的命令不需要记录其run_time，run_time代表的是当次命令的结束时间，以最后关门为准*/
			else{
				if(j==0){
					run_time[0]=(init[j].getFl()-1)*0.5+1;
					continue;
				}
				if(init[j].getFl()>init[k].getFl()){
					sub=init[j].getFl()-init[k].getFl();
				}
				else{
					sub=init[k].getFl()-init[j].getFl();
				}/*代表楼层差*/
				if(init[j].getrq_time()>run_time[k]){
					run_time[j]=init[j].getrq_time()+sub*0.5+1;
				}
				else{
					run_time[j]=run_time[k]+sub*0.5+1;
				}
			}
		}
	}
}