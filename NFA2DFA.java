import java.util.*;

public class NFA2DFA {

	public static void main(String[] args) 
	{
	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of states of NFA : ");
		int nos = sc.nextInt();
		System.out.print("Enter the states : ");
		char states_ar[] = new char[nos];
		HashMap<Character, Integer> states = new HashMap<Character, Integer>();
		for(int i=0;i<nos;i++)
		{
			char ch = sc.next().charAt(0);
			states_ar[i] = ch;
			states.put(ch, i);
		}
		
		System.out.print("Enter the initial state : ");
		char ini_state = sc.next().charAt(0);
		System.out.print("Enter the no. of final states : ");
		int nof = sc.nextInt();
		System.out.print("Enter the set of final states : ");
		HashMap<Character, Integer> fin_states = new HashMap<Character, Integer>();
		for(int i=0;i<nof;i++)
			fin_states.put(sc.next().charAt(0), 1);
		
		System.out.print("Enter the no. of alpha : ");
		int noa = sc.nextInt();
		System.out.print("Enter the alphabets : ");
		char alpha[] = new char[noa];
		for(int i=0;i<noa;i++)
			alpha[i] = sc.next().charAt(0);

		Queue<String> q = new LinkedList<>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		q.add(ini_state+"");
		map.put(ini_state+"", 1);
		
		System.out.println("Enter the table for NFA(where multilple characters if any will not be separated by anything)");
		System.out.println("#for null enter any state which is not present in the state set");
		String table_NFA[][] = new String[nos][noa];
		
		System.out.print("-------------------------------------\n\t");
		for(int al=0;al<noa;al++)
			System.out.print(alpha[al]+"\t");
		System.out.println();
		for(int i=0;i<nos;i++)
		{
			System.out.print(states_ar[i]+"|\t");
			for(int j=0;j<noa;j++)
			{
				
				table_NFA[i][j] = sc.next();
				//System.out.print("\t");
				
			}
			
		}
		
		System.out.println("\nThe Equivalent DFA Table is - ");
		
		String table_DFA[][] = new String[1000][noa];
		String final_states_DFA[] = new String[1000];
		int top_finalstates = 0;
		HashMap<Character, Integer> stts;
		for(int i=0;q.size() > 0;i++)
		{
			
			String cur_state = q.remove();
			System.out.print(cur_state+"|\t");
			for(int j=0;j<noa;j++)
			{
				
				table_DFA[i][j] = "";
				if(cur_state.length() == 1 && !states.containsKey(cur_state.charAt(0)))
				{
					table_DFA[i][j] = cur_state;
					if(!map.containsKey(table_DFA[i][j]))
					{
						q.add(table_DFA[i][j]);
						map.put(table_DFA[i][j], 1);
					}
					//System.out.println("for check"+(table_DFA[i][j].charAt(0)));
					if(fin_states.containsKey((table_DFA[i][j].charAt(0))))
						final_states_DFA[top_finalstates++] = table_DFA[i][j];
					System.out.print(table_DFA[i][j]+"\t");
					continue;
				}
				int flag_for_final = 0;
				stts = new HashMap<Character, Integer>();
				for(int k=0;k<cur_state.length();k++)
				{
					
					if(!states.containsKey(cur_state.charAt(k)))
						continue;
					if(fin_states.containsKey(cur_state.charAt(k)))
						flag_for_final = 1;
					for(int ch=0;ch<table_NFA[states.get(cur_state.charAt(k))][j].length();ch++)
					{
						if(!stts.containsKey(table_NFA[states.get(cur_state.charAt(k))][j].charAt(ch)) && states.containsKey(table_NFA[states.get(cur_state.charAt(k))][j].charAt(ch)))
						{
							table_DFA[i][j] += table_NFA[states.get(cur_state.charAt(k))][j].charAt(ch);//null state is appearing i.e AC
							stts.put(table_NFA[states.get(cur_state.charAt(k))][j].charAt(ch), 1);
						}
					}
							
					
				}
				if(!map.containsKey(table_DFA[i][j]))
				{
					q.add(table_DFA[i][j]);
					map.put(table_DFA[i][j], 1);
				}
				if(flag_for_final == 1)
					final_states_DFA[top_finalstates++] = table_DFA[i][j];
				System.out.print(table_DFA[i][j]+"\t");
				
			}
			
			
			System.out.println();
			
		}
		/*System.out.print("Initial state : "+ini_state+"\nFinal States : ");
		for(int fs=0;fs<top_finalstates;fs++)
			System.out.print(final_states_DFA[fs]);
		System.out.println(top_finalstates);*/
		
	}

}
