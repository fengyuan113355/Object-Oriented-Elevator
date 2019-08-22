package lift;

class Request {
	int type = 0;	//请求类型 1 为 FR
	int direction = 0;  //请求方向1 上
	int time = 0; //请求绝对时间
	int goal= 0; //目标楼层
	int cur = 0;//FR发出楼层
	int valid = 1; //请求有效性
}
