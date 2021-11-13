package solution;

import java.util.*;

public class BracketBalancerAndAndOrValidator {
	public static void main(String[] args) {        
        String val = "1 AND 2 AND (2 OR 3 AND (2 OR 3)) AND (2 OR 3 AND (2 OR 3)) and (aa)";
        boolean value = mainMethod(val);
        System.out.println("Value is :"+value);
        }
 
 public static boolean mainMethod(String val) {
	 Integer[] valArray = {1,2,3};
        char[] ch = val.toCharArray();
        boolean value = val.contains("(");
        if(value) {
            value = checkBalanced(val);
            if(!value)
            	return value;
            else {
            	bracketMatches(ch);
            }
        }
        int count = 0;
        int[] tempArray = new int[2];
        int count1 = 0;
        char[] tempArray1 = new char[3];
        for(int i=0;i<ch.length;i++) {
            if(count > 0) {
            	if((ch[i] >47 && ch[i] <58)) {
            		
            	}else {
            		 value = checkValue(tempArray,valArray,count);
 	                if(!value) {
 	                	return false;
 	                }
 	                count = 0;
 	    	        tempArray = new int[2];
            	}
               
            }if(ch[i] >47 && ch[i] <58) {
                tempArray[count] = Integer.parseInt(String.valueOf(ch[i]));
                count = count+1;

            }if(count1 > 0) {
            	if(((ch[i] > 64 && ch[i] < 91) || (ch[i] > 96 && ch[i] < 123))) {
            		
            	}else {
            		value = checkValueAndOrOr(tempArray1,count1);
	                if(!value) {
	                	return false;
	                }
	                count1 = 0;
	    	        tempArray1 = new char[3];
            	}
            	
            }if((ch[i] > 64 && ch[i] < 91) || (ch[i] > 96 && ch[i] < 123)) {
            	tempArray1[count1] = ch[i];
            	count1 = count1 +1;
            }
        }
		return value;
 }

    public static boolean checkBalanced(String check)
    {
        Stack<Character> S = new Stack<Character>();
        for(int a = 0; a < check.length(); a++)
        {
            char let = check.charAt(a);
            if(let == '(')
                S.push(let);
            else if(let == ')')
            {
                if(S.empty())
                    return false;
                switch(let)
                {
                    case ')':
                        if (S.pop() != '(')
                            return false;
                        break;
                    default:
                        break;
                }
            }
        }
        if(S.empty())
            return true;
        return false;
    }

    public static boolean checkValueAndOrOr(char[] arr,int count) {
    	boolean bool = false;
    	if(arr.length > 0) {
    		String s = new String();
	        for(int i=0;i<count;i++) {
	        	s = s+arr[i];
	        }
            String[] values = {"AND","OR"};
            
            for(String v : values) {
            	bool = s.equalsIgnoreCase(v);
            	if(bool) {
            		break;
            	}
            }
            
        }
    	return bool;
    }

        public static boolean checkValue(int[] arr,Integer[] result,int count) {
        int val = 0;
        boolean response = false;
        String s = new String();
        for(int i=0;i<count;i++) {
        	s = s+arr[i];
        }
        val = Integer.valueOf(s);
        Integer check = val;
        long rst = Arrays.asList(result).stream().filter(va -> va==check).count();
        if(rst>0) {
             response= true;
         }else {
             response = false;
          }
         return response;
        }
        
        public static boolean bracketMatches(char[] value) {
        	List<Integer> openList = new ArrayList<>();
        	List<Integer> closeList = new ArrayList<>();
        	for(int i=0;i<value.length;i++) {
        		if(value[i] == '(') {
        			openList.add(i);
        			}
        		if(value[i] == ')') {
        			closeList.add(i);
        		}
        	}
        	System.out.println("openList :"+openList);
        	System.out.println("closeList :"+closeList);
        	//Collections.reverse(openList);
        	System.out.println("openList Reverse :"+openList);
        	Map<Integer,Integer> openMap = new HashMap<>();
        	Stack<Integer> tempStack = null;
        	int lessThanCount = 0;
        	int size = 0;
        	for(int i=0;i<closeList.size();i++) {
        		if(tempStack == null || tempStack.size() == 0) {
        			tempStack = new Stack<>();
	        		for(int j=size;j< openList.size();j++) {
	        			if(closeList.get(i) > openList.get(j)) {
	        				tempStack.add(openList.get(j));
	        				lessThanCount = lessThanCount + 1;
	        			}
	        		}
	        		System.out.println("Temp Stack : "+tempStack);
        		}
        		System.out.println("Before Finishing map : "+openMap);
        		openMap.put(closeList.get(i), tempStack.pop());
        		if(tempStack.size() ==0) {
        			size = size + lessThanCount;
        			lessThanCount =0;
        		}
        	}
        	System.out.println("openMap :"+openMap);
        	return false;
        }
}
