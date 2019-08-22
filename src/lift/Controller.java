package lift;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
	try{

			Request quene[]= new Request[1000000];   //总共接受的请求个数
			 
			Floor floor[] = new Floor[11];
			for(int i = 1 ; i<11 ; i++){
				 floor[i]=new Floor();
			}
			Scanner in = new Scanner(System.in);
	        String str = in.nextLine();
	        in.close();
	  
	        Quene q = new Quene();
	          
	        int o= q.intothequene(quene , str);  //实际的请求个数
	        if (o > 999999) {System.out.println("请求的个数大于处理范围");}
	        else{
	      
				Elevator ele = new Elevator();
				Manager mag = new Manager();
				mag.command(quene , ele , o , floor);
		        }
		}catch(Exception e)
		{
			System.out.println("极异常输入！！");
		}
		}
	}

