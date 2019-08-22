package lift;

class Manager {
	
		void command (Request [] quene, Elevator ele, int a, Floor[] floorr){   
		float now = 0; 
	
		String d = null;
		int temp = 0;
		String tp = null;
		int zeroo;
		for( zeroo = 0 ;zeroo <a ; zeroo++){
	//System.out.println( zeroo );
			if(quene[zeroo].time == 0){
				if((((quene[zeroo].goal > 10 )| (quene[zeroo].goal < 1 )) & (quene[zeroo].type==0))|((quene[zeroo].type==1) & ((quene[zeroo].cur > 10) |(quene[zeroo].cur < 1)))){
					System.out.println("楼层请求不在1-10内");
					quene[zeroo].valid = 0;
					continue;
				}else if((quene[zeroo].type==1) & ((quene[zeroo].cur ==10 & quene[zeroo].direction== 1) | (quene[zeroo].cur ==1 & quene[zeroo].direction==0))){
					 System.out.println("该层楼不能执行此操作");
					 quene[zeroo].valid = 0;
					 continue;
				}else if((quene[zeroo].type==1) & ((quene[zeroo].cur ==10 & quene[zeroo].direction== 1) | (quene[zeroo].cur ==1 & quene[zeroo].direction==0))){
					 System.out.println("该层楼不能执行此操作");
					 quene[zeroo].valid = 0;
					 continue;
				}
				break;
			}
		}
		
		//System.out.println(zeroo + "********");
		if(zeroo==a){
			System.out.println("没有任何一项的请求时间为0，全部无效");
			return ;
		}
		
		//System.out.println( zeroo );
		
		for(int j = zeroo ; j < a; j++){
		/*	if(zeroo==a){
				System.out.println("没有任何一项的请求时间为0，全部无效");
				break;
			}
			*/
			//if(quene[zero].time == 0){
			if(quene[j].time < temp){
				System.out.println("不符合时间顺序排列");
				quene[j].valid = 0;
				continue;
		
			}
			else if((((quene[j].goal > 10 )| (quene[j].goal < 1 )) & (quene[j].type==0))|((quene[j].type==1) & ((quene[j].cur > 10) |(quene[j].cur < 1)))){
				System.out.println("楼层请求不在1-10内");
				quene[j].valid = 0;
				continue;
			}
			else if((quene[j].type==1) & ((quene[j].cur ==10 & quene[j].direction== 1) | (quene[j].cur ==1 & quene[j].direction==0))){
				 System.out.println("该层楼不能执行此操作");
				 quene[j].valid = 0;
				 continue;
			}
			else{
			
				if(quene[j].type == 1){
					
					int judge1=0;
					int judge2 =0;
					int judge3 =0;
					int judge4=0;
					int judge5=0;
					int judge= 0;
					if( (quene[j].time == 0)){
						if(quene[j].direction==1){
							judge4 = (quene[j].time>=floorr[quene[j].cur].frup)?1:0;
							judge =judge4;
							//System.out.println(judge5 + "********");
						}else if(quene[j].direction==0){
							judge5 = (quene[j].time>= floorr[quene[j].cur].frdown)?1:0;
							judge = judge5;
							//System.out.println(judge5 + "********");
						}
					}else{
						if(quene[j].direction==1){
							judge1 = (quene[j].time> floorr[quene[j].cur].frup)?1:0;
							judge =judge1;
						}else if(quene[j].direction==0){
							judge2 = (quene[j].time> floorr[quene[j].cur].frdown)?1:0;
							judge = judge2;
							//System.out.println(floorr[quene[j].cur].frdown);
						}
					}
					
					
					
					if(judge==1){
				        judge=0;
						if((quene[j].cur == ele.curfloor ) & (now >= quene[j].time)){
							
								now = now + 1 ;
								System.out.println("(" + ele.curfloor + ","+ "STILL" + "," + now + ")");
								tp="STILL";
						}
							
						else if((quene[j].cur == ele.curfloor ) & (now < quene[j].time)){
							
							now = ele.run(ele.curfloor, quene[j].cur, quene[j].type, quene[j].direction) + quene[j].time ;
										
							System.out.println("(" +ele.curfloor + ","+ "STILL" + "," + (now) + ")"); ///
							tp="STILL";
						
						}
						else if(quene[j].cur != ele.curfloor){
			
							d= (quene[j].cur > ele.curfloor ) ? "UP" : "DOWN" ;
							if(now >= quene[j].time){
								now = now + ele.run(ele.curfloor, quene[j].cur, quene[j].type, quene[j].direction);
								
							} else if (now <quene[j].time){
								now = ele.run(ele.curfloor, quene[j].cur, quene[j].type, quene[j].direction) + quene[j].time ;	
							}
							
						/*	if(d.equals("UP")){
								floorr[quene[j].cur].frup= now;
							}else{
								floorr[quene[j].cur].frdown= now;
							}
	*/
							System.out.println("(" +ele.curfloor + ","+ d + "," + (now - 1) + ")");
							tp=d;
						}	
						if(quene[j].direction==1){
							floorr[quene[j].cur].frup= now;
						}else{
							floorr[quene[j].cur].frdown= now;
							}
						}else  {System.out.println("同层请求");}

					
				}else if (quene[j].type == 0){
					int jud=0;
					if(quene[j].time == 0){
						jud= (quene[j].time >= floorr[quene[j].goal].er)?1:0;
						
					}else{
						jud= (quene[j].time > floorr[quene[j].goal].er)?1:0;
					}
					
					if(jud==1)
					{			jud=0;		
					if((quene[j].goal == ele.curfloor ) & (now >= quene[j].time)){
						
							now = ele.run(ele.curfloor, quene[j].goal, quene[j].type, quene[j].direction) + now ;
							System.out.println("(" +ele.curfloor + ","+ "STILL" + "," + (now) + ")");
							floorr[quene[j].goal].er= now ;
							tp="STILL";
						
					}else if((quene[j].goal == ele.curfloor ) & (now < quene[j].time)){
						
						now = ele.run(ele.curfloor, quene[j].goal, quene[j].type, quene[j].direction) + quene[j].time ;
						floorr[quene[j].goal].er= now ;
						System.out.println("(" +ele.curfloor + ","+ "STILL" + "," + (now) + ")" ); ///�����ź�
						tp="STILL";
						
					}else if(quene[j].goal != ele.curfloor){
						//System.out.println(quene[j].time + "++++++");
						//System.out.println(floorr[quene[j].goal].er + "*****");
							
							d= (quene[j].goal > ele.curfloor ) ? "UP" : "DOWN" ;	
							if(now >= quene[j].time){
								now = now + ele.run(ele.curfloor, quene[j].goal, quene[j].type, quene[j].direction);
								
									floorr[quene[j].goal].er= now ;
								///	System.out.println(floorr[quene[j].goal].er + "*-*-*-*-");
							} else if (now < quene[j].time){
								now = ele.run(ele.curfloor, quene[j].goal, quene[j].type, quene[j].direction) + quene[j].time ;	
								
								floorr[quene[j].goal].er= now ;
								//System.out.println(floorr[quene[j].goal].er + "*-*-*-*-");
								
							}
							
							System.out.println("(" +ele.curfloor + ","+ d + "," + (now - 1) + ")" );
							tp=d;
					}
						
					}else{System.out.println("同层请求");}
				}
			temp=quene[j].time; ////比较时间顺序
			}
		}
		}
}
	


