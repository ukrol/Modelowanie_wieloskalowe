public class CellularAutomaton { 
	
	public static void main(String[] args) { 
		int n = 16; //liczba kroków
	    int numCells = 2 * n;
		CellularAutomaton CA= new CellularAutomaton();
		CA.cellularAutomaton(n, numCells);
	}
	
	public void cellularAutomaton(int n, int numCells){
		boolean[] cells = new boolean[numCells]; //time t+1
	    //element tablicy oldCells[i] jest prawdziwy, jeśli komórka i żyje w poprzednim kroku czasowym t
	    boolean[] oldCells   = new boolean[numCells]; //time t
	    cells[numCells/2] = true;//po środku zawsze true
	    /*for (int time = 1; time < 2*n; time++) {
	    	System.out.print(cells[time]+" "); 
	    }*/
	    System.out.println();
	    for (int time = 1; time < n; time++) {
	        for (int i = 0; i < numCells; i++) {
	           if(cells[i] == true) {
	          	 System.out.print("*");
	           }
	           else {
	          	 System.out.print(" ");
	           }
	        }
	        System.out.println("");
	          
	        for (int i = 0; i < numCells; i++){ //stare na nowe
	      	  oldCells[i] = cells[i];
	        }
	          
	       // rule90(cells, oldCells, numCells);       
	        //rule60(cells, oldCells, numCells);
	        rule30(cells, oldCells, numCells);

	    }
	}
	
	public void rule90(boolean[] cells, boolean[] oldCells, int numCells) {
	    for (int i = 1; i < numCells - 1; i++){//update
		   cells[i] = oldCells[i-1] ^ oldCells[i+1]; //xor
	    }       
	}
	
	public void rule60(boolean[] cells, boolean[] oldCells, int numCells) {
		for (int i = 1; i < numCells - 1; i++){
			if(oldCells[i-1] == false && oldCells[i] == false && oldCells[i+1] == false){
				cells[i]=false;
			}
			else if(oldCells[i-1] == false && oldCells[i] == false && oldCells[i+1] == true){
				cells[i]=false;
			}
			else if(oldCells[i-1] == false && oldCells[i] == true && oldCells[i+1] == false){
				cells[i]=true;
			}
			else if(oldCells[i-1] == false && oldCells[i] == true && oldCells[i+1] == true){
				cells[i]=true;
			}
			else if(oldCells[i-1] == true && oldCells[i] == false && oldCells[i+1] == false){
				cells[i]=true;
			}
			else if(oldCells[i-1] == true && oldCells[i] == false && oldCells[i+1] == true){
				cells[i]=true;
			}
			else if(oldCells[i-1] == true && oldCells[i] == true && oldCells[i+1] == false){
				cells[i]=false;
			}
			else if(oldCells[i-1] == true && oldCells[i] == true && oldCells[i+1] == true){
				cells[i]=false;
			}
		}
	}
	public void rule30(boolean[] cells, boolean[] oldCells, int numCells) {
		for (int i = 1; i < numCells - 1; i++){
			if(oldCells[i-1] == false && oldCells[i] == false && oldCells[i+1] == false){
				cells[i]=false;
			}
			else if(oldCells[i-1] == false && oldCells[i] == false && oldCells[i+1] == true){
				cells[i]=true;
			}
			else if(oldCells[i-1] == false && oldCells[i] == true && oldCells[i+1] == false){
				cells[i]=true;
			}
			else if(oldCells[i-1] == false && oldCells[i] == true && oldCells[i+1] == true){
				cells[i]=true;
			}
			else if(oldCells[i-1] == true && oldCells[i] == false && oldCells[i+1] == false){
				cells[i]=true;
			}
			else if(oldCells[i-1] == true && oldCells[i] == false && oldCells[i+1] == true){
				cells[i]=false;
			}
			else if(oldCells[i-1] == true && oldCells[i] == true && oldCells[i+1] == false){
				cells[i]=false;
			}
			else if(oldCells[i-1] == true && oldCells[i] == true && oldCells[i+1] == true){
				cells[i]=false;
			}
		}
	}
}
