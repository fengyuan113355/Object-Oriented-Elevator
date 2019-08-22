package lift;

class Elevator {
// runtime = 0.5;
	int oc = 1; //������
	int curfloor = 1; 
	int curstate= 0; ////当前状态
//	int state = 0; ///电梯状态ֹ
	float run(int a, int b, int c, int d){  //a  是当前楼层 b 目标楼层
		
		float  runtimee= ((Math.abs(a-b)) *(float) 0.5)+ (float)1.0 ; 
		curfloor = b ; 
		
		if(c==1){
			curstate = d;
			
		}else if(c==0){
			curstate = (b>a) ? 1 :0;
		}
		return  runtimee;
		
		
	}
}
