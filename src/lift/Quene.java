package lift;

class Quene {
	String str = null ;
	
	int intothequene(Request []  quene, String str){
		String str2= str.replaceAll(" ","");////去除空格
		str2= str2.replaceAll("\t","");

		
		
		
		String strs[] = str2.split("[)]");
		
		String regex = "\\(FR\\,\\d+\\,(UP|DOWN)\\,\\d{1,9}";   ////正则-
		String regex2 = "\\(ER\\,\\d+\\,\\d{1,9}";
		int k = 0;
		for(int n =0 ;n<strs.length; n++){
		    if( !( strs[n].matches(regex) | (strs[n].matches(regex2) ))){
		    	
		    	System.out.println("其中包括格式不符的请求，已忽略");
		    	continue;
		    }else {
		    	String strss[] = strs[n].split("[(),]");
		    	
				for(int i=0; i<strss.length; i++){
					if(strss[i].equals("FR")){
						//System.out.println(k);
						if(k>999999){
							return k;
						}
						quene[k] = new Request();
						quene[k].type = 1;
						quene[k].cur = Integer.parseInt(strss[i+1]);
						quene[k].direction = (strss[i+2].equals("UP")) ? 1 : 0;  
						quene[k].time = Integer.parseInt(strss[i+3]);
				
						k++;
						
							
					}else if (strss[i].equals("ER")){
						//System.out.println(k);
						if(k>999999){
							return k;
						}
						quene[k] = new Request();
						quene[k].type = 0;
						quene[k].goal = Integer.parseInt(strss[i+1]);
						quene[k].time = Integer.parseInt(strss[i+2]);		
						
						k++;
						
					}
				}
				
		    }
		    
		}	
		return k;
	}
	
}
